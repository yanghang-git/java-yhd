package com.yhd.dao.impl;

import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.dao.IndentStatusDao;
import com.yhd.pojo.IndentStatus;
import com.yhd.util.ConnectionFactory;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author 杨航
 * @Date 2020/6/28 19:23
 * @Since 1.8
 */
public class IndentStatusDaoImplTest {
	private Connection conn = ConnectionFactory.INSTANCE.create();
	private IndentStatusDao dao = DaoFlyweightPatternFactory.getInstance().getDaoImpl("indent_status");
	@Test
	public void getAllList() {
		List<IndentStatus> list = dao.getAllList(conn);
		System.out.println(list);
		assertNotNull(list);
	}

	@Test
	public void addStatues() {
		boolean result = dao.addStatues(conn, new IndentStatus(0, "确认订单1"));
		assertTrue(result);
	}

	@Test
	public void updateStatues() {
		boolean result = dao.updateStatues(conn, new IndentStatus(1, "确认订单"));
		assertTrue(result);
	}

	@Test
	public void removeById() {
		boolean result = dao.removeById(conn, 1);
		assertTrue(result);
	}

	@Test
	public void getIndentStatusById() {
		System.out.println(dao.getStatusById(conn, 4));
	}
}
