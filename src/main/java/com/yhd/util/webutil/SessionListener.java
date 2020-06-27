package com.yhd.util.webutil;


import com.yhd.util.ConnectionFactory;
import com.yhd.util.ContentConstant;
import com.yhd.util.JDBCUtils;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.sql.Connection;

/**
 * @Author 杨航
 * @Date 2020/6/26 18:04
 * @Since 1.8
 */
public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		HttpSession session = httpSessionEvent.getSession();
		Connection conn = ConnectionFactory.INSTANCE.create(ConnectionFactory.MYSQL_TOMCAT_CONN);
		session.setAttribute(ContentConstant.SESSION_CONNECTION, conn);
	}


	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		HttpSession session = httpSessionEvent.getSession();
		JDBCUtils.closeAll((Connection) session.getAttribute(ContentConstant.SESSION_CONNECTION), null, null);
	}
}
