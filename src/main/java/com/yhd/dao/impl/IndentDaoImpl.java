package com.yhd.dao.impl;

import com.mysql.jdbc.StringUtils;
import com.yhd.dao.BaseDao;
import com.yhd.dao.IndentDao;
import com.yhd.pojo.Indent;
import com.yhd.util.JDBCUtils;

import java.math.BigDecimal;
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
	 * @param conn    连接
	 * @param goodsId 商品id
	 * @param userId  用户id
	 * @param id      自己的id
	 * @return 订单集合
	 */
	@Override
	public List<Indent> getListByStatusAndGoodsIdAndUserIdAndId(Connection conn, int statusId, List<Integer> goodsId, String userId, String id, int pageSize, int pageCount) {
		StringBuilder sql = new StringBuilder("select id, user_id, goods_id, status_id, buy_number, type, total_prices, address_id,order_time from indent where 1 = 1");
		if (!StringUtils.isNullOrEmpty(id)) {
			sql.append(" and id = '").append(id).append("'");
		}
		if (!StringUtils.isNullOrEmpty(userId)) {
			sql.append(" and user_id = '").append(userId).append("'");
		}
		if (statusId > 0) {
			sql.append(" and status_id = ").append(statusId);
		}
		if (goodsId != null && goodsId.size() > 0) {
			sql.append(" and goods_id in( ");
			for (int i = 0; i < goodsId.size(); i++) {
				sql.append(goodsId.get(i)).append(i == goodsId.size() -1 ? "" :",");
			}
			sql.append(")");
		}
		sql.append("limit ?, ?");
		return super.getInstances(conn, sql.toString(), (pageSize - 1) * pageCount, pageCount);
	}

	@Override
	public long getCountByStatusAndGoodsIdAndUserIdAndId(Connection conn, int statusId, List<Integer> goodsId, String userId, String id) {
		StringBuilder sql = new StringBuilder("select count(*) from indent where 1 = 1");
		if (!StringUtils.isNullOrEmpty(id)) {
			sql.append(" and id = '").append(id).append("'");
		}
		if (!StringUtils.isNullOrEmpty(userId)) {
			sql.append(" and user_id = '").append(userId).append("'");
		}
		if (statusId > 0) {
			sql.append(" and status_id = ").append(statusId);
		}
		if (goodsId != null && goodsId.size() > 0) {
			sql.append(" and goods_id in( ");
			for (int i = 0; i < goodsId.size(); i++) {
				sql.append(goodsId.get(i)).append(i == goodsId.size() -1 ? "" :",");
			}
			sql.append(")");
		}
		return super.getSimple(conn, sql.toString());
	}

	/**
	 * 根据订单编号获取订单全部信息
	 * @param conn 连接
	 * @param id   订单编号
	 * @return 订单
	 */
	@Override
	public Indent getIndentById(Connection conn, String id) {
		String sql = "select id, user_id, goods_id, status_id, buy_number, type, total_prices, address_id,order_time from indent where id = ?";
		return getInstance(conn, sql, id);
	}

	/**
	 * 修改订单的类型、数量、价格、收货地址。 根据Id
	 * @param conn       连接
	 * @param goodsType  商品类型
	 * @param buyNumber  数量
	 * @param totalPrice 价格
	 * @param indentId   id
	 * @return 是否修改成功
	 */
	@Override
	public boolean updateIndentGoodsTypeAndBuyNumberAndTotalPriceById(Connection conn, String goodsType, int buyNumber, BigDecimal totalPrice, String indentId) {
		String sql = "update indent set buy_number = ?, type = ?, total_prices = ? where id = ?";
		return super.update(conn, sql, buyNumber, goodsType, totalPrice, indentId) == 1;
	}

	/**
	 * 根据订单编号修改订单状态
	 *
	 * @param conn     连接
	 * @param statusId 订单状态Id
	 * @param id       订单id
	 * @return
	 */
	@Override
	public boolean updateIndentStatusById(Connection conn, int statusId, String id) {
		String sql = "update indent set status_id = ? where id = ?";
		return super.update(conn, sql, statusId, id) == 1;
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
