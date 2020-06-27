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
		String sql = "select id, `password`, money from admin where id = ? and password = ?";
		return super.getInstance(conn, sql, id, password);
	}
}
