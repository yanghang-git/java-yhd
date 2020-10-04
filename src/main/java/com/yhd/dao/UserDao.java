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
	 * 获取分页的User用户
	 * @param conn 连接
	 * @return 用户集合
	 */
	List<User> getListAll(Connection conn);

	/**
	 * 根据用户的Id进行查询。 模糊查询
	 * @param conn 连接
	 * @param id 用户的账号
	 * @param pageSize 第几页
	 * @param pageCount 一页显示多少
	 * @return 用户集合 List
	 */
	List<User> getAllByIdList(Connection conn, String id, int pageSize, int pageCount);

	/**
	 * 根据用户的Id进行 冻结 / 解冻
	 * @param conn 连接
	 * @param id 用户的账号
	 * @param freeze 解冻 / 冻结
	 * @return 是否修改成功
	 */
	boolean updateUserFreeze(Connection conn, String id, boolean freeze);

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

	/**
	 * 获取用户的个数.模糊查询
	 * @param conn 连接
	 * @param id 用户的账号
	 * @return 用户的个数
	 */
	long getUserCount(Connection conn, String id);

	/**
	 * 根据用户id获取其信息
	 * @param conn 连接
	 * @param userId 用户id
	 * @return 用户
	 */
	User getUserById(Connection conn, String userId);

	/**
	 * 修改用户的默认地址
	 * @param conn 连接
	 * @param addressId 地址编号
	 * @param userId 用户名称
	 * @return 是否修改成功
	 */
	boolean updateUserDefaultAddress(Connection conn, int addressId, String userId);

	/**
	 * 检查手机号是否存在
	 * @param conn 连接
	 * @param phone 手机号
	 * @return 是否存在
	 */
	boolean checkUserPhoneIsExist(Connection conn, String phone);
}
