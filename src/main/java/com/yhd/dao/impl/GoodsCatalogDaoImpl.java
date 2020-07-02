package com.yhd.dao.impl;

import com.mysql.jdbc.StringUtils;
import com.yhd.dao.BaseDao;
import com.yhd.dao.GoodsCatalogDao;
import com.yhd.pojo.GoodsCatalog;

import java.sql.Connection;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/28 18:13
 * @Since 1.8
 */
public class GoodsCatalogDaoImpl extends BaseDao<GoodsCatalog> implements GoodsCatalogDao {
	/**
	 * 根据upId查询对应的地址。 如果输入为0 则是一级目录。 如果其他地址的id 则查询输入地址的子目录
	 * @param conn 数据库连接
	 * @param upId 上一级地址
	 * @return 商品目录
	 */
	@Override
	public List<GoodsCatalog> getListByUpId(Connection conn, int upId) {
		String sql = "select id, name, up_id from goods_catalog where up_id = ?";
		return super.getInstances(conn, sql, upId);
	}

	/**
	 * 根据id查询对应的商品目录。
	 * @param conn 数据库连接
	 * @param id   上一级商品目录
	 * @return 商品目录
	 */
	@Override
	public GoodsCatalog getCatalogById(Connection conn, int id) {
		String sql = "select id, name, up_id from goods_catalog where id = ?";
		return super.getInstance(conn, sql, id);
	}

	/**
	 * 根据商品目录名字查询它上级的id
	 *
	 * @param conn 数据连接
	 * @param name 地址名字
	 * @return 上级id
	 */
	@Override
	public List<GoodsCatalog> getUpIdByCatalogName(Connection conn, String name) {
		String sql = "select id, name, up_id from goods_catalog where name like ?";
		return super.getInstances(conn, sql, (StringUtils.isNullOrEmpty(name) ? "%%" : "%" + name + "%"));
	}

	/**
	 * 删除记录。 根据id删除
	 * @param conn 连接
	 * @param id   唯一标识
	 * @return 是否删除
	 */
	@Override
	public boolean removeById(Connection conn, int id) {
		String sql = "delete from goods_catalog where id = ?";
		return super.update(conn, sql, id) == 1;
	}

	/**
	 * 增加目录
	 * @param conn    连接
	 * @param catalog 记录对象
	 * @return 是否增加成功
	 */
	@Override
	public boolean addGoodsCatalog(Connection conn, GoodsCatalog catalog) {
		String sql = "insert into goods_catalog(name, up_id) values(?, ?)";
		return super.update(conn, sql, catalog.getName(), catalog.getUpId()) == 1;
	}

	/**
	 * 修改目录
	 * @param conn    连接
	 * @param id          地址id
	 * @param catalogName 地址名称
	 * @return 是否修改成功
	 */
	@Override
	public boolean updateCatalog(Connection conn, int id, String catalogName) {
		String sql = "update goods_catalog set name = ? where id = ?";
		return super.update(conn, sql, catalogName, id) == 1;
	}
}
