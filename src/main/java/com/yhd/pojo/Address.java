package com.yhd.pojo;

import java.io.Serializable;

/**
 * @Author 杨航
 * @Date 2020/6/27 14:49
 * @Since 1.8
 */
public class Address implements Serializable {
	private static final long serialVersionUID = 163777329761168777L;
	private Integer id;
	private String userId;
	private String username;
	private String phone;
	private Integer city;
	private Integer county;
	private Integer street;
	private String detail;

	public Address(){}

	public Address(Integer id, String userId, String username, String phone, Integer city, Integer county, Integer street, String detail) {
		this.id = id;
		this.userId = userId;
		this.username = username;
		this.phone = phone;
		this.city = city;
		this.county = county;
		this.street = street;
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "{"
				+ "\"id\":"
				+ id
				+ ",\"userId\":\""
				+ userId + '\"'
				+ ",\"username\":\""
				+ username + '\"'
				+ ",\"phone\":\""
				+ phone + '\"'
				+ ",\"city\":\""
				+ city + '\"'
				+ ",\"county\":\""
				+ county + '\"'
				+ ",\"street\":\""
				+ street + '\"'
				+ ",\"detail\":\""
				+ detail + '\"'
				+ "}";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getCounty() {
		return county;
	}

	public void setCounty(Integer county) {
		this.county = county;
	}

	public Integer getStreet() {
		return street;
	}

	public void setStreet(Integer street) {
		this.street = street;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
