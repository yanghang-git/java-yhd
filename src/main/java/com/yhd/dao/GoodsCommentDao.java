package com.yhd.dao;

import com.yhd.pojo.GoodsComment;

import java.sql.Connection;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/28 15:01
 * @Since 1.8
 */
public interface GoodsCommentDao {
	/**
	 * 根据用户账号（id）和商品编号(id）获取对应的评论
	 * @param conn 连接
	 * @param userId 用户Id
	 * @param goodsId 商品Id
	 * @return 对应的评论
	 */
	List<GoodsComment> getByGoodsIdAndUserIdList(Connection conn, String userId, int goodsId);

	/**
	 * 根据商品编号(id）获取对应的评论
	 * @param conn 连接
	 * @param goodsId 商品Id
	 * @return 对应的评论 List集合
	 */
	List<GoodsComment> getByGoodsIdList(Connection conn, int goodsId);

	/**
	 * 根据用户账号(id）获取对应的评论
	 * @param conn 连接
	 * @param userId 用户Id
	 * @return 对应的评论 List集合
	 */
	List<GoodsComment> getByUserIdList(Connection conn, String userId);

	/**
	 * 添加商品评论
	 * @param conn 连接
	 * @param comment 商品评论的实例
	 * @return 是否添加成功
	 */
	boolean addComment(Connection conn, GoodsComment comment);

	/**
	 * 根据用户Id 和 商品Id 删除评论
	 * @param conn 连接
	 * @param userId 用户Id
	 * @param goodsId 商品Id
	 * @return 是否删除成功
	 */
	boolean removeByUserIdAndGoodsId(Connection conn, String userId, int goodsId);
	/**
	 * 修改商品评论
	 * @param conn 连接
	 * @param userId 用户Id
	 * @param goodsId 商品Id
	 * @param comment 商品评论
	 * @return 是否修改成功
	 */
	boolean updateCommentByUserIdAndGoodsId(Connection conn, String userId, int goodsId, String comment);
}
