package com.j1987.coffeeroo.web.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class AssignFactoryForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1484053414632375199L;


	@NotNull
	private String userName;
	
	
	private String factoryCode;

	public AssignFactoryForm() {
		// TODO Auto-generated constructor stub
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	
}
