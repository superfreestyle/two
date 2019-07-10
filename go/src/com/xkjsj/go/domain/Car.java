package com.xkjsj.go.domain;
import java.io.Serializable;
import java.util.List;
public class Car implements Serializable{
	private Integer id;
	private Customer customer;
	private List<Good> goods;
	
	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}

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
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
		
}
