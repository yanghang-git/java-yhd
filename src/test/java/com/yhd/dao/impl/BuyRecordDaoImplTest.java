package com.yhd.dao.impl;

import com.yhd.dao.BuyRecordDao;
import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.pojo.BuyRecord;
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
 * @Date 2020/6/28 17:41
 * @Since 1.8
 */
public class BuyRecordDaoImplTest {
	private Connection conn = ConnectionFactory.INSTANCE.create();
	private BuyRecordDao dao = DaoFlyweightPatternFactory.getInstance().getDaoImpl("buy_record");
	@Test
	public void getListByUserId() {
		List<BuyRecord> list = dao.getListByUserId(conn, "Tom");
		assertNotNull(list);
		System.out.println(list);
	}

	@Test
	public void getListAll() {
		List<BuyRecord> list = dao.getListAll(conn);
		assertNotNull(list);
		System.out.println(list);
	}

	@Test
	public void getBuyRecordById() {
		BuyRecord buyRecordById = dao.getBuyRecordById(conn, "966ff83e2fda4399a578700970bd0086");
		assertNotNull(buyRecordById);
		System.out.println(buyRecordById);
	}

	@Test
	public void updateBuyRecord() {
		boolean result = dao.updateBuyRecord(conn, new BuyRecord("966ff83e2fda4399a578700970bd0086", "Tom", "大呲花"
				, 3, "3个;5k", new BigDecimal(30), "匿名", "13777777777"
				, "河北省", new Timestamp(System.currentTimeMillis())));
		assertTrue(result);
	}

	@Test
	public void addBuyRecord() {
		boolean result = dao.addBuyRecord(conn, new BuyRecord(JDBCUtils.randomCreateIndentId(), "Tom", "大呲花"
				, 2, "3个;5k", new BigDecimal(20), "匿名", "13777777777"
				, "河北省", new Timestamp(System.currentTimeMillis())));
		assertTrue(result);
	}

	@Test
	public void removeById() {
		boolean result = dao.removeById(conn, "");
		assertTrue(result);
	}
}
