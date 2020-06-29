package com.yhd.service.backend;

import com.yhd.pojo.Admin;

/**
 * @Author 杨航
 * @Date 2020/6/29 10:57
 * @Since 1.8
 */
public interface AdminService {
	/**
	 * according username and password try login
	 * @param username account
	 * @param password password
	 * @return not null is success or null is failure
	 */
	Admin login(String username, String password);
}
