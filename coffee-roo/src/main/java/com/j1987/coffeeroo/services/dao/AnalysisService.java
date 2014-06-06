package com.j1987.coffeeroo.services.dao;

import java.util.Date;
import java.util.List;

import com.j1987.coffeeroo.domain.JAnalysis;
import com.j1987.coffeeroo.domain.JBridge;

public interface AnalysisService {
	
	public long countAnalyses(String productType);
	
	public long countAnalysesInFactory(String factoryCode,String productType);
	
	public long countAnalysesInBridge(JBridge bridge,String productType);

	public List<JAnalysis> findAllAnalyses(String productType);

	public JAnalysis findAnalysis(Long id, String productType);

	public List<JAnalysis> findAnalysisEntries(int firstResult,	int maxResults, String productType);

	public void persist(JAnalysis analysis);

	public void remove(Long id);

	public void flush();

	public void clear();

	public JAnalysis merge(JAnalysis analysis);
	
	public void update(JAnalysis analysis);

	public List<JAnalysis> findAnalysisByFactoryCodeEntries(String factoryCode, String productType,	int firstResult, int maxResults);
	
	public List<JAnalysis> findAnalysisByFactoryCode(String factoryCode, String productType);

	public List<JAnalysis> findAnalysisByFactoryCodeListAndAnalysisDateBetween(List<String> factoriesCode , String productType, Date startDate, Date endDate);

	public List<JAnalysis> findAnalysisByFactoryCodeListDateBetweenEntries(List<String> factoriesCode , String productType, Date startDate, Date endDate,int firstResult, int maxResults);

	public List<JAnalysis> findAnalysisByReferenceEquals(String reference);

	public List<JAnalysis> findRefusedAnalysisByReferenceList(List<String> referenceList);

	public List<JAnalysis> findStandByAnalysisByReferenceList(List<String> referenceList);

	public List<JAnalysis> findValidatedAnalysisByReferenceList(List<String> referenceList);

	public List<JAnalysis> findRefusedAnalysisByFactoryCodeListAndAnalysisDateBetween(List<String> factoriesCode , String productType, Date startDate, Date endDate);

	public List<JAnalysis> findAllowedAnalysisByFactoryCodeListAndAnalysisDateBetween(List<String> factoriesCode , String productType, Date startDate, Date endDate);

	public List<JAnalysis> findAnalysisByReferenceEqualsAndProductType(String analysisReference, String cocoaProduct);

	
}
