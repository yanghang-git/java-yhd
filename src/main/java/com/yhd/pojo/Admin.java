package com.yhd.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author 杨航
 * @Date 2020/6/27 14:42
 * @Since 1.8
 */
public class Admin implements Serializable {
	private static final long serialVersionUID = -4164449861287126201L;

	private String id;
	private String password;
	private BigDecimal money;

	public Admin() {}
	public Admin(String id, String password, BigDecimal money) {
		this.id = id;
		this.password = password;
		this.money = money;
	}

	@Override
	public String toString() {
		return "{"
				+ "\"id\":"
				+ id
				+ ",\"password\":\""
				+ password + '\"'
				+ ",\"money\":"
				+ money
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

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}
}
