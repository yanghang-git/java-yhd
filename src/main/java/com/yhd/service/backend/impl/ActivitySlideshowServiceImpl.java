package com.yhd.service.backend.impl;

import com.yhd.dao.ActivitySlideshowDao;
import com.yhd.pojo.ActivitySlideshow;
import com.yhd.service.backend.ActivitySlideshowService;
import com.yhd.util.ConnectionFactory;
import com.yhd.util.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/7/1 15:09
 * @Since 1.8
 */
public class ActivitySlideshowServiceImpl implements ActivitySlideshowService {
	private Connection conn;
	private ActivitySlideshowDao dao;

	public ActivitySlideshowServiceImpl(Connection conn, ActivitySlideshowDao dao) {
		this.conn = conn;
		this.dao = dao;
	}

	/**
	 * get All ActivitySlideshow instance result list gather
	 * @return ActivitySlideshow instance all
	 */
	@Override
	public List<ActivitySlideshow> getAllList() {
		return dao.getAllList(conn);
	}

	/**
	 * add ActivitySlideshow arrive record
	 * @param slideshow instance
	 * @return true: success or false: fail
	 */
	@Override
	public boolean addSlideshow(ActivitySlideshow slideshow) {
		return dao.addSlideshow(conn, slideshow);
	}

	/**
	 * remove ActivitySlideshow By id
	 * @param id ActivitySlideShow of id
	 * @return true: success or false: fail
	 */
	@Override
	public boolean removeById(int id) {
		return dao.removeById(conn, id);
	}

	/**
	 * update ActivitySlideshow by id
	 * @param slideshow ActivitySlideShow instance
	 * @return true: success or false: fail
	 */
	@Override
	public boolean updateById(ActivitySlideshow slideshow) {
		return dao.updateSlideshow(conn, slideshow);
	}
}
