package com.yhd.service.backend;

import com.yhd.pojo.ActivitySlideshow;

import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/29 12:04
 * @Since 1.8
 */
public interface ActivitySlideshowService {
	/**
	 * get All ActivitySlideshow instance result list gather
	 * @return ActivitySlideshow instance all
	 */
	List<ActivitySlideshow> getAllList();

	/**
	 * add ActivitySlideshow arrive record
	 * @param slideshow instance
	 * @return true: success or false: fail
	 */
	boolean addSlideshow(ActivitySlideshow slideshow);

	/**
	 * remove ActivitySlideshow By id
	 * @param id ActivitySlideShow of id
	 * @return true: success or false: fail
	 */
	boolean removeById(int id);

	/**
	 * update ActivitySlideshow by id
	 * @param slideshow ActivitySlideShow instance
	 * @return true: success or false: fail
	 */
	boolean updateById(ActivitySlideshow slideshow);
}
