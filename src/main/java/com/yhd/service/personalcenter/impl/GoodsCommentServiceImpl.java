package com.yhd.service.personalcenter.impl;

import com.yhd.dao.GoodsCommentDao;
import com.yhd.pojo.GoodsComment;
import com.yhd.service.personalcenter.GoodsCommentService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 杨航
 * @date 2020/9/28 17:48
 * @since 1.8
 */
public class GoodsCommentServiceImpl implements GoodsCommentService {
	private Connection conn;
	private GoodsCommentDao dao;

	public GoodsCommentServiceImpl(Connection conn, GoodsCommentDao dao) {
		this.conn = conn;
		this.dao = dao;
	}


	/**
	 * get all goodsComment
	 * @return goodsComment
	 */
	@Override
	public List<GoodsComment> getCommentByUsernameList(String username) {
		return dao.getByUserIdList(conn, username);
	}

	/**
	 * give goods comment
	 * @param comment comment value
	 * @return is comment success
	 */
	@Override
	public boolean addGoodsComment(String indentId, GoodsComment comment) {
		boolean flag = false;
		try {
			conn.setAutoCommit(false);
			flag = dao.addComment(conn, comment);
			PreparedStatement ps = conn.prepareStatement("update indent set status_id = 3 where id = ?");
			ps.setObject(1, indentId);
			int executeUpdate = ps.executeUpdate();
			if (executeUpdate != 1) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				return false;
			}
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
}
