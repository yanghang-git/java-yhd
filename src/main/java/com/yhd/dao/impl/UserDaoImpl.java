package com.yhd.dao.impl;

import com.yhd.dao.BaseDao;
import com.yhd.dao.UserDao;
import com.yhd.pojo.User;

import java.sql.Connection;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/28 20:00
 * @Since 1.8
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao {
	/**
	 * 获取全部的User用户
	 * @param conn 连接
	 * @return 用户集合
	 */
	@Override
	public List<User> getListAll(Connection conn) {
		String sql = "select id, password, name, sex, birthday, email, phone, address_id, freeze from user";
		return super.getInstances(conn, sql);
	}

	/**
	 * 根据用户的Id进行查询。 模糊查询
	 * @param conn 连接
	 * @param id   用户的账号
	 * @return 用户集合 List
	 */
	@Override
	public List<User> getAllByIdList(Connection conn, String id) {
		String sql = "select id, password, name, sex, birthday, email, phone, address_id, freeze from user where id like ?";
		return super.getInstances(conn, sql, "%" + (id == null || id.length() == 0 ? "" : id) + "%");
	}

	/**
	 * 根据用户的Id进行 冻结 / 解冻
	 * @param conn   连接
	 * @param id     用户的账号
	 * @param freeze 解冻 / 冻结
	 * @return 是否修改成功
	 */
	@Override
	public boolean updateUserFreeze(Connection conn, String id, boolean freeze) {
		String sql = "update user set freeze = ? where id = ?";
		return super.update(conn, sql, freeze, id) == 1;
	}

	/**
	 * 修改User用户
	 * @param conn 连接
	 * @param user 用户
	 * @return 是否修改成功
	 */
	@Override
	public boolean updateUser(Connection conn, User user) {
		String sql = "update user set password = ?, name = ?, sex = ?, birthday = ?, email = ?, phone = ?, address_id = ?, freeze = ? where id = ?";
		return super.update(conn, sql, user.getPassword(), user.getName(), user.getSex(), user.getBirthday()
			, user.getEmail(), user.getPhone(), user.getAddressId(), user.getFreeze(), user.getId()) == 1;
	}

	/**
	 * 添加User用户
	 * @param conn 连接
	 * @param user 用户
	 * @return 是否添加成功
	 */
	@Override
	public boolean addUser(Connection conn, User user) {
		String sql = "insert into user(id, password, phone) values(?, ? ,?)";
		return super.update(conn, sql, user.getId(), user.getPassword(), user.getPhone()) == 1;
	}

	/**
	 * 删除User用户 根据用户Id
	 * @param conn 连接
	 * @param id   用户id
	 * @return 是否添加删除
	 */
	@Override
	public boolean removeById(Connection conn, String id) {
		String sql = "delete from user where id = ?";
		return super.update(conn, sql, id) == 1;
	}
}
