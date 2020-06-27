package com.yhd.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

/**
 * @Author 杨航
 * @Date 2020/6/24 18:11
 * @Since 1.8
 */
public class JDBCUtils {
	public static void closeAll(Connection conn, Statement st, ResultSet rs) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 将传递进来的字符串。首字母大写 在其前方加上set
	 * @param field 属性
	 * @return 属性对应的set方法的名字
	 */
	public static String firstAddSet(String field) {
		// abc   setAbc
		return "set" + field.substring(0, 1).toUpperCase() + field.substring(1);
	}

	/**
	 * 处理数据库中字段带下划线的函数。 将下划线提出。下划线下一个首字母大写 <br/>
	 * 		如果没有下划线则直接返回	<br/>
	 * 		如果下划线为只有一个。 只删除下划线不做处理
	 * @param str 处理的字段
	 * @return 处理完成的字段
	 */
	public static String replace_FirstToUpper(String str) {
		int index = str.indexOf("_");
		// 如果没有下划线则直接返回
		if(index == -1) return str;
		// 如果下划线为只有一个。 只删除下划线不做处理
		else if(index == str.length() - 1) return str.replace("_", "");
		str = str.replaceFirst("_", "");
		String result = str.substring(0, index) + str.substring(index , index + 1).toUpperCase() + str.substring(index + 1);
		// 处理一个下下划线后判断是字符串中是否还有下划线。如果后则递归调用即可。
		if(result.contains("_")) {
			result = replace_FirstToUpper(result);
		}
		return result;
	}

	/**
	 * 随即生成一个32位的字符串 作为订单编号
	 * @return 订单编号
	 */
	public static String randomCreateGoodsId() {
		return UUID.randomUUID().toString().replace("-","");
	}

}
