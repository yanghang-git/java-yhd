package com.yhd.service.backend.impl;

import com.yhd.dao.UserDao;
import com.yhd.pojo.User;
import com.yhd.service.backend.UserService;

import java.sql.Connection;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/30 18:17
 * @Since 1.8
 */
public class UserServiceImpl implements UserService {

	private Connection conn;
	private UserDao dao;


	public UserServiceImpl(Connection conn, UserDao dao) {
		this.conn = conn;
		this.dao = dao;
	}

	/**
	 * fuzzy search get All User instance by user id result list gather
	 * @param id user of id
	 * @return User instance all
	 */
	@Override
	public List<User> getAllByIdList(String id) {
		return dao.getAllByIdList(conn, id);
	}

	/**
	 * will appoint of username freeze
	 * @param username username
	 * @return true freeze success or  false freeze fail
	 */
	@Override
	public boolean freeze(String username) {
		return dao.updateUserFreeze(conn, username, true);
	}

	/**
	 * will appoint of username unfreeze
	 * @param username username
	 * @return true unfreeze success or  false unfreeze fail
	 */
	@Override
	public boolean unfreeze(String username) {
		return dao.updateUserFreeze(conn, username, false);
	}
}
