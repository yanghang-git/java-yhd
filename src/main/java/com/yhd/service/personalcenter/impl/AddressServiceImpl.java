package com.yhd.service.personalcenter.impl;

import com.yhd.dao.AddressDao;
import com.yhd.pojo.Address;
import com.yhd.service.personalcenter.AddressService;

import java.sql.Connection;
import java.util.List;

/**
 * @author 杨航
 * @date 2020/9/28 15:51
 * @since 1.8
 */
public class AddressServiceImpl implements AddressService {
	private Connection conn;
	private AddressDao dao;

	public AddressServiceImpl(Connection conn, AddressDao dao) {
		this.conn = conn;
		this.dao = dao;
	}


	/**
	 * get address by username
	 * @param username user of name
	 * @return address
	 */
	@Override
	public List<Address> getAddressListByUsername(String username) {
		return dao.getByUserIdList(conn, username);
	}

	/**
	 * add address
	 * @param address address instance
	 * @return is add success
	 */
	@Override
	public boolean addAddress(Address address) {
		return dao.addAddress(conn, address);
	}

	/**
	 * alter address
	 * @param address address instance
	 * @return is alter success
	 */
	@Override
	public boolean alterAddress(Address address) {
		return dao.updateAddress(conn, address);
	}

	/**
	 * remove address
	 * @param id address instance
	 * @return is remove success
	 */
	@Override
	public boolean removeAddress(int id) {
		return dao.removeById(conn, id);
	}

	/**
	 * get address detail info by address id
	 * @param addressId address of id
	 * @return address info
	 */
	@Override
	public String getAddressDetailByAddressId(int addressId) {
		return dao.getAddressDetailById(conn, addressId);
	}

	/**
	 * ger Address by address id
	 * @param addressId address of id
	 * @return Address Instance
	 */
	@Override
	public Address getAddressById(int addressId) {
		return dao.getAddressById(conn, addressId);
	}

	/**
	 * update address
	 * @param address address instance
	 * @return is update success
	 */
	@Override
	public boolean updateAddress(Address address) {
		return dao.updateAddress(conn, address);
	}
}
