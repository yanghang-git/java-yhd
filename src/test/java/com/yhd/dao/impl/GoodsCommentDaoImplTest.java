package com.yhd.dao.impl;

import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.dao.GoodsCommentDao;
import com.yhd.pojo.GoodsComment;
import com.yhd.util.ConnectionFactory;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author 杨航
 * @Date 2020/6/28 18:46
 * @Since 1.8
 */
public class GoodsCommentDaoImplTest {

	private Connection conn = ConnectionFactory.INSTANCE.create();
	private GoodsCommentDao dao = DaoFlyweightPatternFactory.getInstance().getDaoImpl("goods_comment");

	@Test
	public void getByGoodsIdAndUserIdList() {
		List<GoodsComment> list = dao.getByGoodsIdAndUserIdList(conn, "Tom", 1);
		assertNotNull(list);
		System.out.println(list);
	}

	@Test
	public void getByGoodsIdList() {
		List<GoodsComment> list = dao.getByGoodsIdList(conn, 1);
		assertNotNull(list);
		System.out.println(list);
	}

	@Test
	public void getByUserIdList() {
		List<GoodsComment> list = dao.getByUserIdList(conn, "Tom");
		assertNotNull(list);
		System.out.println(list);
	}

	@Test
	public void addComment() {
		boolean result = dao.addComment(conn, new GoodsComment("Tom", 1, 5, null, new Timestamp(System.currentTimeMillis())));
		assertTrue(result);
	}

	@Test
	public void removeByUserIdAndGoodsId() {
		boolean result = dao.removeByUserIdAndGoodsId(conn, "Tom", 1);
		assertTrue(result);
	}

	@Test
	public void updateCommentByUserIdAndGoodsId() {
		boolean result = dao.updateCommentByUserIdAndGoodsId(conn,"Tom", 1, "goods");
		assertTrue(result);
	}
}
