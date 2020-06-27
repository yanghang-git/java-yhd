package com.yhd.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author 杨航
 * @Date 2020/6/27 15:02
 * @Since 1.8
 */
public class GoodsComment implements Serializable {
	private static final long serialVersionUID = -840447079731888257L;

	private String userId;
	private Integer goodsId;
	private Integer level;
	private String comment;
	private Timestamp orderTime;

	public GoodsComment() {}

	public GoodsComment(String userId, Integer goodsId, Integer level, String comment, Timestamp orderTime) {
		this.userId = userId;
		this.goodsId = goodsId;
		this.level = level;
		this.comment = comment;
		this.orderTime = orderTime;
	}

	@Override
	public String toString() {
		return "{"
				+ "\"userId\":\""
				+ userId + '\"'
				+ ",\"goodsId\":"
				+ goodsId
				+ ",\"level\":"
				+ level
				+ ",\"comment\":\""
				+ comment + '\"'
				+ ",\"orderTime\":\""
				+ orderTime + '\"'
				+ "}";
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
