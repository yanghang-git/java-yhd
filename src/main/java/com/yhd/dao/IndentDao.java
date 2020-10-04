package com.yhd.dao;

import com.yhd.pojo.Indent;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/27 17:02
 * @Since 1.8
 */
public interface IndentDao {

	/**
	 * 根据订单的状态、商品的Id、用户的id、自身的id查询出对应的订单
	 * @param conn 连接
	 * @param statusId 状态id
	 * @param goodsId 商品id
	 * @param userId 用户id
	 * @param id 自己的id
	 * @param pageSize 第几页
	 * @param pageCount 一页显示多少
	 * @return 订单集合
	 */
	List<Indent> getListByStatusAndGoodsIdAndUserIdAndId(Connection conn, int statusId, List<Integer> goodsId, String userId, String id, int pageSize, int pageCount);

	/**
	 * 根据订单的状态、商品的Id、用户的id、自身的id查询出对应的订单数量
	 * @param conn 连接
	 * @param statusId 状态id
	 * @param goodsId 商品id
	 * @param userId 用户id
	 * @param id 自己的id
	 * @return 订单集合
	 */
	long getCountByStatusAndGoodsIdAndUserIdAndId(Connection conn, int statusId, List<Integer> goodsId, String userId, String id);

	/**
	 * 根据订单编号获取订单全部信息
	 * @param conn 连接
	 * @param id 订单编号
	 * @return 订单
	 */
	Indent getIndentById(Connection conn, String id);

	/**
	 * 根据用户姓名查询订单
	 * @param conn 连接
	 * @param username 用户的姓名
	 * @return 订单List
	 */
	List<Indent> getIndentByUsername(Connection conn, String username);

	/**
	 * 修改订单的类型、数量、价格。 根据Id
	 * @param conn 连接
	 * @param goodsType 商品类型
	 * @param buyNumber 数量
	 * @param totalPrice 价格
	 * @param indentId id
	 * @return 是否修改成功
	 */
	boolean updateIndentGoodsTypeAndBuyNumberAndTotalPriceById(Connection conn, String goodsType, int buyNumber, BigDecimal totalPrice, String indentId);

	/**
	 * 根据订单编号修改订单状态
	 * @param conn 连接
	 * @param statusId 订单状态Id
	 * @param id 订单id
	 * @return
	 */
	boolean updateIndentStatusById(Connection conn, int statusId, String id);

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

	/**
	 * 根据订单状态查询信息
	 * @param conn 连接
	 * @param statusId 订单状态id
	 * @param username 用户名称
	 * @return 订单信息
	 */
	List<Indent> getIndentByStatusIdAndUsername(Connection conn, int statusId, String username);

	/**
	 * 修改订单状态
	 * @param conn 连接
	 * @param indentId 订单Id
	 * @param statusId 状态id
	 * @return 是否修改成功
	 */
	boolean alterIndentStatusById(Connection conn, String indentId, int statusId);

	Indent getRecentlyIndentByUserName(Connection conn, String username);

}
