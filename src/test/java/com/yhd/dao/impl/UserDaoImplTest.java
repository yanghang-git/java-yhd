package com.yhd.dao.impl;

import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.dao.UserDao;
import com.yhd.pojo.User;
import com.yhd.util.ConnectionFactory;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author 杨航
 * @Date 2020/6/28 20:11
 * @Since 1.8
 */
public class UserDaoImplTest {
	private Connection conn = ConnectionFactory.INSTANCE.create();
	private UserDao dao = DaoFlyweightPatternFactory.getInstance().getDaoImpl("user");
	@Test
	public void getListAll() {
		List<User> list = dao.getListAll(conn);
		System.out.println(list);
		assertNotNull(list);
	}

	@Test
	public void updateUser() {
		boolean result = dao.updateUser(conn, new User("Tom", "tom", null, null, null, null, "13777777777", null, false));
		assertTrue(result);
	}

	@Test
	public void addUser() {
		boolean result = dao.addUser(conn, new User("Jack", "Jack", null, null, null, null, "13777777722", null, null));
		assertTrue(result);
	}

	@Test
	public void removeById() {
		boolean result = dao.removeById(conn, "Jack");
		assertTrue(result);
	}
}
