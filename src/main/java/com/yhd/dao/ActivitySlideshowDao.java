package com.yhd.dao;

import com.yhd.pojo.ActivitySlideshow;

import java.sql.Connection;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/27 17:51
 * @Since 1.8
 */
public interface ActivitySlideshowDao {
	/**
	 * 获取全部轮播图对象
	 * @param conn 连接
	 * @return List集合
	 */
	List<ActivitySlideshow> getAllList(Connection conn);

	/**
	 * 添加一个记录
	 * @param conn 连接
	 * @param slideShow 轮播图单个实例
	 * @return 是否添加成功
	 */
	boolean addSlideshow(Connection conn, ActivitySlideshow slideShow);

	/**
	 * 根据序号删除
	 * @param conn 连接
	 * @param id 序号
	 * @return 是否删除成功
	 */
	boolean removeById(Connection conn, int id);

	/**
	 * 修改记录
	 * @param conn 连接
	 * @param slideShow 轮播图单个实例
	 * @return 是否修改成功
	 */
	boolean updateSlideshow(Connection conn, ActivitySlideshow slideShow);
}
