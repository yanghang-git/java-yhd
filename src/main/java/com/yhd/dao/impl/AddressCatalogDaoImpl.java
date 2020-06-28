package com.yhd.dao.impl;

import com.yhd.dao.AddressCatalogDao;
import com.yhd.dao.BaseDao;
import com.yhd.pojo.AddressCatalog;

import java.sql.Connection;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/28 11:32
 * @Since 1.8
 */
public class AddressCatalogDaoImpl extends BaseDao<AddressCatalog> implements AddressCatalogDao {

	/**
	 * 根据upId查询对应的地址。 如果输入为0 则是一级地址。 如果其他地址的id 则查询输入地址的子目录
	 * @param conn 数据库连接
	 * @param upId 上一级地址
	 * @return 地址目录
	 */
	@Override
	public List<AddressCatalog> getListByUpId(Connection conn, int upId) {
		String sql = "select id, name, up_id from address_catalog where up_id = ?";
		return super.getInstances(conn, sql, upId);
	}

	/**
	 * 删除记录。 根据id删除
	 * @param conn 连接
	 * @param id   唯一标识
	 * @return 是否删除
	 */
	@Override
	public boolean removeById(Connection conn, int id) {
		String sql = "delete from address_catalog where id = ?";
		return super.update(conn, sql, id) == 1;
	}

	/**
	 * 增加记录。
	 * @param conn    连接
	 * @param catalog 记录对象
	 * @return 是否增加成功
	 */
	@Override
	public boolean addAddressCatalog(Connection conn, AddressCatalog catalog) {
		String sql = "insert into address_catalog(name, up_id) values(?, ?)";
		return super.update(conn, sql, catalog.getName(), catalog.getUpId()) == 1;
	}

	/**
	 * 修改记录。
	 * @param conn    连接
	 * @param catalog 记录对象
	 * @return 是否修改成功
	 */
	@Override
	public boolean updateCatalog(Connection conn, AddressCatalog catalog) {
		String sql = "update address_catalog set name = ?, up_id = ? where id = ?";
		return super.update(conn, sql, catalog.getName(), catalog.getUpId(), catalog.getId()) == 1;
	}
}
