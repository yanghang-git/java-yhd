package com.yhd.dao;

import com.yhd.pojo.Goods;

import java.sql.Connection;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/27 17:33
 * @Since 1.8
 */
public interface GoodsDao {
	/**
	 * 根据商品目录Id查询下所有的商品
	 * @param conn 连接
	 * @param catalogId 目录Id
	 * @return 商品集合
	 */
	List<Goods> getListByCatalogId(Connection conn, int catalogId);

	/**
	 * 添加一个商品
	 * @param conn 连接
	 * @param goods 商品实例
	 * @return 是否添加成功
	 */
	boolean addGoods(Connection conn, Goods goods);

	/**
	 * 删除一个商品
	 * @param conn 连接
	 * @param id 商品的id
	 * @return 是否删除成功
	 */
	boolean removeById(Connection conn, int id);

	/**
	 * 修改商品
	 * @param conn 连接
	 * @param goods 商品
	 * @return 是否修改成功
	 */
	boolean updateGoods(Connection conn, Goods goods);

}


