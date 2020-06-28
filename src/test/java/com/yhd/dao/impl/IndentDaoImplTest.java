package com.yhd.dao.impl;

import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.dao.IndentDao;
import com.yhd.pojo.Indent;
import com.yhd.util.ConnectionFactory;
import com.yhd.util.JDBCUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author 杨航
 * @Date 2020/6/28 19:52
 * @Since 1.8
 */
public class IndentDaoImplTest {

	private Connection conn = ConnectionFactory.INSTANCE.create();
	private IndentDao dao = DaoFlyweightPatternFactory.getInstance().getDaoImpl("indent");
	@Test
	public void getListByUserId() {
		List<Indent> list = dao.getListByUserId(conn, "Tom");
		System.out.println(list);
		assertNotNull(list);
	}

	@Test
	public void getListAll() {
		List<Indent> list = dao.getListAll(conn);
		System.out.println(list);
		assertNotNull(list);
	}

	@Test
	public void getIndentById() {
		Indent indent = dao.getIndentById(conn, "2922e1650ff34196bb2faab7262571f9");
		System.out.println(indent);
		assertNotNull(indent);
	}

	@Test
	public void updateIndent() {
		boolean result = dao.updateIndent(conn, new Indent("2922e1650ff34196bb2faab7262571f9", "Tom", 1, 1, 2, "12/12", new BigDecimal(22), 1, new Timestamp(System.currentTimeMillis())));
		assertTrue(result);
	}

	@Test
	public void addIndent() {
		boolean result = dao.addIndent(conn, new Indent(JDBCUtils.randomCreateIndentId(), "Tom", 1, 1, 2, "12/12", new BigDecimal(20), 1, new Timestamp(System.currentTimeMillis())));
		assertTrue(result);
	}

	@Test
	public void removeById() {
		boolean result = dao.removeById(conn, "2922e1650ff34196bb2faab7262571f9");
		assertTrue(result);
	}
}
