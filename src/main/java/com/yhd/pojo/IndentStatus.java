package com.yhd.pojo;

import java.io.Serializable;

/**
 * @Author 杨航
 * @Date 2020/6/27 15:06
 * @Since 1.8
 */
public class IndentStatus implements Serializable {
	private static final long serialVersionUID = 5888327872752352994L;

	private Integer id;
	private String statusName;

	public IndentStatus() {}

	public IndentStatus(Integer id, String statusName) {
		this.id = id;
		this.statusName = statusName;
	}


	@Override
	public String toString() {
		return "{"
				+ "\"id\":"
				+ id
				+ ",\"statusName\":\""
				+ statusName + '\"'
				+ "}";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}
