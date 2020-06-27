package com.yhd.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Author 杨航
 * @Date 2020/6/27 14:52
 * @Since 1.8
 */
public class BuyRecord implements Serializable {
	private static final long serialVersionUID = -28477911687959360L;

	private String id;
	private String userId;
	private String goodsName;
	private String buyNumber;
	private String type;
	private BigDecimal totalPrices;
	private String addressName;
	private String addressPhone;
	private String address;
	private Timestamp takeTime;

	public BuyRecord() {}

	public BuyRecord(String id, String userId, String goodsName, String buyNumber, String type, BigDecimal totalPrices, String addressName, String addressPhone, String address, Timestamp takeTime) {
		this.id = id;
		this.userId = userId;
		this.goodsName = goodsName;
		this.buyNumber = buyNumber;
		this.type = type;
		this.totalPrices = totalPrices;
		this.addressName = addressName;
		this.addressPhone = addressPhone;
		this.address = address;
		this.takeTime = takeTime;
	}


	@Override
	public String toString() {
		return "{"
				+ "\"id\":\""
				+ id + '\"'
				+ ",\"userId\":\""
				+ userId + '\"'
				+ ",\"goodsName\":\""
				+ goodsName + '\"'
				+ ",\"buyNumber\":\""
				+ buyNumber + '\"'
				+ ",\"type\":\""
				+ type + '\"'
				+ ",\"totalPrices\":"
				+ totalPrices
				+ ",\"addressName\":\""
				+ addressName + '\"'
				+ ",\"addressPhone\":\""
				+ addressPhone + '\"'
				+ ",\"address\":\""
				+ address + '\"'
				+ ",\"takeTime\":\""
				+ takeTime + '\"'
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

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getAddressPhone() {
		return addressPhone;
	}

	public void setAddressPhone(String addressPhone) {
		this.addressPhone = addressPhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getTakeTime() {
		return takeTime;
	}

	public void setTakeTime(Timestamp takeTime) {
		this.takeTime = takeTime;
	}
}
