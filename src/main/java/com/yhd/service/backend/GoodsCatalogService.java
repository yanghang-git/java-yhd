package com.yhd.service.backend;

import com.yhd.pojo.GoodsCatalog;

import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/29 14:00
 * @Since 1.8
 */
public interface GoodsCatalogService {
	/**
	 * get GoodsCatalog By up_id. if up_id is 0,get one level all GoodsCatalog
	 * @param upId up level GoodsCatalog id
	 * @return GoodsCatalog gather List
	 */
	List<GoodsCatalog> getListByUpId(int upId);

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
	 * @param catalog instance
	 * @return true: update success or false: update fail
	 */
	boolean updateCatalog(GoodsCatalog catalog);
}
