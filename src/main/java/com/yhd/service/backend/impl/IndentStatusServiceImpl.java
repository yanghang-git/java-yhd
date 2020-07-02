package com.yhd.service.backend.impl;

import com.yhd.dao.IndentStatusDao;
import com.yhd.pojo.IndentStatus;
import com.yhd.service.backend.IndentStatusService;

import java.sql.Connection;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/29 13:42
 * @Since 1.8
 */
public class IndentStatusServiceImpl implements IndentStatusService {

	private Connection conn;
	private IndentStatusDao dao;

	public IndentStatusServiceImpl(Connection conn, IndentStatusDao dao) {
		this.conn = conn;
		this.dao = dao;
	}

	/**
	 * get all Indent Status
	 * @return gather List
	 */
	@Override
	public List<IndentStatus> getAllList() {
		return dao.getAllList(conn);
	}

	/**
	 * add one new of Indent Status
	 * @param status status instance
	 * @return true: add success or false : add fail
	 */
	@Override
	public boolean addStatues(IndentStatus status) {
		return dao.addStatues(conn, status);
	}

	/**
	 * update one of Indent Status
	 * @param status status instance
	 * @return true: update success or false : update fail
	 */
	@Override
	public boolean updateStatues(IndentStatus status) {
		return dao.updateStatues(conn, status);
	}

	/**
	 * remove one of Indent Status
	 * @param id Status of id
	 * @return true: remove success or false : remove fail
	 */
	@Override
	public boolean removeById(int id) {
		return dao.removeById(conn, id);
	}
}
