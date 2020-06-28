package com.yhd.dao.impl;

import com.yhd.dao.ActivitySlideshowDao;
import com.yhd.dao.BaseDao;
import com.yhd.pojo.ActivitySlideshow;

import java.sql.Connection;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/28 11:07
 * @Since 1.8
 */
public class ActivitySlideshowDaoImpl extends BaseDao<ActivitySlideshow> implements ActivitySlideshowDao {

	/**
	 * 获取全部轮播图对象
	 * @param conn 连接
	 * @return List集合
	 */
	@Override
	public List<ActivitySlideshow> getAllList(Connection conn) {
		String sql = "select id, image, font, url from activity_slideshow";
		return super.getInstances(conn, sql);
	}

	/**
	 * 添加一个记录
	 * @param conn      连接
	 * @param slideShow 轮播图单个实例
	 * @return 是否添加成功
	 */
	@Override
	public boolean addSlideshow(Connection conn, ActivitySlideshow slideShow) {
		String sql = "insert into activity_slideshow(id, image, font, url) values(?, ?, ?, ?)";
		return super.update(conn, sql, slideShow.getId(), slideShow.getImage(), slideShow.getFont(), slideShow.getUrl()) == 1;
	}

	/**
	 * 根据序号删除
	 * @param conn 连接
	 * @param id   序号
	 * @return 是否删除成功
	 */
	@Override
	public boolean removeById(Connection conn, int id) {
		String sql = "delete from activity_slideshow where id = ?";
		return super.update(conn, sql, id) == 1;
	}

	/**
	 * 修改记录
	 * @param conn      连接
	 * @param slideShow 轮播图单个实例
	 * @return 是否修改成功
	 */
	@Override
	public boolean updateSlideshow(Connection conn, ActivitySlideshow slideShow) {
		String sql = "update activity_slideshow set image = ?, font = ?, url = ? where id = ?";
		return super.update(conn, sql, slideShow.getImage(), slideShow.getFont(), slideShow.getUrl(), slideShow.getId()) == 1;
	}
}
