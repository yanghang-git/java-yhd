package com.yhd.service.personalcenter;

import com.yhd.pojo.IndentStatus;

import java.util.List;

/**
 * @author 杨航
 * @date 2020/9/29 16:02
 * @since 1.8
 */
public interface IndentStatusService {
	/**
	 * get status name by status id
	 * @param statusId status of id
	 * @return status of name
	 */
	String getIndentStatusNameById(int statusId);

	/**
	 * get all status
	 * @return status
	 */
	List<IndentStatus> getAllList();
}
