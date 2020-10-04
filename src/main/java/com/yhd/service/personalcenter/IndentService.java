package com.yhd.service.personalcenter;

import com.yhd.pojo.Indent;

import java.util.List;

/**
 * @author 杨航
 * @date 2020/9/28 11:06
 * @since 1.8
 */
public interface IndentService {
	/**
	 * select indent paging
	 * @param username user of name
	 * @return paging of values indent
	 */
	List<Indent> getIndentByUserIdList(String username);

	/**
	 * query indent paging by status of id
	 * @param statusId indent status of id
	 * @param username user of name
	 * @return paging of values indent
	 */
	List<Indent> getAllByStatusIdAndUsername(int statusId, String username);



	/**
	 * harvest goods
	 * @param id indent of id
	 * @return is harvest goods succeed
	 */
	boolean harvestGoods(String id);

	/**
	 * remove Indent
	 * 		ps:	status must is not payment or already succeed
	 * @param id indent of id
	 * @return is remove succeed
	 */
	boolean removeIndentById(String id);

	/**
	 * get recently indent by user name
	 * @param username user of name
	 * @return indent
	 */
	Indent getRecentlyIndentByUserName(String username);

	/**
	 * get indent by indent id
	 * @param indentId indent of id
	 * @return indent instance
	 */
	Indent getIndentById(String indentId);
}
