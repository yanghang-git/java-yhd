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
	 * 获取地址详细信息根据id
	 * @param conn 连接
	 * @param id id
	 * @return 详细地址
	 */
	String getAddressDetailById(Connection conn, int id);
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

	/**
	 * 获取address的示例跟它的Id
	 * @param conn 连接
	 * @param addressId id
	 * @return 示例
	 */
	Address getAddressById(Connection conn, int addressId);
}
