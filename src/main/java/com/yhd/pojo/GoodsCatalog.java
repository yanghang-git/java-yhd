package com.yhd.pojo;

import java.io.Serializable;

/**
 * @Author 杨航
 * @Date 2020/6/27 15:00
 * @Since 1.8
 */
public class GoodsCatalog implements Serializable {

	private static final long serialVersionUID = 7019633352482556782L;

	private Integer id;
	private String name;
	private Integer upId;

	public GoodsCatalog() {}
	public GoodsCatalog(Integer id, String name, Integer upId) {
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

	public static long getSerialVersionUID() {
		return serialVersionUID;
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
