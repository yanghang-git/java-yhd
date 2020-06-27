package com.yhd.pojo;

import java.io.Serializable;

/**
 * 页面提示框
 * @Author 杨航
 * @Date 2020/5/19 15:46
 * @Since 1.8
 */
public class Hint implements Serializable {
    private static final long serialVersionUID = 8269254978809211081L;
    private String title;
    private String value;

    public Hint(){}

    public Hint(String title, String value) {
        this.title = title;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

	@Override
	public String toString() {
		return "{"
				+ "\"title\":\""
				+ title + '\"'
				+ ",\"value\":\""
				+ value + '\"'
				+ "}";
	}
}

