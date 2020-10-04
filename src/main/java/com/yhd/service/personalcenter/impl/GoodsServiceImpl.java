package com.yhd.service.personalcenter.impl;

import com.yhd.dao.GoodsDao;
import com.yhd.pojo.Goods;
import com.yhd.service.personalcenter.GoodsService;

import java.sql.Connection;

/**
 * @author 杨航
 * @date 2020/9/30 9:56
 * @since 1.8
 */
public class GoodsServiceImpl implements GoodsService {
	private Connection conn;
	private GoodsDao dao;

	public GoodsServiceImpl(Connection conn, GoodsDao dao) {
		this.conn = conn;
		this.dao = dao;
	}

	/**
	 * get goods by goods of id
	 * @param goodsId goods of id
	 * @return goods instance
	 */
	@Override
	public Goods getGoodsById(int goodsId) {
		return dao.getGoodsById(conn, goodsId);
	}
}
