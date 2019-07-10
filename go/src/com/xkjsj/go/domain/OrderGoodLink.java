package com.xkjsj.go.domain;

public class OrderGoodLink {
	private Order order;
	private Good good;
	private Integer amount;
	public OrderGoodLink() {
		super();
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Good getGood() {
		return good;
	}
	public void setGood(Good good) {
		this.good = good;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
