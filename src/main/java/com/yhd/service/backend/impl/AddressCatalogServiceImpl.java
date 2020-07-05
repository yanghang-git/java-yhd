package com.yhd.service.backend.impl;

import com.yhd.dao.AddressCatalogDao;
import com.yhd.pojo.AddressCatalog;
import com.yhd.service.backend.AddressCatalogService;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/7/2 8:59
 * @Since 1.8
 */
public class AddressCatalogServiceImpl implements AddressCatalogService {

	private Connection conn;
	private AddressCatalogDao dao;

	public AddressCatalogServiceImpl(Connection conn, AddressCatalogDao dao) {
		this.conn = conn;
		this.dao = dao;
	}

	/**
	 * get AddressCatalog By up_id. if up_id is 0,get all level all AddressCatalog
	 * @param upId up level AddressCatalog id
	 * @return AddressCatalog gather List
	 */
	@Override
	public List<AddressCatalog> getAllListByUpId(int upId) {
		List<AddressCatalog> parentList = dao.getListByUpId(conn, upId);
		for (AddressCatalog catalog : parentList) {
			catalog.setCatalogs(this.getAllListByUpId(catalog.getId()));
		}
		return parentList;
	}

	/**
	 * get AddressCatalog By up_id. if up_id is 0,get one level all AddressCatalog
	 * @param upId up level AddressCatalog id
	 * @return AddressCatalog gather List
	 */
	@Override
	public List<AddressCatalog> getListByUpId(int upId) {
		return dao.getListByUpId(conn, upId);
	}

	/**
	 * get AddressCatalog by catalog of name
	 * @param name catalog of name
	 * @return AddressCatalog gather List
	 */
	@Override
	public List<AddressCatalog> getListByCatalogName(String name) {
		List<AddressCatalog> sonList = dao.getUpIdByCatalogName(conn, name);
		List<AddressCatalog> resultList = new ArrayList<>(sonList.size());
		for (AddressCatalog catalog : sonList) {
			resultList.add(getSuperCatalog(catalog));
		}
		return resultList;
	}

	/**
	 * get one rank catalog, it of son catalog is transmit of parameter of all parent rank and son catalog only have one
	 * @param catalog son catalog
	 * @return one rank catalog
	 */
	private AddressCatalog getSuperCatalog(AddressCatalog catalog) {
		if(catalog.getUpId() == 0) {
			return catalog;
		}
		AddressCatalog tempParentCatalog = dao.getCatalogById(conn, catalog.getUpId());
		tempParentCatalog.setCatalogs(Collections.singletonList(catalog));
		return getSuperCatalog(tempParentCatalog);
	}


	/**
	 * remove AddressCatalog By id
	 * @param id id
	 * @return true: remove success or false: remove fail
	 */
	@Override
	public boolean removeById(int id) {
		return dao.removeById(conn, id);
	}

	/**
	 * add AddressCatalog
	 * @param catalog instance
	 * @return true: add success or false: add fail
	 */
	@Override
	public boolean addAddressCatalog(AddressCatalog catalog) {
		return dao.addAddressCatalog(conn, catalog);
	}

	/**
	 * update AddressCatalog name by catalogId
	 * @param id          catalog of id
	 * @param catalogName new catalog name
	 * @return true: add success or false: add fail
	 */
	@Override
	public boolean updateCatalogNameById(int id, String catalogName) {
		return dao.updateCatalogNameById(conn, id, catalogName);
	}

}
