package com.yhd.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author 杨航
 * @Date 2020/6/24 17:33
 * @Since 1.8
 */
public class ConnectionFactory {

	public static final ConnectionFactory INSTANCE = new ConnectionFactory();
	private Context ctx;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private ConnectionFactory() {
		try {
			this.ctx = new InitialContext();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// 我需要什么参数。 我可以自己指定。 也可以通过人家类自带的静态常量
	public static final String MYSQL = "MYSQL";
	public static final String MYSQL_TOMCAT_CONN = "SQL_SERVER";

	public Connection create() {
		return this.create(MYSQL);
	}



	public Connection create(String type) {

		if (MYSQL.equals(type)) {
			return createMysqlConnection();
		} else if (MYSQL_TOMCAT_CONN.equals(type)) {
			return createMysqlTomcatConnection();
		}
		return null;
	}
	private Connection createMysqlTomcatConnection() {
		try {
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/news");
			return ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Connection createMysqlConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/yhddb", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 现在这个 算是基本实现了

}
