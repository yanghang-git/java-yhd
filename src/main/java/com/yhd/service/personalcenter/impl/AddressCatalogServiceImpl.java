package com.yhd.service.personalcenter.impl;

import com.yhd.dao.AddressCatalogDao;
import com.yhd.pojo.AddressCatalog;
import com.yhd.service.personalcenter.AddressCatalogService;

import java.sql.Connection;
import java.util.List;

/**
 * @author 杨航
 * @date 2020/9/30 15:05
 * @since 1.8
 */
public class AddressCatalogServiceImpl implements AddressCatalogService {
	private Connection conn;
	private AddressCatalogDao dao;

	public AddressCatalogServiceImpl(Connection conn, AddressCatalogDao dao) {
		this.conn = conn;
		this.dao = dao;
	}

	/**
	 * get addressCatalog by addressCatalog up of id
	 * @param id catalog of id
	 * @return addressCatalog
	 */
	@Override
	public List<AddressCatalog> getCatalogByUpId(int id) {
		return dao.getListByUpId(conn, id);
	}

	/**
	 * get addredssCatalog by catalog id
	 * @param id addressCatalog of id
	 * @return addressCatalog instance
	 */
	@Override
	public AddressCatalog getCatalogById(int id) {
		return dao.getCatalogById(conn, id);
	}
}
