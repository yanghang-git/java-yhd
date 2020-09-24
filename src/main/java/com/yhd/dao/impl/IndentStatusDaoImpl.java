package com.yhd.dao.impl;

import com.yhd.dao.BaseDao;
import com.yhd.dao.IndentStatusDao;
import com.yhd.pojo.IndentStatus;

import java.sql.Connection;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/28 19:15
 * @Since 1.8
 */
public class IndentStatusDaoImpl extends BaseDao<IndentStatus> implements IndentStatusDao {
	/**
	 * 获取全部的订单状态
	 * @param conn 连接
	 * @return List集合
	 */
	@Override
	public List<IndentStatus> getAllList(Connection conn) {
		String sql = "select id, status_name from indent_status";
		return super.getInstances(conn, sql);
	}

	/**
	 * 根据订单状态Id 获取订单状态
	 * @param conn 连接
	 * @param id   订单状态id
	 * @return 订单状态示例
	 */
	@Override
	public IndentStatus getStatusById(Connection conn, int id) {
		String sql = "select id, status_name from indent_status where id = ?";
		return super.getInstance(conn, sql, id);
	}

	/**
	 * 添加一个订单状态
	 * @param conn   连接
	 * @param status 状态实例
	 * @return 是否添加成功
	 */
	@Override
	public boolean addStatues(Connection conn, IndentStatus status) {
		String sql = "insert into indent_status(status_name) values(?)";
		return super.update(conn, sql, status.getStatusName()) == 1;
	}

	/**
	 * 修改一个订单状态
	 * @param conn   连接
	 * @param status 状态实例
	 * @return 是否修改成功
	 */
	@Override
	public boolean updateStatues(Connection conn, IndentStatus status) {
		String sql = "update indent_status set status_name = ? where id = ?";
		return super.update(conn, sql, status.getStatusName(), status.getId()) == 1;
	}

	/**
	 * 删除一个订单状态
	 *
	 * @param conn 连接
	 * @param id   订单Id
	 * @return 是否删除成功
	 */
	@Override
	public boolean removeById(Connection conn, int id) {
		String sql = "delete from indent_status where id = ?";
		return super.update(conn, sql, id) == 1;
	}
}
