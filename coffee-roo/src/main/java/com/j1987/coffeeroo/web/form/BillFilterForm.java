package com.j1987.coffeeroo.web.form;

import java.io.Serializable;

public class BillFilterForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8429343930632693933L;
	
	private int size;
	private String factoryCode;
	private String startDate;
	private String endDate;
	
	
	public BillFilterForm() {
		// TODO Auto-generated constructor stub
	}

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	
}
