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
	boolean updateUser(Connection conn, User user);

	/**
	 * 添加User用户
	 * @param conn 连接
	 * @param user 用户
	 * @return 是否添加成功
	 */
	boolean addUser(Connection conn, User user);
	/**
	 * 删除User用户 根据用户Id
	 * @param conn 连接
	 * @param id 用户id
	 * @return 是否添加删除
	 */
	boolean removeById(Connection conn, String id);
}
