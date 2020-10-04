package com.yhd.service.personalcenter;

import com.yhd.pojo.Address;

import java.util.List;

/**
 * @author 杨航
 * @date 2020/9/28 15:45
 * @since 1.8
 */
public interface AddressService {
	/**
	 * get address by username
	 * @param username user of name
	 * @return address
	 */
	List<Address> getAddressListByUsername(String username);


	/**
	 * add address
	 * @param address address instance
	 * @return is add success
	 */
	boolean addAddress(Address address);

	/**
	 * alter address
	 * @param address address instance
	 * @return is alter success
	 */
	boolean alterAddress(Address address);

	/**
	 * remove address
	 * @param id address instance
	 * @return is remove success
	 */
	boolean removeAddress(int id);

	/**
	 * get address detail info by address id
	 * @param addressId address of id
	 * @return address info
	 */
	String getAddressDetailByAddressId(int addressId);

	/**
	 * ger Address by address id
	 * @param addressId address of id
	 * @return Address Instance
	 */
	Address getAddressById(int addressId);

	/**
	 * update address
	 * @param address address instance
	 * @return is update success
	 */
	boolean updateAddress(Address address);
}
