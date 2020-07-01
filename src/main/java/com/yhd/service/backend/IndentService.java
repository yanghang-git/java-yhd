package com.yhd.service.backend;

import com.yhd.pojo.Indent;

import java.sql.Connection;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/29 13:42
 * @Since 1.8
 */
public interface IndentService {

	/**
	 * get Indent all by User of id and Goods of id
	 * @param userId User id
	 * @param goodsId Goods id
	 * @return Indent instance gather List
	 */
	List<Indent> getListByUserIdAndId(String userId, String goodsId);

	/**
	 * get Indent all by User of id
	 * @param userId User id
	 * @return Indent instance gather List
	 */
	List<Indent> getListByUserId(String userId);

	/**
	 * get Indent all
	 * @return Indent instance gather List
	 */
	List<Indent> getListAll();

	/**
	 * get Indent all by id
	 * @param id id
	 * @return Indent instance
	 */
	Indent getIndentById(String id);

	/**
	 * update Indent
	 * @param indent instance
	 * @return  true: update success or false: update fail
	 */
	boolean updateIndent(Indent indent);

	/**
	 * add Indent
	 * @param indent instance
	 * @return true: add success or false: add fail
	 */
	boolean addIndent(Indent indent);

	/**
	 * remove Indent by id
	 * @param id Indent id
	 * @return true: remove success or false: remove fail
	 */
	boolean removeById(String id);
}
