package com.yhd.service.backend;

import com.yhd.pojo.AddressCatalog;
import com.yhd.pojo.GoodsCatalog;

import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/29 14:00
 * @Since 1.8
 */
public interface GoodsCatalogService {
	/**
	 * get GoodsCatalog By up_id. if up_id is 0,get all GoodsCatalog
	 * @param upId up level GoodsCatalog id
	 * @return GoodsCatalog gather List
	 */
	List<GoodsCatalog> getAllListByUpId(int upId);

	/**
	 * get GoodsCatalog By up_id. if up_id is 0,get one level all GoodsCatalog
	 * @param upId up level AddressCaGoodsCatalogtalog id
	 * @return GoodsCatalog gather List
	 */
	List<GoodsCatalog> getListByUpId(int upId);

	/**
	 * get GoodsCatalog By id
	 * @param id GoodsCatalog id
	 * @return GoodsCatalog instance
	 */
	GoodsCatalog getCatalogById(int id);

	/**
	 * get GoodsCatalog by catalog of name
	 * @param name catalog of name
	 * @return GoodsCatalog gather List
	 */
	List<GoodsCatalog> getListByCatalogName(String name);

	/**
	 * remove GoodsCatalog By id
	 * @param id id
	 * @return true: remove success or false: remove fail
	 */
	boolean removeById(int id);

	/**
	 * add GoodsCatalog
	 * @param catalog instance
	 * @return true: add success or false: add fail
	 */
	boolean addGoodsCatalog(GoodsCatalog catalog);

	/**
	 * update GoodsCatalog
	 * @param id catalog of id
	 * @param catalogName new catalog name
	 * @return true: update success or false: update fail
	 */
	boolean updateCatalog(int id, String catalogName);
}
