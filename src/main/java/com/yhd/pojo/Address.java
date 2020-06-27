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
	private String city;
	private String county;
	private String street;
	private String detail;

	public Address(){}

	public Address(Integer id, String userId, String username, String phone, String city, String county, String street, String detail) {
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
