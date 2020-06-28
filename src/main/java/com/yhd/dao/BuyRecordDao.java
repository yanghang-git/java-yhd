package com.yhd.dao;

import com.yhd.pojo.BuyRecord;

import java.sql.Connection;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/28 14:30
 * @Since 1.8
 */
public interface BuyRecordDao {
	/**
	 * 根据用户的id查询出对应的订单
	 * @param conn 连接
	 * @param userId 用户id
	 * @return 订单集合
	 */
	List<BuyRecord> getListByUserId(Connection conn, String userId);

	/**
	 * 查询出全部的订单
	 * @param conn 连接
	 * @return 订单集合
	 */
	List<BuyRecord> getListAll(Connection conn);

	/**
	 * 根据订单的id查询出对应的订单
	 * @param conn 连接
	 * @param id id
	 * @return 单个订单
	 */
	BuyRecord getBuyRecordById(Connection conn, String id);

	/**
	 * 修改订单的信息
	 * @param conn 连接
	 * @param buyRecord 订单实例
	 * @return 是否修改成功
	 */
	boolean updateBuyRecord(Connection conn, BuyRecord buyRecord);

	/**
	 * 添加订单的信息
	 * @param conn 连接
	 * @param buyRecord 订单实例
	 * @return 是否添加成功
	 */
	boolean addBuyRecord(Connection conn, BuyRecord buyRecord);

	/**
	 * 删除订单信息
	 * @param conn 连接
	 * @param id 订单ID
	 * @return 是否删除成功
	 */
	boolean removeById(Connection conn, String id);
}
