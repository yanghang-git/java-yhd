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
