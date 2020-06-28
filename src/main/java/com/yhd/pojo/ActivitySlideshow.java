package com.yhd.pojo;

import java.io.Serializable;

/**
 * @Author 杨航
 * @Date 2020/6/27 14:46
 * @Since 1.8
 */
public class ActivitySlideshow implements Serializable {
	private static final long serialVersionUID = 6779026118842029746L;
	private Integer id;
	private String image;
	private String font;
	private String url;

	public ActivitySlideshow() {}

	public ActivitySlideshow(Integer id, String image, String font, String url) {
		this.id = id;
		this.image = image;
		this.font = font;
		this.url = url;
	}

	@Override
	public String toString() {
		return "{"
				+ "\"id\":"
				+ id
				+ ",\"image\":\""
				+ image + '\"'
				+ ",\"font\":\""
				+ font + '\"'
				+ ",\"url\":\""
				+ url + '\"'
				+ "}";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
