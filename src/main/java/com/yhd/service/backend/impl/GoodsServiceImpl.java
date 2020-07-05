package com.yhd.service.backend.impl;

import com.yhd.dao.GoodsDao;
import com.yhd.pojo.Goods;
import com.yhd.service.backend.GoodsService;

import java.sql.Connection;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/7/3 13:31
 * @Since 1.8
 */
public class GoodsServiceImpl implements GoodsService {

	private Connection conn;
	private GoodsDao dao;

	public GoodsServiceImpl(Connection conn, GoodsDao dao) {
		this.conn = conn;
		this.dao = dao;
	}

	/**
	 * get goods all by catalog of id
	 * @param catalogId catalog id
	 * @param goodsName goods name
	 * @return goods all list gather
	 */
	@Override
	public List<Goods> getGoodsByCatalogIdAndGoodsName(int catalogId, String goodsName) {
		return dao.getListByCatalogIdAndGoodsName(conn, catalogId, goodsName);
	}

	/**
	 * get single goods instance by goods of id
	 * @param id goods of id
	 * @return single goods instance
	 */
	@Override
	public Goods getGoodsById(int id) {
		return dao.getGoodsById(conn, id);
	}

	/**
	 * add Goods arrive record
	 * @param goods instance
	 * @return true: add success  or  false: add fail
	 */
	@Override
	public boolean addGoods(Goods goods) {
		return dao.addGoods(conn, goods);
	}

	/**
	 * remove Goods by id
	 * @param id goods id
	 * @return true: remove success or false: remove fail
	 */
	@Override
	public boolean removeById(int id) {
		return dao.removeById(conn, id);
	}

	/**
	 * update Goods by id
	 * @param goods Goods instance
	 * @return true: update success or false: update fail
	 */
	@Override
	public boolean updateById(Goods goods) {
		return dao.updateGoods(conn, goods);
	}
}
