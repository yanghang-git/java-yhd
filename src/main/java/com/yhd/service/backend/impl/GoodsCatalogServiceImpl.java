package com.yhd.service.backend.impl;

import com.yhd.dao.GoodsCatalogDao;
import com.yhd.pojo.GoodsCatalog;
import com.yhd.service.backend.GoodsCatalogService;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/7/2 22:10
 * @Since 1.8
 */
public class GoodsCatalogServiceImpl implements GoodsCatalogService {
	private Connection conn;
	private GoodsCatalogDao dao;

	public GoodsCatalogServiceImpl(Connection conn, GoodsCatalogDao dao) {
		this.conn = conn;
		this.dao = dao;
	}

	/**
	 * get GoodsCatalog By up_id. if up_id is 0,get all GoodsCatalog
	 * @param upId up level GoodsCatalog id
	 * @return GoodsCatalog gather List
	 */
	@Override
	public List<GoodsCatalog> getAllListByUpId(int upId) {
		List<GoodsCatalog> parentList = dao.getListByUpId(conn, upId);
		for (GoodsCatalog catalog : parentList) {
			catalog.setCatalogs(this.getAllListByUpId(catalog.getId()));
		}
		return parentList;
	}

	/**
	 * get GoodsCatalog By up_id. if up_id is 0,get one level all GoodsCatalog
	 * @param upId up level AddressCatalog id
	 * @return AddressCatalog gather List
	 */
	@Override
	public List<GoodsCatalog> getListByUpId(int upId) {
		return dao.getListByUpId(conn, upId);
	}

	/**
	 * get GoodsCatalog By id
	 *
	 * @param id GoodsCatalog id
	 * @return GoodsCatalog instance
	 */
	@Override
	public GoodsCatalog getCatalogById(int id) {
		return dao.getCatalogById(conn, id);
	}

	/**
	 * get GoodsCatalog by catalog of name
	 * @param name catalog of name
	 * @return GoodsCatalog gather List
	 */
	@Override
	public List<GoodsCatalog> getListByCatalogName(String name) {
		List<GoodsCatalog> sonList = dao.getUpIdByCatalogName(conn, name);
		List<GoodsCatalog> resultList = new ArrayList<>(sonList.size());
		for (GoodsCatalog catalog : sonList) {
			resultList.add(getSuperCatalog(catalog));
		}
		return resultList;
	}

	/**
	 * get one rank catalog, it of son catalog is transmit of parameter of all parent rank and son catalog only have one
	 * @param catalog son catalog
	 * @return one rank catalog
	 */
	private GoodsCatalog getSuperCatalog(GoodsCatalog catalog) {
		if(catalog.getUpId() == 0) {
			return catalog;
		}
		GoodsCatalog tempParentCatalog = dao.getCatalogById(conn, catalog.getUpId());
		tempParentCatalog.setCatalogs(Collections.singletonList(catalog));
		return getSuperCatalog(tempParentCatalog);
	}


	/**
	 * remove GoodsCatalog By id
	 * @param id id
	 * @return true: remove success or false: remove fail
	 */
	@Override
	public boolean removeById(int id) {
		return dao.removeById(conn, id);
	}

	/**
	 * add GoodsCatalog
	 * @param catalog instance
	 * @return true: add success or false: add fail
	 */
	@Override
	public boolean addGoodsCatalog(GoodsCatalog catalog) {
		return dao.addGoodsCatalog(conn, catalog);
	}

	/**
	 * update GoodsCatalog
	 * @param id catalog of id
	 * @param catalogName new catalog name
	 * @return true: update success or false: update fail
	 */
	public boolean updateCatalog(int id, String catalogName) {
		return dao.updateCatalog(conn, id, catalogName);
	}
}
