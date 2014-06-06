package com.j1987.coffeeroo.web.json;

import java.io.Serializable;

public class FactoryJSON implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8293233270594440115L;

	private String code;
	
	private String name;

	public FactoryJSON() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	

}
