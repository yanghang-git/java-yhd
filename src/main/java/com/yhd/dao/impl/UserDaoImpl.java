package com.yhd.dao.impl;

import com.yhd.dao.BaseDao;
import com.yhd.dao.UserDao;
import com.yhd.pojo.User;
import com.yhd.util.JDBCUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Date;
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
	 *
	 * @param conn      连接
	 * @param id        用户的账号
	 * @param pageSize  第几页
	 * @param pageCount 一页显示多少
	 * @return 用户集合 List
	 */
	@Override
	public List<User> getAllByIdList(Connection conn, String id, int pageSize, int pageCount) {
		String sql = "select id, password, name, sex, birthday, email, phone, address_id, freeze from user where id like ? limit ?, ?";
		return super.getInstances(conn, sql, "%" + (id == null || id.length() == 0 ? "" : id) + "%", (pageSize - 1) * pageCount, pageCount);
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
		if (user.getId() == null) throw new RuntimeException("alter user of id not is null");
		StringBuilder sql = new StringBuilder();
		sql.append("update user set ");
		Field[] fields = user.getClass().getDeclaredFields();
		try {
			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i].getName();
				if ("serialVersionUID".equals(fieldName) || "id".equals(fieldName)) continue;
				Method method = user.getClass().getMethod(JDBCUtils.firstAddGet(fieldName));
				Object value = method.invoke(user);
				if (value != null) {
					sql.append(JDBCUtils.replaceFirstToUpper_(fieldName)).append("=");
					if (fields[i].getType().equals(String.class) || fields[i].getType().equals(Date.class)) {
						sql.append("'").append(value).append("'");
					} else {
						sql.append(value);
					}
					sql.append(",");
				}
			}
			if (sql.charAt(sql.length() - 1) == ',') {
				sql.replace(sql.length() - 1, sql.length(), "");
			}
			sql.append(" where id = ?");
			return super.update(conn, sql.toString(), user.getId()) == 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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

	/**
	 * 获取用户的个数
	 * @param conn 连接
	 * @param id 用户id
	 * @return 用户的个数
	 */
	@Override
	public long getUserCount(Connection conn, String id) {
		String sql = "select count(*) from user where id like ?";
		return super.getSimple(conn, sql, "%" + (id == null || id.length() == 0 ? "" : id) + "%");
	}

	/**
	 * 根据用户id获取其信息
	 * @param conn   连接
	 * @param userId 用户id
	 * @return 用户
	 */
	@Override
	public User getUserById(Connection conn, String userId) {
		String sql = "select id, password, name, sex, birthday, email, phone, address_id, freeze from user where id = ?";
		return super.getInstance(conn, sql, userId);
	}

	/**
	 * 修改用户的默认地址
	 * @param conn      连接
	 * @param addressId 地址编号
	 * @param userId    用户名称
	 * @return 是否修改成功
	 */
	@Override
	public boolean updateUserDefaultAddress(Connection conn, int addressId, String userId) {
		String sql = "update user set address_id = ? where id = ?";
		return super.update(conn, sql, addressId, userId) == 1;
	}

	/**
	 * 检查手机号是否存在
	 * @param conn  连接
	 * @param phone 手机号
	 * @return 是否存在
	 */
	@Override
	public boolean checkUserPhoneIsExist(Connection conn, String phone) {
		String sql = "select count(*) from user where phone = ?";
		long count = super.getSimple(conn, sql, phone);
		return count == 1;
	}
}
