package com.yhd.service.personalcenter;

import com.yhd.pojo.User;

/**
 * @author 杨航
 * @date 2020/9/28 15:41
 * @since 1.8
 */
public interface UserService {
	/**
	 * get user by username
	 * @param username user of name
	 * @return user instance
	 */
	User getUserByName(String username);

	/**
	 * update user
	 * @param user user instance
	 * @return is update success
	 */
	boolean updateUser(User user);

	/**
	 * alter user default address
	 * @param addressId address of id
	 * @param userId user of id
	 * @return is alter success
	 */
	boolean alterDefaultAddress(int addressId, String userId);

	/**
	 * check user of phone is exist
	 * @param phone user of phone
	 * @return is exist
	 */
	boolean checkUserPhoneIsExist(String phone);
}
