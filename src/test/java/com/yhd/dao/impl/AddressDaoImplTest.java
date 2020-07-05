package com.yhd.dao.impl;


import com.yhd.dao.AddressDao;
import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.pojo.Address;
import com.yhd.util.ConnectionFactory;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author 杨航
 * @Date 2020/6/28 16:32
 * @Since 1.8
 */

public class AddressDaoImplTest {
	private Connection conn = ConnectionFactory.INSTANCE.create();
	private AddressDao dao = DaoFlyweightPatternFactory.getInstance().getDaoImpl("address");
	@Test
	public void getByUserIdList() {
		List<Address> list = dao.getByUserIdList(conn, "Tom");
		System.out.println(list);
		assertNotNull(list);
	}
	@Test
	public void getAddressDetailById() {
		String address = dao.getAddressDetailById(conn, 1);
		System.out.println(address);
		assertNotNull(address);
	}

	@Test
	public void removeById() {
		boolean result = dao.removeById(conn, 1);
		assertTrue(result);
	}

	@Test
	public void addAddress() {
		boolean result = dao.addAddress(conn, new Address(0, "Tom", "Tom", "13777777777", 1, 5, 8, "第30号"));
		assertTrue(result);
	}

	@Test
	public void updateAddress() {
		boolean result = dao.updateAddress(conn, new Address(1, "Tom", "Tom", "13777776777", 1, 5, 8, "第30号"));
		assertTrue(result);
	}
}
