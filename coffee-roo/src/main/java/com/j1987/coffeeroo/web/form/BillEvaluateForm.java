package com.j1987.coffeeroo.web.form;

import java.io.Serializable;

public class BillEvaluateForm implements Serializable{
	
	/**
	 */
	private String factoryFilter;
	
	/**
	 */
	private String productTypeFilter;
	
	/**
	 */
	private Long month;
	
	/**
	 */
	private Long year;

	/**
	 * 
	 */
	private static final long serialVersionUID = -4577113150162170037L;
	
	public BillEvaluateForm() {
		// TODO Auto-generated constructor stub
	}

	public String getFactoryFilter() {
		return factoryFilter;
	}

	public void setFactoryFilter(String factoryFilter) {
		this.factoryFilter = factoryFilter;
	}

	public String getProductTypeFilter() {
		return productTypeFilter;
	}

	public void setProductTypeFilter(String productTypeFilter) {
		this.productTypeFilter = productTypeFilter;
	}

	public Long getMonth() {
		return month;
	}

	public void setMonth(Long month) {
		this.month = month;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	
}
