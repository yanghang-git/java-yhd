package com.yhd.dao;


import com.yhd.util.JDBCUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/24 17:01
 * @Since 1.8
 */
public abstract class BaseDao<T> {

	private Class<T> target;

	public BaseDao(){
		ParameterizedType parameterized = (ParameterizedType) this.getClass().getGenericSuperclass();
		target = (Class<T>) parameterized.getActualTypeArguments()[0];
	}

	/**
	 * 增删改
	 * @param conn 连接
	 * @param sql 预编译语句
	 * @param args 占位符
	 * @return 受影响的行数
	 */
	protected int update(Connection conn , String sql , Object... args) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 1; i <= args.length; i++) {
				ps.setObject(i, args[i-1]);
			}
			System.out.println(ps);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(null , ps , null);
		}
		return -1;
	}

	/**
	 * 获取多个
	 * @param conn 连接
	 * @param sql 预编译语句
	 * @param args 占位符
	 * @return 多个实例
	 */
	protected List<T> getInstances(Connection conn , String sql , Object... args){
		List<T> list = new ArrayList<T>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 1; i <= args.length; i++) {
				ps.setObject(i, args[i-1]);
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsm = rs.getMetaData();
			while (rs.next()) {
				list.add(newInstance(rs, rsm));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(null , ps , rs);
		}
		return list;
	}

	/**
	 * 根据ResultSet提供的值创建一个 T 类型的实例并返回。
	 * @param rs 返回集
	 * @param rsmd 返回集的原数据
	 * @return 实例
	 */
	private T newInstance(ResultSet rs, ResultSetMetaData rsmd) throws IllegalAccessException, InstantiationException, SQLException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
		T t = target.newInstance();
		int len = rsmd.getColumnCount();
		for (int i = 1; i <= len; i++) {
			String columnLabel = rsmd.getColumnLabel(i);	// 获取列别名
			String label = JDBCUtils.replace_FirstToUpper(columnLabel);
			// 计算反射可以更改私有的。但是最好不要去更改私有的
			String methodName = JDBCUtils.firstAddSet(label);
			Class<?> type = target.getDeclaredField(label).getType();
			Method method = target.getMethod(methodName, type);
			method.invoke(t, rs.getObject(columnLabel));
		}
		return t;
	}

	/**
	 * 获取单个
	 * @param conn 连接
	 * @param sql 预编译语句
	 * @param args 占位符
	 * @return 单个实例
	 */
	protected T getInstance(Connection conn ,String sql ,Object... args){
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 1; i <= args.length; i++) {
				ps.setObject(i, args[i-1]);
			}
			rs = ps.executeQuery();
			ResultSetMetaData md = rs.getMetaData();
			while (rs.next()) {
				return newInstance(rs, md);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeAll(null , ps , rs);
		}
		return null;
	}
	protected  <E> E getSimple(Connection conn, String sql, Object... args) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		E e = null;
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 1; i <= args.length; i++) {
				ps.setObject(i, args[i-1]);
			}
			rs = ps.executeQuery();

			e = null;
			if(rs.next()){
				e = (E) rs.getObject(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtils.closeAll(null, ps , rs);
		}
		return e;
	}

}
