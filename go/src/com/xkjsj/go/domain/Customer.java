package com.xkjsj.go.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
public class Customer implements Serializable{
	private Integer id;
	//private Car car;
	private String username;
	private String password;
	private Integer paynumber;
	private String address;
	private String sex;
	private Integer age;
	private String telnumber;
	private String email;
	private Integer status;
	private Date createDate;
	private List<Order> orders;

	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public Customer() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	/*public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}*/
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getPaynumber() {
		return paynumber;
	}
	public void setPaynumber(Integer paynumber) {
		this.paynumber = paynumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getTelnumber() {
		return telnumber;
	}
	public void setTelnumber(String telnumber) {
		this.telnumber = telnumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
}
