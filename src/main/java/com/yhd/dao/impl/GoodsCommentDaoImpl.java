package com.yhd.dao.impl;

import com.yhd.dao.BaseDao;
import com.yhd.dao.GoodsCommentDao;
import com.yhd.pojo.GoodsComment;

import java.sql.Connection;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/28 18:29
 * @Since 1.8
 */
public class GoodsCommentDaoImpl extends BaseDao<GoodsComment> implements GoodsCommentDao {
	/**
	 * 根据用户账号（id）和商品编号(id）获取对应的评论
	 * @param conn    连接
	 * @param userId  用户Id
	 * @param goodsId 商品Id
	 * @return 对应的评论
	 */
	@Override
	public List<GoodsComment> getByGoodsIdAndUserIdList(Connection conn, String userId, int goodsId) {
		String sql = "select user_id, goods_id, level, comment, order_time from goods_comment where user_id = ? and goods_id = ?";
		return super.getInstances(conn, sql, userId, goodsId);
	}

	/**
	 * 根据商品编号(id）获取对应的评论
	 * @param conn    连接
	 * @param goodsId 商品Id
	 * @return 对应的评论 List集合
	 */
	@Override
	public List<GoodsComment> getByGoodsIdList(Connection conn, int goodsId) {
		String sql = "select user_id, goods_id, level, comment, order_time from goods_comment where goods_id = ?";
		return super.getInstances(conn, sql, goodsId);
	}

	/**
	 * 根据用户账号(id）获取对应的评论
	 * @param conn   连接
	 * @param userId 用户Id
	 * @return 对应的评论 List集合
	 */
	@Override
	public List<GoodsComment> getByUserIdList(Connection conn, String userId) {
		String sql = "select user_id, goods_id, level, comment, order_time from goods_comment where user_id = ?";
		return super.getInstances(conn, sql, userId);
	}

	/**
	 * 添加商品评论
	 * @param conn    连接
	 * @param comment 商品评论的实例
	 * @return 是否添加成功
	 */
	@Override
	public boolean addComment(Connection conn, GoodsComment comment) {
		String sql = "insert into goods_comment(user_id, goods_id, level, comment, order_time) values(?, ?, ?, ?, ?)";
		return super.update(conn, sql, comment.getUserId(), comment.getGoodsId(), comment.getLevel(), comment.getComment(), comment.getOrderTime()) == 1;
	}

	/**
	 * 根据用户Id 和 商品Id 删除评论
	 * @param conn    连接
	 * @param userId  用户Id
	 * @param goodsId 商品Id
	 * @return 是否删除成功
	 */
	@Override
	public boolean removeByUserIdAndGoodsId(Connection conn, String userId, int goodsId) {
		String sql = "delete from goods_comment where user_id = ? and goods_id = ?";
		return super.update(conn, sql, userId, goodsId) == 1;
	}

	/**
	 * 修改商品评论
	 * @param conn    连接
	 * @param userId  用户Id
	 * @param goodsId 商品Id
	 * @param comment 商品评论
	 * @return 是否修改成功
	 */
	@Override
	public boolean updateCommentByUserIdAndGoodsId(Connection conn, String userId, int goodsId, String comment) {
		String sql = "update goods_comment set comment = ? where user_id = ? and goods_id = ?";
		return super.update(conn, sql, comment, userId, goodsId) == 1;
	}


}
