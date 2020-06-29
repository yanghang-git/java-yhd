package com.yhd.dao;

import com.yhd.pojo.Indent;

import java.sql.Connection;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/27 17:02
 * @Since 1.8
 */
public interface IndentDao {

	/**
	 * 根据用户的id查询出对应的订单
	 * @param conn 连接
	 * @param userId 用户id
	 * @param goodsId 商品id
	 * @return 订单集合
	 */
	List<Indent> getListByUserId(Connection conn, String userId, String goodsId);

	/**
	 * 根据用户的id查询出对应的订单
	 * @param conn 连接
	 * @param userId 用户id
	 * @return 订单集合
	 */
	List<Indent> getListByUserId(Connection conn, String userId);

	/**
	 * 查询出全部的订单
	 * @param conn 连接
	 * @return 订单集合
	 */
	List<Indent> getListAll(Connection conn);

	/**
	 * 根据订单的id查询出对应的订单
	 * @param conn 连接
	 * @param id id
	 * @return 单个订单
	 */
	Indent getIndentById(Connection conn, String id);

	/**
	 * 修改订单的信息
	 * @param conn 连接
	 * @param indent 订单实例
	 * @return 是否修改成功
	 */
	boolean updateIndent(Connection conn, Indent indent);

	/**
	 * 添加订单的信息
	 * @param conn 连接
	 * @param indent 订单实例
	 * @return 是否添加成功
	 */
	boolean addIndent(Connection conn, Indent indent);

	/**
	 * 删除订单信息
	 * @param conn 连接
	 * @param id 订单ID
	 * @return 是否删除成功
	 */
	boolean removeById(Connection conn, String id);
}
