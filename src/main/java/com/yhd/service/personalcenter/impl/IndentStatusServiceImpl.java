package com.yhd.service.personalcenter.impl;

import com.yhd.dao.IndentStatusDao;
import com.yhd.pojo.IndentStatus;
import com.yhd.service.personalcenter.IndentStatusService;

import java.sql.Connection;
import java.util.List;

/**
 * @author 杨航
 * @date 2020/9/29 16:04
 * @since 1.8
 */
public class IndentStatusServiceImpl implements IndentStatusService {

	private Connection conn;
	private IndentStatusDao dao;

	public IndentStatusServiceImpl(Connection conn, IndentStatusDao dao) {
		this.conn = conn;
		this.dao = dao;
	}

	/**
	 * get status name by status id
	 * @param statusId status of id
	 * @return status of name
	 */
	@Override
	public String getIndentStatusNameById(int statusId) {
		return dao.getStatusById(conn, statusId).getStatusName();
	}

	/**
	 * get all status
	 * @return status
	 */
	@Override
	public List<IndentStatus> getAllList() {
		return dao.getAllList(conn);
	}
}
