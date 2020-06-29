package com.yhd.dao.impl;

import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.dao.GoodsDao;
import com.yhd.pojo.Goods;
import com.yhd.util.ConnectionFactory;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author 杨航
 * @Date 2020/6/28 15:55
 * @Since 1.8
 */
public class GoodsDaoImplTest {
	private Connection conn = ConnectionFactory.INSTANCE.create();
	private GoodsDao dao = DaoFlyweightPatternFactory.getInstance().getDaoImpl("goods");

	@Test
	public void getListByCatalogId() {
		List<Goods> list = dao.getListByCatalogId(conn, 2);
		assertNotNull(list);
		System.out.println(list);
	}

	@Test
	public void addGoods() {
		boolean result = dao.addGoods(conn, new Goods(0, "name", new BigDecimal(20), "style", "kind", "content", "imagePrimary"
				, "imageDetails", 123, 2));
		assertTrue(result);
	}

	@Test
	public void removeById() {
		boolean result = dao.removeById(conn, 1);
		assertTrue(result);
	}

	@Test
	public void updateGoods() throws SQLException {
		boolean result = dao.updateGoods(conn, new Goods(1, "name", new BigDecimal(20),  "style", "kind", "content", "imagePrimary"
				, "imageDetails", 122, 2));
		assertTrue(result);
	}
}
