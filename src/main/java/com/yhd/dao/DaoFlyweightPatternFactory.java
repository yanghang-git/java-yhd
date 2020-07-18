package com.yhd.dao;

import com.mysql.jdbc.StringUtils;
import com.yhd.util.JDBCUtils;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 杨航
 * @Date 2020/6/28 11:47
 * @Since 1.8
 */
public class DaoFlyweightPatternFactory {
	/*
		结合工厂的享元
	 */

	private static final DaoFlyweightPatternFactory factory = new DaoFlyweightPatternFactory();
	private final String separator = "&";
	private Map<String, BaseDao> cache = new HashMap<>();
	private DaoFlyweightPatternFactory() { }
	public enum Type {
		MYSQL;

		/*
			预先规定好。 添加其他数据库的时候。  应该是什么格式。 表名+XxxXxx...
			 	比如 MySql ： 表名+DaoImpl
		 */
		public static String getSuffix(Type type) {
			switch(type) {
				case MYSQL:
					return "DaoImpl";
				default:
					return null;
			}
		}
	}

	public static DaoFlyweightPatternFactory getInstance() {
		return factory;
	}

	/**
	 * 默认为Mysql数据的实现
	 * @param daoName 表名
	 * @param <T> 返回值类型
	 * @return 对应的数据库实现
	 */
	public <T> T getDaoImpl(String daoName) {
		return getDaoImpl(daoName, Type.MYSQL);
	}
	/**
	 * 默认为Mysql数据的实现
	 * @param daoName 表名
	 * @param type 已实现的数据库类型
	 * @param <T> 返回值类型
	 * @return 对应的数据库实现
	 */
	public <T> T getDaoImpl(String daoName, Type type) {
		String tableName = getClassNameByTableName(daoName);
		String key = tableName + this.separator + type;
		if (this.cache.containsKey(key)) {
			return (T) this.cache.get(key);
		}
		this.cache.put(key, getBaseDaoByTableNameAndSqlType(tableName, type));
		return (T) this.cache.get(key);
	}

	private BaseDao getBaseDaoByTableNameAndSqlType(String daoName, Type type) {
		String className = "com.yhd.dao.impl." + daoName + Type.getSuffix(type);
		try {
			Constructor<?> constructor = Class.forName(className).getConstructor();
			return (BaseDao) constructor.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Table name incorrectness");
		}
	}

	private String getClassNameByTableName(String typeName) {
		if(StringUtils.isNullOrEmpty(typeName)) {
			return typeName;
		}
		typeName = typeName.substring(0, 1).toUpperCase() + typeName.substring(1);
		return JDBCUtils.replace_FirstToUpper(typeName);
	}


}
