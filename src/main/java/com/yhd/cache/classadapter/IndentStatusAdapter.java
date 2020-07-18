package com.yhd.cache.classadapter;

import com.yhd.dao.IndentStatusDao;
import com.yhd.pojo.IndentStatus;

import java.sql.Connection;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/7/18 14:45
 * @Since 1.8
 */
public abstract class IndentStatusAdapter implements IndentStatusDao {
	private IndentStatusDao impl;

	public IndentStatusAdapter(IndentStatusDao impl) {
		this.impl = impl;
	}

	/**
	 * 获取全部的订单状态
	 * @param conn 连接
	 * @return List集合
	 */
	@Override
	public List<IndentStatus> getAllList(Connection conn) {
		return impl.getAllList(conn);
	}

	/**
	 * 添加一个订单状态
	 * @param conn   连接
	 * @param status 状态实例
	 * @return 是否添加成功
	 */
	@Override
	public boolean addStatues(Connection conn, IndentStatus status) {
		return impl.addStatues(conn, status);
	}

	/**
	 * 修改一个订单状态
	 * @param conn   连接
	 * @param status 状态实例
	 * @return 是否修改成功
	 */
	@Override
	public boolean updateStatues(Connection conn, IndentStatus status) {
		return impl.updateStatues(conn, status);
	}

	/**
	 * 删除一个订单状态
	 * @param conn 连接
	 * @param id   订单Id
	 * @return 是否删除成功
	 */
	@Override
	public boolean removeById(Connection conn, int id) {
		return impl.removeById(conn, id);
	}
}
