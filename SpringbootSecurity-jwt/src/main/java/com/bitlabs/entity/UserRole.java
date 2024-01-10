package com.bitlabs.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserRole {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "UserRole [id=" + id + ", name=" + name + "]";
	}
	public UserRole(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
