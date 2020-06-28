package com.yhd.dao;

import com.yhd.pojo.Address;

import java.sql.Connection;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/28 14:31
 * @Since 1.8
 */
public interface AddressDao {
	/**
	 * 根据用户账号（id） 查询对应的地址集合
	 * @param conn 连接
	 * @param userId 用户账号
	 * @return List集合
	 */
	List<Address> getByUserIdList(Connection conn, String userId);

	/**
	 * 根据自己的id删除记录
	 * @param conn 连接
	 * @param id 自己的id
	 * @return 是否删除成功
	 */
	boolean removeById(Connection conn, int id);

	/**
	 * 根据一个Address的实例。去添加数据
	 * @param conn 连接
	 * @param address 实例
	 * @return 是否添加成功
	 */
	boolean addAddress(Connection conn, Address address);

	/**
	 * 根据一个Address的实例。去修改数据
	 * @param conn 连接
	 * @param address 实例
	 * @return 是否修改成功
	 */
	boolean updateAddress(Connection conn, Address address);
}
