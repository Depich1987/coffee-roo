package com.j1987.coffeeroo.web.form;

import java.io.Serializable;

public class FilterCoffeeAnalysis implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8429343930632693933L;
	private String factoryFilter;
	
	private String analysisReference;
	
	public FilterCoffeeAnalysis() {
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
	
	
}