package com.yhd.service.personalcenter;

import com.yhd.pojo.AddressCatalog;

import java.util.List;

/**
 * @author 杨航
 * @date 2020/9/30 12:18
 * @since 1.8
 */
public interface AddressCatalogService {
	/**
	 * get addressCatalog by addressCatalog up of id
	 * @param id catalog of id
	 * @return addressCatalog
	 */
	List<AddressCatalog> getCatalogByUpId(int id);

	/**
	 * get addredssCatalog by catalog id
	 * @param id addressCatalog of id
	 * @return addressCatalog instance
	 */
	AddressCatalog getCatalogById(int id);
}
