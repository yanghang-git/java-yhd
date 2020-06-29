package com.yhd.service.backend.impl;

import com.yhd.dao.AdminDao;
import com.yhd.pojo.Admin;
import com.yhd.service.backend.AdminService;

import java.sql.Connection;

/**
 * @Author 杨航
 * @Date 2020/6/29 14:20
 * @Since 1.8
 */
public class AdminServiceImpl implements AdminService {

	private Connection conn;
	private AdminDao dao;

	public AdminServiceImpl(Connection conn, AdminDao dao) {
		this.conn = conn;
		this.dao = dao;
	}

	/**
	 * according username and password try login
	 * @param username account
	 * @param password password
	 * @return not null is success or null is failure
	 */
	@Override
	public Admin login(String username, String password) {
		return dao.getAdminByIdAndPassword(conn, username, password);
	}


}
