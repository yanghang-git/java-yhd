package com.yhd.service.personalcenter.impl;

import com.yhd.dao.IndentDao;
import com.yhd.pojo.Indent;
import com.yhd.service.personalcenter.IndentService;

import java.sql.Connection;
import java.util.List;

/**
 * @author 杨航
 * @date 2020/9/28 17:53
 * @since 1.8
 */
public class IndentServiceImpl implements IndentService {
	private Connection conn;
	private IndentDao dao;

	public IndentServiceImpl(Connection conn, IndentDao dao) {
		this.conn = conn;
		this.dao = dao;
	}

	/**
	 * select indent paging
	 * @param username  user of name
	 * @return paging of values indent
	 */
	@Override
	public List<Indent> getIndentByUserIdList(String username) {
		return dao.getIndentByUsername(conn, username);
	}

	/**
	 * query indent by status of id and user of name
	 * @param statusId  indent status of id
	 * @param username user of id
	 * @return paging of values indent
	 */
	@Override
	public List<Indent> getAllByStatusIdAndUsername(int statusId, String username) {
		return dao.getIndentByStatusIdAndUsername(conn, statusId, username);
	}

	/**
	 * harvest goods
	 * @param id indent of id
	 * @return is harvest goods succeed
	 */
	@Override
	public boolean harvestGoods(String id) {
		return dao.updateIndentStatusById(conn, 0, id);
	}

	/**
	 * remove Indent
	 * 		ps:	status must is not payment or already succeed
	 * @param id indent of id
	 * @return is remove succeed
	 */
	@Override
	public boolean removeIndentById(String id) {
		return dao.removeById(conn, id);
	}

	/**
	 * get recently indent by user name
	 * @param username user of name
	 * @return indent
	 */
	@Override
	public Indent getRecentlyIndentByUserName(String username) {
		return dao.getRecentlyIndentByUserName(conn, username);
	}

	/**
	 * get indent by indent id
	 * @param indentId indent of id
	 * @return indent instance
	 */
	@Override
	public Indent getIndentById(String indentId) {
		return dao.getIndentById(conn, indentId);
	}


}
