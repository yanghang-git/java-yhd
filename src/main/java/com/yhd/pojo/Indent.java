package com.yhd.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Author 杨航
 * @Date 2020/6/27 15:03
 * @Since 1.8
 */
public class Indent implements Serializable {

	private static final long serialVersionUID = -7174382118398765022L;
	private String id;
	private String userId;
	private String goodsId;
	private Integer statusId;
	private String buyNumber;
	private String type;
	private BigDecimal totalPrices;
	private Integer addressId;
	private Timestamp orderTime;

	public Indent() {}

	public Indent(String id, String userId, String goodsId, Integer statusId, String buyNumber, String type, BigDecimal totalPrices, Integer addressId, Timestamp orderTime) {
		this.id = id;
		this.userId = userId;
		this.goodsId = goodsId;
		this.statusId = statusId;
		this.buyNumber = buyNumber;
		this.type = type;
		this.totalPrices = totalPrices;
		this.addressId = addressId;
		this.orderTime = orderTime;
	}

	@Override
	public String toString() {
		return "{"
				+ "\"id\":\""
				+ id + '\"'
				+ ",\"userId\":\""
				+ userId + '\"'
				+ ",\"goodsId\":"
				+ goodsId
				+ ",\"statusId\":"
				+ statusId
				+ ",\"buyNumber\":"
				+ buyNumber
				+ ",\"type\":\""
				+ type + '\"'
				+ ",\"totalPrices\":"
				+ totalPrices
				+ ",\"addressId\":"
				+ addressId
				+ ",\"orderTime\":\""
				+ orderTime + '\"'
				+ "}";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getBuyNumber() {
		return buyNumber;
	}

	public void setBuyNumber(String buyNumber) {
		this.buyNumber = buyNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getTotalPrices() {
		return totalPrices;
	}

	public void setTotalPrices(BigDecimal totalPrices) {
		this.totalPrices = totalPrices;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}
}
