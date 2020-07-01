package com.yhd.dao.impl;

import com.yhd.dao.ActivitySlideshowDao;
import com.yhd.dao.DaoFlyweightPatternFactory;
import com.yhd.pojo.ActivitySlideshow;
import com.yhd.util.ConnectionFactory;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author 杨航
 * @Date 2020/6/28 11:23
 * @Since 1.8
 */
public class ActivitySlideShowDaoImplTest {

	private Connection conn = ConnectionFactory.INSTANCE.create();
	private ActivitySlideshowDao dao = DaoFlyweightPatternFactory.getInstance().getDaoImpl("activity_slideshow");
	@Test
	public void getAllList() {
		List<ActivitySlideshow> list = dao.getAllList(conn);
		System.out.println(list);
		assertNotNull(list);
	}

	@Test
	public void addSlideShow() {
		boolean result = dao.addSlideshow(conn, new ActivitySlideshow(2, "http://img14.360buyimg.com/img/jfs/t1/104854/16/12622/85577/5e4b7f46E214f9a8f/96ba1ada94c24502.jpg", "进口食品", "null"));
		assertTrue(result);
	}

	@Test
	public void removeById() {
		boolean result = dao.removeById(conn, 1);
		assertTrue(result);
	}

	@Test
	public void updateSlideShow() {
		boolean result = dao.updateSlideshow(conn, new ActivitySlideshow(1, "http://img14.360buyimg.com/img/jfs/t1/104854/16/12622/85577/5e4b7f46E214f9a8f/96ba1ada94c24502.jpg", "进口食品", "empty"));
		assertTrue(result);
	}
}
