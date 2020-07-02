package com.yhd.service.backend.impl;

import com.yhd.dao.AddressCatalogDao;
import com.yhd.pojo.AddressCatalog;
import com.yhd.service.backend.AddressCatalogService;

import java.sql.Connection;
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
	 * get AddressCatalog By up_id. if up_id is 0,get one level all AddressCatalog
	 * @param upId up level AddressCatalog id
	 * @return AddressCatalog gather List
	 */
	@Override
	public List<AddressCatalog> getListByUpId(int upId) {
		List<AddressCatalog> parentList = dao.getListByUpId(conn, upId);
		for (AddressCatalog catalog : parentList) {
			List<AddressCatalog> sonList = this.getListByUpId(catalog.getId());
			catalog.setCatalogs(sonList);
		}
		return parentList;
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
	 * update AddressCatalog
	 * @param catalog instance
	 * @return true: add success or false: add fail
	 */
	@Override
	public boolean updateCatalog(AddressCatalog catalog) {
		return dao.updateCatalog(conn, catalog);
	}
}
