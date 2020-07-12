package com.yhd.dao.impl;

import com.yhd.dao.AdminDao;
import com.yhd.dao.BaseDao;
import com.yhd.pojo.Admin;

import java.sql.Connection;

/**
 * @Author 杨航
 * @Date 2020/6/27 15:23
 * @Since 1.8
 */
public class AdminDaoImpl extends BaseDao<Admin> implements AdminDao {

	/**
	 * 根据指定的账号密码返回复合的 Admin 实例 。如果不存在则返回  null
	 * @param conn     数据的连接
	 * @param id       账号
	 * @param password 密码
	 * @return Admin of instance	 or		 null
	 */
	@Override
	public Admin getAdminByIdAndPassword(Connection conn, String id, String password) {
		String sql = "select id, `password`, admin_power, user_power, activity_power, indent_power, goods_power, address_power from admin where id = ? and password = ?";
		return super.getInstance(conn, sql, id, password);
	}

	/**
	 * 添加一个管理员
	 * @param conn  连接
	 * @param admin 管理员
	 * @return 是否添加成功
	 */
	@Override
	public boolean addAdmin(Connection conn, Admin admin) {
		String sql = "insert into admin(id, `password`, user_power, activity_power, indent_power, goods_power, address_power) values(?, ?, ?, ?, ?, ?, ?)";
		return super.update(conn, sql, admin.getId(), admin.getPassword(), admin.getUserPower(), admin.getActivityPower()
				, admin.getIndentPower(), admin.getGoodsPower(), admin.getAddressPower()) == 1;
	}

	/**
	 * 查询id是否存在
	 *
	 * @param conn 连接
	 * @param id   id
	 * @return 是否存在
	 */
	@Override
	public boolean containsId(Connection conn, String id) {
		String sql = "SELECT COUNT(*) FROM `admin` WHERE id = ?";
		return ((long)super.getSimple(conn, sql, id) >= 1);
	}

}
