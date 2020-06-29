package com.yhd.service.backend;

import com.yhd.pojo.Goods;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/29 12:28
 * @Since 1.8
 */
public interface GoodsService {
	/**
	 * get goods all by catalog of id
	 * @param catalogId catalog id
	 * @return goods all list gather
	 */
	List<Goods> getGoodsByCatalogId(int catalogId);

	/**
	 * add Goods arrive record
	 * @param goods instance
	 * @return true: add success  or  false: add fail
	 */
	boolean addGoods(Goods goods);

	/**
	 * remove Goods by id
	 * @param id goods id
	 * @return true: remove success or false: remove fail
	 */
	boolean removeById(String id);

	/**
	 * update Goods by id
	 * @param id Goods id
	 * @param goods Goods instance
	 * @return true: update success or false: update fail
	 */
	boolean updateById(String id, Goods goods);

}
