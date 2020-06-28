package com.yhd.dao.impl;

import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.dao.GoodsCatalogDao;
import com.yhd.pojo.GoodsCatalog;
import com.yhd.util.ConnectionFactory;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author 杨航
 * @Date 2020/6/28 18:18
 * @Since 1.8
 */
public class GoodsCatalogDaoImplTest {
	private Connection conn = ConnectionFactory.INSTANCE.create();
	private GoodsCatalogDao dao = DaoFlyweightPatternFactory.getInstance().getDaoImpl("goods_catalog");

	@Test
	public void getListByUpId() {
		List<GoodsCatalog> list = dao.getListByUpId(conn, 0);
		assertNotNull(list);
		System.out.println(list);
	}

	@Test
	public void removeById() {
		boolean result = dao.removeById(conn, 3);
		assertTrue(result);
	}

	@Test
	public void addGoodsCatalog() {
		boolean result = dao.addGoodsCatalog(conn, new GoodsCatalog(0, "母婴/玩具/童装", 0));
		assertTrue(result);
	}

	@Test
	public void updateCatalog() {
		boolean result = dao.updateCatalog(conn, new GoodsCatalog(2, "母婴/玩具/童装 ", 1));
		assertTrue(result);
	}
}
