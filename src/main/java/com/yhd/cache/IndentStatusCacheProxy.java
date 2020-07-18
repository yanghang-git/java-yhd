package com.yhd.cache;

import com.yhd.cache.classadapter.IndentStatusAdapter;
import com.yhd.dao.IndentStatusDao;
import com.yhd.pojo.IndentStatus;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


/**
 * static proxy
 * @Author 杨航
 * @Date 2020/7/18 14:41
 * @Since 1.8
 */
public class IndentStatusCacheProxy extends IndentStatusAdapter {
	/*
		在普通的电商网站中。 其 查询/修改 的占比大概是   8 / 2
			所以。 侧重于查询。 所以添加一个缓存用于查询
	 */
	private ArrayList<IndentStatus> statusCache = new ArrayList<>();

	public IndentStatusCacheProxy(IndentStatusDao impl) {
		super(impl);
	}

	/**
	 * get all indent status
	 * @param conn database of connection
	 * @return indent of all status gather list
	 */
	@Override
	public List<IndentStatus> getAllList(Connection conn) {
		if (statusCache.isEmpty()) {
			reloadCache(conn);
		}
		return statusCache;
	}


	/**
	 * add one status and reload cache
	 * @param conn   database of connection
	 * @param status indent status
	 * @return whether add success
	 */
	@Override
	public boolean addStatues(Connection conn, IndentStatus status) {
		boolean flag = super.addStatues(conn, status);
		reloadCache(conn);
		return flag;
	}

	/**
	 * update status and reload cache
	 * @param conn   database of connection
	 * @param status indent status
	 * @return whether update success
	 */
	@Override
	public boolean updateStatues(Connection conn, IndentStatus status) {
		boolean flag = super.updateStatues(conn, status);
		reloadCache(conn);
		return flag;
	}

	/**
	 * remove status and reload cache
	 * @param conn   database of connection
	 * @param id indent of id
	 * @return whether remove success
	 */
	@Override
	public boolean removeById(Connection conn, int id) {
		boolean flag = super.removeById(conn, id);
		reloadCache(conn);
		return flag;
	}

	/**
	 * reload indent status of cache
	 * @param conn database of connection
	 */
	private void reloadCache(Connection conn) {
		statusCache = (ArrayList<IndentStatus>) super.getAllList(conn);
	}
}
