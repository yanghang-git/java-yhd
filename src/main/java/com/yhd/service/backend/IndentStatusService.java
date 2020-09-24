package com.yhd.service.backend;

import com.yhd.pojo.IndentStatus;

import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/7/1 18:00
 * @Since 1.8
 */
public interface IndentStatusService {
	/**
	 * get all Indent Status
	 * @return gather List
	 */
	List<IndentStatus> getAllList();

	/**
	 * get indentStatus by id
	 * @param id indentStatus id
	 * @return indentStatus
	 */
	IndentStatus getStatusById(int id);

	/**
	 * add one new of Indent Status
	 * @param status status instance
	 * @return true: add success or false : add fail
	 */
	boolean addStatues(IndentStatus status);

	/**
	 * update one of Indent Status
	 * @param status status instance
	 * @return true: update success or false : update fail

	 */
	boolean updateStatues(IndentStatus status);

	/**
	 * remove one of Indent Status
	 * @param id Status of id
	 * @return true: remove success or false : remove fail
	 */
	boolean removeById(int id);
}
