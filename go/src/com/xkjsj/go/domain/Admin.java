package com.xkjsj.go.domain;

import java.io.Serializable;

public class Admin implements Serializable{
	private Integer id;
	private String username;
	private String password;
	private String telnumber;
	public Admin() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public String getTelnumber() {
		return telnumber;
	}
	public void setTelnumber(String telnumber) {
		this.telnumber = telnumber;
	}
	
}
