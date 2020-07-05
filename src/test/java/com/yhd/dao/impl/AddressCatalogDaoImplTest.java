package com.yhd.dao.impl;

import com.yhd.dao.AddressCatalogDao;
import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.pojo.AddressCatalog;
import com.yhd.util.ConnectionFactory;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author 杨航
 * @Date 2020/6/28 11:40
 * @Since 1.8
 */
public class AddressCatalogDaoImplTest {
	private Connection conn = ConnectionFactory.INSTANCE.create();
	private AddressCatalogDao dao = DaoFlyweightPatternFactory.getInstance().getDaoImpl("address_catalog");

	@Test
	public void getListByUpId() {
		List<AddressCatalog> list = dao.getListByUpId(conn, 0);
		System.out.println(list);
		assertNotNull(list);
	}

	@Test
	public void getUpIdByCatalogName() {
		List<AddressCatalog> list = dao.getUpIdByCatalogName(conn, "区");
		System.out.println(list);
		assertNotNull(list);
	}

	@Test
	public void getCatalogById() {
		AddressCatalog catalog = dao.getCatalogById(conn, 5);
		System.out.println(catalog);
		assertNotNull(catalog);
	}

	@Test
	public void removeById() {
		boolean result = dao.removeById(conn, 1);
	}



	@Test
	public void addAddressCatalog() {
		boolean result = dao.addAddressCatalog(conn, new AddressCatalog(0, "河北", 0));
		assertTrue(result);
	}

	@Test
	public void updateCatalog() {
		boolean result = dao.updateCatalogNameById(conn,1, "河北");

	}
}
