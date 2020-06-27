package com.yhd.dao;

import com.yhd.pojo.AddressCatalog;
import com.yhd.pojo.GoodsCatalog;

import java.sql.Connection;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/27 17:21
 * @Since 1.8
 */
public interface GoodsCatalogDao {

	/**
	 * 根据upId查询对应的地址。 如果输入为0 则是一级目录。 如果其他地址的id 则查询输入地址的子目录
	 * @param conn  数据库连接
	 * @param upId 上一级地址
	 * @return 商品目录
	 */
	List<GoodsCatalog> getListByUpId(Connection conn, int upId);

	/**
	 * 删除记录。 根据id删除
	 * @param conn 连接
	 * @param id 唯一标识
	 * @return 是否删除
	 */
	boolean removeById(Connection conn, int id);

	/**
	 * 增加目录
	 * @param conn 连接
	 * @param catalog 记录对象
	 * @return 是否增加成功
	 */
	boolean addGoodsCatalog(Connection conn, GoodsCatalog catalog);

	/**
	 * 修改目录
	 * @param conn 连接
	 * @param catalog 记录对象
	 * @return 是否修改成功
	 */
	boolean updateCatalog(Connection conn, GoodsCatalog catalog);

}
