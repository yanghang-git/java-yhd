package com.yhd.dao.impl;

import com.mysql.jdbc.StringUtils;
import com.yhd.dao.BaseDao;
import com.yhd.dao.GoodsDao;
import com.yhd.pojo.Goods;

import java.sql.Connection;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/28 14:26
 * @Since 1.8
 */
public class GoodsDaoImpl extends BaseDao<Goods> implements GoodsDao {



	/**
	 * 根据商品目录Id查询和商品名字下所有的商品
	 * @param conn      连接
	 * @param catalogId 目录Id
	 * @param goodsName 商品名称
	 * @return 商品集合
	 */
	@Override
	public List<Goods> getListByCatalogIdAndGoodsName(Connection conn, int catalogId, String goodsName) {
		String sql = "select id, name, price, style, kind, content, image_primary, image_details, number, catalog_id from goods where name like ? ";
		sql += (catalogId == 0 ? "" : " and catalog_id = " + catalogId);
		return super.getInstances(conn, sql, StringUtils.isNullOrEmpty(goodsName) ? "%%" : "%" + goodsName + "%");
	}

	/**
	 * 根据商品id查询
	 *
	 * @param conn 连接
	 * @param id   商品id
	 * @return 商品实例
	 */
	@Override
	public Goods getGoodsById(Connection conn, int id) {
		String sql = "select id, name, price, style, kind, content, image_primary, image_details, number, catalog_id from goods where id = ? ";
		return super.getInstance(conn, sql, id);
	}

	/**
	 * 添加一个商品
	 * @param conn  连接
	 * @param goods 商品实例
	 * @return 是否添加成功
	 */
	@Override
	public boolean addGoods(Connection conn, Goods goods) {
		String sql = "insert into goods(`name`, price, style, kind, content, image_primary, image_details, number, catalog_id) values(?, ?, ?, ?, ?, ? ,? ,? ,?)";
		return super.update(conn, sql, goods.getName(), goods.getPrice(), goods.getStyle()
				, goods.getKind(), goods.getContent(), goods.getImagePrimary()
				, goods.getImageDetails(), goods.getNumber(), goods.getCatalogId()) == 1;
	}

	/**
	 * 删除一个商品
	 * @param conn 连接
	 * @param id   商品的id
	 * @return 是否删除成功
	 */
	@Override
	public boolean removeById(Connection conn, int id) {
		String sql = "delete from goods where id = ?";
		return super.update(conn, sql, id) == 1;
	}

	/**
	 * 修改商品
	 * @param conn  连接
	 * @param goods 商品
	 * @return 是否修改成功
	 */
	@Override
	public boolean updateGoods(Connection conn, Goods goods) {
		String sql = "update goods set name = ?, price = ?, style = ?, kind = ?, content = ?, image_primary = ?, image_details = ?, number = ?, catalog_id = ? where id = ?";
		return super.update(conn, sql, goods.getName(), goods.getPrice(), goods.getStyle()
				, goods.getKind(), goods.getContent(), goods.getImagePrimary()
				, goods.getImageDetails(), goods.getNumber(), goods.getCatalogId(), goods.getId()) == 1;
	}
}
