package com.j1987.coffeeroo.web.form;

import java.io.Serializable;
import java.util.Date;

public class FilterAnalysisForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8429343930632693933L;
	private String analysisReference;
	private String factoryFilter;
	private String bridgeFilter;
	private String productTypeFilter;
//	private String typeValue;
	private Long areaFilter;
	private Long statusFilter;
	
	private String startDate;
	private String endDate;
	
	private Date sd;
	private Date ed;
	
	private Integer page;
	private Integer size;
	
	private int firstResult;
	private int maxResult;
	
	
	public FilterAnalysisForm() {
		// TODO Auto-generated constructor stub
	}

	public String getFactoryFilter() {
		return factoryFilter;
	}

	public void setFactoryFilter(String factoryFilter) {
		this.factoryFilter = factoryFilter;
	}

	public String getAnalysisReference() {
		return analysisReference;
	}

	public void setAnalysisReference(String analysisReference) {
		this.analysisReference = analysisReference;
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

	public String getBridgeFilter() {
		return bridgeFilter;
	}

	public void setBridgeFilter(String bridgeFilter) {
		this.bridgeFilter = bridgeFilter;
	}

	public Long getAreaFilter() {
		return areaFilter;
	}

	public void setAreaFilter(Long areaFilter) {
		this.areaFilter = areaFilter;
	}

	public Long getStatusFilter() {
		return statusFilter;
	}

	public void setStatusFilter(Long statusFilter) {
		this.statusFilter = statusFilter;
	}

	public String getProductTypeFilter() {
		return productTypeFilter;
	}

	public void setProductTypeFilter(String productTypeFilter) {
		this.productTypeFilter = productTypeFilter;
	}

	public Date getSd() {
		return sd;
	}

	public void setSd(Date sd) {
		this.sd = sd;
	}

	public Date getEd() {
		return ed;
	}

	public void setEd(Date ed) {
		this.ed = ed;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}
	
	
	 
}
