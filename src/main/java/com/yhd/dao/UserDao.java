package com.yhd.dao;

import com.yhd.pojo.User;

import java.sql.Connection;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/27 16:08
 * @Since 1.8
 */
public interface UserDao {
	/**
	 * 获取全部的User用户
	 * @param conn 连接
	 * @return 用户集合
	 */
	List<User> getListAll(Connection conn);

	/**
	 * 修改User用户
	 * @param conn 连接
	 * @param user 用户
	 * @return 是否修改成功
	 */
	boolean update(Connection conn, User user);
}
