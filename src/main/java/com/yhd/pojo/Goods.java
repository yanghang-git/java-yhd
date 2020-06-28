package com.yhd.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author 杨航
 * @Date 2020/6/27 14:56
 * @Since 1.8
 */
public class Goods implements Serializable {
	private static final long serialVersionUID = -5297397240471735225L;

	private Integer id;
	private String name;
	private BigDecimal price;
	private String style;
	private String kind;
	private String content;
	private String imagePrimary;
	private String imageDetails;
	private Integer number;
	private Integer catalogId;


	public Goods() {}

	public Goods(Integer id, String name, BigDecimal price,String style, String kind, String content, String imagePrimary, String imageDetails, Integer number, Integer catalogId) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.style = style;
		this.kind = kind;
		this.content = content;
		this.imagePrimary = imagePrimary;
		this.imageDetails = imageDetails;
		this.number = number;
		this.catalogId = catalogId;
	}

	@Override
	public String toString() {
		return "{"
				+ "\"id\":"
				+ id
				+ ",\"name\":\""
				+ name + '\"'
				+ ",\"price\":"
				+ price + '\"'
				+ ",\"style\":\""
				+ style + '\"'
				+ ",\"kind\":\""
				+ kind + '\"'
				+ ",\"content\":\""
				+ content + '\"'
				+ ",\"imagePrimary\":\""
				+ imagePrimary + '\"'
				+ ",\"imageDetails\":\""
				+ imageDetails + '\"'
				+ ",\"number\":"
				+ number
				+ ",\"catalogId\":"
				+ catalogId
				+ "}";
	}

	public Integer getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImagePrimary() {
		return imagePrimary;
	}

	public void setImagePrimary(String imagePrimary) {
		this.imagePrimary = imagePrimary;
	}

	public String getImageDetails() {
		return imageDetails;
	}

	public void setImageDetails(String imageDetails) {
		this.imageDetails = imageDetails;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
}
