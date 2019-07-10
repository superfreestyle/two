package com.xkjsj.go.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable{
	private Integer id;
	private Integer code;
	private String total;
	private Integer status;
	private Date createDate;
	private String address;
	private String remark;
	private  Customer customer;
	private List<Good> goods;
	public Order() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<Good> getGoods() {
		return goods;
	}
	public void setGoods(List<Good> goods) {
		this.goods = goods;
	}

}
