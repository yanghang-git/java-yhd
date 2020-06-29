package com.yhd.service.backend;

import com.yhd.pojo.AddressCatalog;

import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/29 14:11
 * @Since 1.8
 */
public interface AddressCatalogService {
	/**
	 * get AddressCatalog By up_id. if up_id is 0,get one level all AddressCatalog
	 * @param upId up level AddressCatalog id
	 * @return AddressCatalog gather List
	 */
	List<AddressCatalog> getListByUpId(int upId);

	/**
	 * remove AddressCatalog By id
	 * @param id id
	 * @return true: remove success or false: remove fail
	 */
	boolean removeById(int id);

	/**
	 * add AddressCatalog
	 * @param catalog instance
	 * @return true: add success or false: add fail
	 */
	boolean addAddressCatalog(AddressCatalog catalog);

	/**
	 * update AddressCatalog
	 * @param catalog instance
	 * @return true: add success or false: add fail
	 */
	boolean updateCatalog(AddressCatalog catalog);
}
