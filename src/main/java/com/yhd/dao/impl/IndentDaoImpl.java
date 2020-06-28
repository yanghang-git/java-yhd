package com.yhd.dao.impl;

import com.yhd.dao.BaseDao;
import com.yhd.dao.IndentDao;
import com.yhd.pojo.Indent;

import java.sql.Connection;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/28 19:34
 * @Since 1.8
 */
public class IndentDaoImpl extends BaseDao<Indent> implements IndentDao {
	/**
	 * 根据用户的id查询出对应的订单
	 * @param conn   连接
	 * @param userId 用户id
	 * @return 订单集合
	 */
	@Override
	public List<Indent> getListByUserId(Connection conn, String userId) {
		String sql = "select id, user_id, goods_id, buy_number, type, total_prices, address_id,order_time from indent where user_id = ?";
		return super.getInstances(conn, sql, userId);
	}

	/**
	 * 查询出全部的订单
	 * @param conn 连接
	 * @return 订单集合
	 */
	@Override
	public List<Indent> getListAll(Connection conn) {
		String sql = "select id, user_id, goods_id, buy_number, type, total_prices, address_id,order_time from indent";
		return super.getInstances(conn, sql);
	}

	/**
	 * 根据订单的id查询出对应的订单
	 * @param conn 连接
	 * @param id   id
	 * @return 单个订单
	 */
	@Override
	public Indent getIndentById(Connection conn, String id) {
		String sql = "select id, user_id, goods_id, buy_number, type, total_prices, address_id,order_time from indent where id = ?";
		return super.getInstance(conn, sql, id);
	}

	/**
	 * 修改订单的信息
	 * @param conn   连接
	 * @param indent 订单实例
	 * @return 是否修改成功
	 */
	@Override
	public boolean updateIndent(Connection conn, Indent indent) {
		String sql = "update indent set user_id = ?, goods_id = ?, status_id = ?, buy_number = ?, type = ?, total_prices = ?, address_id = ?, order_time = ? where id = ?";
		return super.update(conn, sql, indent.getUserId(), indent.getGoodsId(), indent.getStatusId()
				, indent.getBuyNumber(), indent.getType(), indent.getTotalPrices(), indent.getAddressId()
				, indent.getOrderTime(), indent.getId()) == 1;
	}

	/**
	 * 添加订单的信息
	 * @param conn   连接
	 * @param indent 订单实例
	 * @return 是否添加成功
	 */
	@Override
	public boolean addIndent(Connection conn, Indent indent) {
		String sql = "insert into indent(id, user_id, goods_id, status_id, buy_number, type, total_prices, address_id, order_time) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		return super.update(conn, sql, indent.getId(), indent.getUserId(), indent.getGoodsId(), indent.getStatusId()
			, indent.getBuyNumber(), indent.getType(), indent.getTotalPrices(), indent.getAddressId(), indent.getOrderTime()) == 1;
	}

	/**
	 * 删除订单信息
	 * @param conn 连接
	 * @param id   订单ID
	 * @return 是否删除成功
	 */
	@Override
	public boolean removeById(Connection conn, String id) {
		String sql = "delete from indent where id = ?";
		return super.update(conn, sql, id) == 1;
	}
}
