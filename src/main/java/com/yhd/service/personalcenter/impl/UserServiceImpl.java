package com.yhd.service.personalcenter.impl;

import com.yhd.dao.UserDao;
import com.yhd.pojo.User;
import com.yhd.service.personalcenter.UserService;

import java.sql.Connection;

/**
 * @author 杨航
 * @date 2020/9/28 18:12
 * @since 1.8
 */
public class UserServiceImpl implements UserService {
	private Connection conn;
	private UserDao dao;

	public UserServiceImpl(Connection conn, UserDao dao) {
		this.conn = conn;
		this.dao = dao;
	}

	/**
	 * get user by username
	 * @param username user of name
	 * @return user instance
	 */
	@Override
	public User getUserByName(String username) {
		return dao.getUserById(conn, username);
	}

	/**
	 * update user
	 * @param user user instance
	 * @return is update success
	 */
	@Override
	public boolean updateUser(User user) {
		return dao.updateUser(conn, user);
	}

	/**
	 * alter user default address
	 * @param addressId address of id
	 * @param userId    user of id
	 * @return is alter success
	 */
	@Override
	public boolean alterDefaultAddress(int addressId, String userId) {
		return dao.updateUserDefaultAddress(conn, addressId, userId);
	}

	/**
	 * check user of phone is exist
	 * @param phone user of phone
	 * @return is exist
	 */
	@Override
	public boolean checkUserPhoneIsExist(String phone) {
		return dao.checkUserPhoneIsExist(conn, phone);
	}
}
