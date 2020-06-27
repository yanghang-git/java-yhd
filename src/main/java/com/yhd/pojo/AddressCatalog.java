package com.yhd.pojo;

import java.io.Serializable;

/**
 * @Author 杨航
 * @Date 2020/6/27 14:51
 * @Since 1.8
 */
public class AddressCatalog implements Serializable {

	private static final long serialVersionUID = -9187065643491876223L;
	private Integer id;
	private String name;
	private Integer upId;

	public AddressCatalog() {}

	public AddressCatalog(Integer id, String name, Integer upId) {
		this.id = id;
		this.name = name;
		this.upId = upId;
	}

	@Override
	public String toString() {
		return "{"
				+ "\"id\":"
				+ id
				+ ",\"name\":\""
				+ name + '\"'
				+ ",\"upId\":"
				+ upId
				+ "}";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getUpId() {
		return upId;
	}

	public void setUpId(Integer upId) {
		this.upId = upId;
	}
}
