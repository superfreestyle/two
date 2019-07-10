package com.xkjsj.go.domain;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable{
	private Integer id;
	private String name;
	private String remark;
	private List<Good> goods;
	public List<Good> getGoods() {
		return goods;
	}
	public void setGoods(List<Good> goods) {
		this.goods = goods;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Category() {
		super();
	}
}
