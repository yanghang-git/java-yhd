package com.yhd.pojo;

import java.io.Serializable;

/**
 * @Author 杨航
 * @Date 2020/6/27 14:42
 * @Since 1.8
 */
public class Admin implements Serializable {
	private static final long serialVersionUID = -4164449861287126201L;

	private String id;
	private String password;
	private boolean adminPower;
	private boolean userPower;
	private boolean activityPower;
	private boolean indentPower;
	private boolean goodsPower;
	private boolean addressPower;


	public Admin() {}

	public Admin(String id, String password, boolean adminPower, boolean userPower, boolean activityPower, boolean indentPower, boolean goodsPower, boolean addressPower) {
		this.id = id;
		this.password = password;
		this.adminPower = adminPower;
		this.userPower = userPower;
		this.activityPower = activityPower;
		this.indentPower = indentPower;
		this.goodsPower = goodsPower;
		this.addressPower = addressPower;
	}

	@Override
	public String toString() {
		return "{"
				+ "\"id\":\""
				+ id + '\"'
				+ ",\"password\":\""
				+ password + '\"'
				+ ",\"adminPower\":\""
				+ adminPower + '\"'
				+ ",\"userPower\":"
				+ userPower
				+ ",\"activityPower\":"
				+ activityPower
				+ ",\"indentPower\":"
				+ indentPower
				+ ",\"goodsPower\":"
				+ goodsPower
				+ ",\"addressPower\":"
				+ addressPower
				+ "}";
	}

	public boolean getAdminPower() {
		return adminPower;
	}

	public void setAdminPower(boolean adminPower) {
		this.adminPower = adminPower;
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

	public boolean getUserPower() {
		return userPower;
	}

	public void setUserPower(boolean userPower) {
		this.userPower = userPower;
	}

	public boolean getActivityPower() {
		return activityPower;
	}

	public void setActivityPower(boolean activityPower) {
		this.activityPower = activityPower;
	}

	public boolean getIndentPower() {
		return indentPower;
	}

	public void setIndentPower(boolean indentPower) {
		this.indentPower = indentPower;
	}

	public boolean getGoodsPower() {
		return goodsPower;
	}

	public void setGoodsPower(boolean goodsPower) {
		this.goodsPower = goodsPower;
	}

	public boolean getAddressPower() {
		return addressPower;
	}

	public void setAddressPower(boolean addressPower) {
		this.addressPower = addressPower;
	}
}
