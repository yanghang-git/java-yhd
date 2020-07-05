package com.yhd.dao;

import com.yhd.pojo.AddressCatalog;

import java.sql.Connection;
import java.util.List;

/**
 * @author 杨航
 * @Date 2020/6/27 15:37
 * @since 1.8
 */
public interface AddressCatalogDao {
	/**
	 * 根据upId查询对应的地址。 如果输入为0 则是一级地址。 如果其他地址的id 则查询输入地址的子目录
	 * @param conn  数据库连接
	 * @param upId 上一级地址
	 * @return 地址目录
	 */
	List<AddressCatalog> getListByUpId(Connection conn, int upId);

	/**
	 * 根据id查询对应的地址。
	 * @param conn  数据库连接
	 * @param id 上一级地址
	 * @return 地址目录
	 */
	AddressCatalog getCatalogById(Connection conn, int id);

	/**
	 * 根据地址目录名字查询它上级的id
	 * @param conn 数据连接
	 * @param name 地址名字
	 * @return 上级id
	 */
	List<AddressCatalog> getUpIdByCatalogName(Connection conn, String name);

	/**
	 * 删除记录。 根据id删除
	 * @param conn 连接
	 * @param id 唯一标识
	 * @return 是否删除
	 */
	boolean removeById(Connection conn, int id);

	/**
	 * 增加记录。
	 * @param conn 连接
	 * @param catalog 记录对象
	 * @return 是否增加成功
	 */
	boolean addAddressCatalog(Connection conn, AddressCatalog catalog);

	/**
	 * 修改地址名称、根据id。
	 * @param conn 连接
	 * @param id 地址id
	 * @param catalogName 地址名称
	 * @return 是否修改成功
	 */
	boolean updateCatalogNameById(Connection conn, int id, String catalogName);
}
