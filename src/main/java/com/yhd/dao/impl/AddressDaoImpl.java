package com.yhd.dao.impl;

import com.yhd.dao.AddressDao;
import com.yhd.dao.BaseDao;
import com.yhd.pojo.Address;
import com.yhd.util.JDBCUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/28 16:11
 * @Since 1.8
 */
public class AddressDaoImpl extends BaseDao<Address> implements AddressDao {
	/**
	 * 根据用户账号（id） 查询对应的地址集合
	 * @param conn   连接
	 * @param userId 用户账号
	 * @return List集合
	 */
	@Override
	public List<Address> getByUserIdList(Connection conn, String userId) {
		String sql = "select id, user_id, username, phone, city, county, street, detail from address where user_id = ?";
		return super.getInstances(conn, sql, userId);
	}

	/**
	 * 获取地址详细信息根据id
	 * @param conn 连接
	 * @param id   id
	 * @return 详细地址
	 */
	@Override
	public String getAddressDetailById(Connection conn, int id) {
		String sql = "SELECT CONCAT(c1.name, c2.name, c3.name, a.detail) adderss FROM address a INNER JOIN address_catalog c1 ON a.city = c1.id INNER JOIN address_catalog c2 ON a.county = c2.id inner JOIN address_catalog c3 ON a.street = c3.id   WHERE a.id = ?";
		return super.getSimple(conn, sql, id);
	}

	/**
	 * 根据自己的id删除记录
	 * @param conn 连接
	 * @param id   自己的id
	 * @return 是否删除成功
	 */
	@Override
	public boolean removeById(Connection conn, int id) {
		String sql = "delete from address where id = ?";
		return super.update(conn, sql, id) == 1;
	}

	/**
	 * 根据一个Address的实例。去添加数据
	 * @param conn    连接
	 * @param address 实例
	 * @return 是否添加成功
	 */
	@Override
	public boolean addAddress(Connection conn, Address address) {
		String sql = "insert into address(user_id, username, phone, city, county, street, detail) values(?, ?, ?, ?, ?, ?, ?)";
		return super.update(conn, sql, address.getUserId(), address.getUsername(), address.getPhone(), address.getCity()
				,address.getCounty(), address.getStreet(), address.getDetail()) == 1;
	}

	/**
	 * 根据一个Address的实例。去修改数据
	 * @param conn    连接
	 * @param address 实例
	 * @return 是否修改成功
	 */
	@Override
	public boolean updateAddress(Connection conn, Address address) {
		if (address.getId() == null) throw new RuntimeException("alter address of id not is null");
		StringBuilder sql = new StringBuilder();
		sql.append("update address set ");
		Field[] fields = address.getClass().getDeclaredFields();
		try {
			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i].getName();
				if ("serialVersionUID".equals(fieldName) || "id".equals(fieldName)) continue;
				Method method = address.getClass().getMethod(JDBCUtils.firstAddGet(fieldName));
				Object value = method.invoke(address);
				if (value != null) {
					sql.append(JDBCUtils.replaceFirstToUpper_(fieldName)).append("=");
					if (fields[i].getType().equals(String.class)) {
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
			return super.update(conn, sql.toString(), address.getId()) == 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 获取address的示例跟它的Id
	 * @param conn      连接
	 * @param addressId id
	 * @return 示例
	 */
	@Override
	public Address getAddressById(Connection conn, int addressId) {
		String sql = "select  id, user_id, username, phone, city, county, street, detail from address where id = ?";
		return super.getInstance(conn, sql, addressId);
	}
}
