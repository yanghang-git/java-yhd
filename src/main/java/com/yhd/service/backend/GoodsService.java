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
	 * get goods all by catalog of id and goods name
	 * @param catalogId catalog id
	 * @param goodsName goods name
	 * @return goods all list gather
	 */
	List<Goods> getGoodsByCatalogIdAndGoodsName(int catalogId, String goodsName);

	/**
	 * get single goods instance by goods of id
	 * @param id goods of id
	 * @return single goods instance
	 */
	Goods getGoodsById(int id);

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
	boolean removeById(int id);

	/**
	 * update Goods by id
	 * @param goods Goods instance
	 * @return true: update success or false: update fail
	 */
	boolean updateById(Goods goods);

}
