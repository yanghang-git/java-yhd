package com.yhd.dao;

import com.yhd.pojo.IndentStatus;

import java.sql.Connection;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/28 15:26
 * @Since 1.8
 */
public interface IndentStatusDao {
	/**
	 * 获取全部的订单状态
	 * @param conn 连接
	 * @return List集合
	 */
	List<IndentStatus> getAllList(Connection conn);

	/**
	 * 根据订单状态Id 获取订单状态
	 * @param conn 连接
	 * @param id 订单状态id
	 * @return 订单状态示例
	 */
	IndentStatus getStatusById(Connection conn, int id);

	/**
	 * 添加一个订单状态
	 * @param conn 连接
	 * @param status 状态实例
	 * @return 是否添加成功
	 */
	boolean addStatues(Connection conn, IndentStatus status);

	/**
	 * 修改一个订单状态
	 * @param conn 连接
	 * @param status 状态实例
	 * @return 是否修改成功
	 */
	boolean updateStatues(Connection conn, IndentStatus status);

	/**
	 * 删除一个订单状态
	 * @param conn 连接
	 * @param id 订单Id
	 * @return 是否删除成功
	 */
	boolean removeById(Connection conn, int id);
}
