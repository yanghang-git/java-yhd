package com.yhd.dao.impl;

import com.yhd.dao.BaseDao;
import com.yhd.dao.BuyRecordDao;
import com.yhd.pojo.BuyRecord;

import java.sql.Connection;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/28 17:27
 * @Since 1.8
 */
public class BuyRecordDaoImpl extends BaseDao<BuyRecord> implements BuyRecordDao {

	/**
	 * 根据用户的id查询出对应的订单
	 * @param conn   连接
	 * @param userId 用户id
	 * @return 订单集合
	 */
	@Override
	public List<BuyRecord> getListByUserId(Connection conn, String userId) {
		String sql = "select id, user_id, goods_name, buy_number, type, total_prices, address_name, address_phone, address, take_time from buy_record where user_id = ?";
		return super.getInstances(conn, sql, userId);
	}

	/**
	 * 查询出全部的订单
	 * @param conn 连接
	 * @return 订单集合
	 */
	@Override
	public List<BuyRecord> getListAll(Connection conn) {
		String sql = "select id, user_id, goods_name, buy_number, type, total_prices, address_name, address_phone, address, take_time from buy_record";
		return super.getInstances(conn, sql);
	}

	/**
	 * 根据订单的id查询出对应的订单
	 * @param conn 连接
	 * @param id   id
	 * @return 单个订单
	 */
	@Override
	public BuyRecord getBuyRecordById(Connection conn, String id) {
		String sql = "select id, user_id, goods_name, buy_number, type, total_prices, address_name, address_phone, address, take_time from buy_record where id = ?";
		return super.getInstance(conn, sql, id);
	}

	/**
	 * 修改订单的信息
	 * @param conn      连接
	 * @param buyRecord 订单实例
	 * @return 是否修改成功
	 */
	@Override
	public boolean updateBuyRecord(Connection conn, BuyRecord buyRecord) {
		String sql = "update buy_record set user_id = ?, goods_name = ?, buy_number = ?, type = ?, total_prices = ?, address_name = ?, address_phone = ?, address = ?, take_time = ? where id = ?";
		return super.update(conn, sql, buyRecord.getUserId(), buyRecord.getGoodsName(), buyRecord.getBuyNumber()
				, buyRecord.getType(), buyRecord.getTotalPrices(), buyRecord.getAddressName(), buyRecord.getAddressPhone()
				, buyRecord.getAddress(), buyRecord.getTakeTime(), buyRecord.getId()) == 1;
	}

	/**
	 * 添加订单的信息
	 * @param conn      连接
	 * @param buyRecord 订单实例
	 * @return 是否添加成功
	 */
	@Override
	public boolean addBuyRecord(Connection conn, BuyRecord buyRecord) {
		String sql = "insert into buy_record(id, user_id, goods_name, buy_number, type, total_prices, address_name, address_phone, address, take_time) values(?, ?, ? , ?, ?, ?, ?, ?, ?, ?)";
		return super.update(conn, sql, buyRecord.getId(), buyRecord.getUserId(), buyRecord.getGoodsName(), buyRecord.getBuyNumber()
				, buyRecord.getType(), buyRecord.getTotalPrices(), buyRecord.getAddressName(), buyRecord.getAddressPhone()
				, buyRecord.getAddress(), buyRecord.getTakeTime()) == 1;
	}

	/**
	 * 删除订单信息
	 * @param conn 连接
	 * @param id   订单ID
	 * @return 是否删除成功
	 */
	@Override
	public boolean removeById(Connection conn, String id) {
		String sql = "delete from buy_record where id = ?";
		return super.update(conn, sql, id) == 1;
	}
}
