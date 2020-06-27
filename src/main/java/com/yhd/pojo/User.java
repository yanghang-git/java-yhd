package com.yhd.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author 杨航
 * @Date 2020/6/27 15:07
 * @Since 1.8
 */
public class User implements Serializable {

	private static final long serialVersionUID = -7211346695867057886L;

	private String id;
	private String password;
	private String name;
	private String sex;
	private Timestamp birthday;
	private String email;
	private String phone;
	private Integer addressId;
	private boolean freeze;

	public User() {}

	public User(String id, String password, String name, String sex, Timestamp birthday, String email, String phone, Integer addressId, boolean freeze) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.email = email;
		this.phone = phone;
		this.addressId = addressId;
		this.freeze = freeze;
	}


	@Override
	public String toString() {
		return "{"
				+ "\"id\":\""
				+ id + '\"'
				+ ",\"password\":\""
				+ password + '\"'
				+ ",\"name\":\""
				+ name + '\"'
				+ ",\"sex\":\""
				+ sex + '\"'
				+ ",\"birthday\":\""
				+ birthday + '\"'
				+ ",\"email\":\""
				+ email + '\"'
				+ ",\"phone\":\""
				+ phone + '\"'
				+ ",\"addressId\":"
				+ addressId
				+ ",\"freeze\":"
				+ freeze
				+ "}";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Timestamp getBirthday() {
		return birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public boolean isFreeze() {
		return freeze;
	}

	public void setFreeze(boolean freeze) {
		this.freeze = freeze;
	}
}
