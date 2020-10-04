package com.yhd.service.personalcenter;

import com.yhd.pojo.GoodsComment;

import java.util.List;

/**
 * @author 杨航
 * @date 2020/9/28 15:33
 * @since 1.8
 */
public interface GoodsCommentService {
	/**
	 * get all goodsComment
	 * @return goodsComment
	 */
	List<GoodsComment> getCommentByUsernameList(String username);

	/**
	 * give goods comment
	 * @param comment comment value
	 * @return is comment success
	 */
	boolean addGoodsComment(String indentId, GoodsComment comment);

}
