package com.xkjsj.go.domain;

import java.io.Serializable;

public class CarGoodLink implements Serializable{
	private Car car;
	private Good good;
	private Integer amount;
	public CarGoodLink() {
		super();
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
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
