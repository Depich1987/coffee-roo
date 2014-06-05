package com.j1987.coffeeroo.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReportFilterForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4623403080959414252L;
	
	private List<String> factoryCodeList = new ArrayList<String>();
	
	private List<String> analysisList = new ArrayList<String>();
	
	private Boolean automaticSubmission = false;
	
	private String description;
	private	Boolean coffee = true;
	private	Boolean cocoa = true;
	
	private String format;
	
	private String startDate;

	private String endDate;

	public ReportFilterForm() {
		// TODO Auto-generated constructor stub
	}

	public List<String> getFactoryCodeList() {
		return factoryCodeList;
	}

	public void setFactoryCodeList(List<String> factoryCodeList) {
		this.factoryCodeList = factoryCodeList;
	}

	public Boolean getCoffee() {
		return coffee;
	}

	public void setCoffee(Boolean coffee) {
		this.coffee = coffee;
	}

	public Boolean getCocoa() {
		return cocoa;
	}

	public void setCocoa(Boolean cocoa) {
		this.cocoa = cocoa;
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

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public List<String> getAnalysisList() {
		return analysisList;
	}

	public void setAnalysisList(List<String> analysisList) {
		this.analysisList = analysisList;
	}

	public Boolean getAutomaticSubmission() {
		return automaticSubmission;
	}

	public void setAutomaticSubmission(Boolean automaticSubmission) {
		this.automaticSubmission = automaticSubmission;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
