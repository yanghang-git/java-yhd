package com.yhd.service.backend;

import com.yhd.pojo.User;

import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/29 11:39
 * @Since 1.8
 */
public interface UserService {
	/**
	 * get All User instance result list gather
	 * @return User instance all
	 */
	List<User> getAllList();

	/**
	 * will appoint of username freeze
	 * @param username username
	 * @return true freeze success or  false freeze fail
	 */
	boolean freeze(String username);

	/**
	 * will appoint of username unfreeze
	 * @param username username
	 * @return true unfreeze success or  false unfreeze fail
	 */
	boolean unfreeze(String username);
}
