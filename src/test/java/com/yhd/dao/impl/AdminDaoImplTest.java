package com.yhd.dao.impl;

import com.yhd.dao.AdminDao;
import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.pojo.Admin;
import com.yhd.util.ConnectionFactory;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * @Author 杨航
 * @Date 2020/6/27 15:20
 * @Since 1.8
 */
public class AdminDaoImplTest {

	private	Connection conn = ConnectionFactory.INSTANCE.create();
	private	AdminDao dao = DaoFlyweightPatternFactory.getInstance().getDaoImpl("admin");
	@Test
	public void getAdminByIdAndPassword() {
		Admin admin = dao.getAdminByIdAndPassword(conn, "Yoyo", "yoyo123");
		System.out.println(admin);
		assertNotNull(admin);
	}


	@Test
	public void addAdmin() {
		boolean flag = dao.addAdmin(conn, new Admin("Tom1", "tom123", false, true, true, true, true, true));
		assertTrue(flag);
	}

	@Test
	public void containsId() {
		boolean flag = dao.containsId(conn, "Yoyo");
		assertTrue(flag);
	}
}
