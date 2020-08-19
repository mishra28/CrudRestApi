package com.example.demo.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class Invoice {

	@Id
	private int aid;
	private String aname;

	@Size(min = 10, max = 10)
	@NotNull
	private String mobileno;

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	@Override
	public String toString() {
		return "Invoice [aid=" + aid + ", aname=" + aname + "]";
	}

}
