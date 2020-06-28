package com.yhd.dao.impl;

import com.yhd.dao.AdminDao;
import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.pojo.Admin;
import com.yhd.util.ConnectionFactory;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertNotNull;

/**
 * @Author 杨航
 * @Date 2020/6/27 15:20
 * @Since 1.8
 */
public class AdminDaoImplTest {

	@Test
	public void getAdminByIdAndPassword() {
		Connection conn = ConnectionFactory.INSTANCE.create();
		AdminDao dao = DaoFlyweightPatternFactory.getInstance().getDaoImpl("admin");
		Admin admin = dao.getAdminByIdAndPassword(conn, "Yoyo", "yoyo123");
		assertNotNull(admin);
	}

}
