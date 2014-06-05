package com.j1987.coffeeroo.services.dao;

import java.util.Date;
import java.util.List;

import com.j1987.coffeeroo.domain.JCoffeeAnalysis;
import com.j1987.coffeeroo.domain.JFactory;

public interface CoffeeAnalysisService {

	public long countCoffeeAnalyses();

	public List<JCoffeeAnalysis> findAllCoffeeAnalyses();

	public JCoffeeAnalysis findCoffeeAnalysis(Long id);

	public List<JCoffeeAnalysis> findCoffeeAnalysisEntries(int firstResult,
			int maxResults);

	public void persist(JCoffeeAnalysis coffeeAnalysis);

	public void remove(Long id);

	public void flush();

	public void clear();

	public JCoffeeAnalysis merge(JCoffeeAnalysis coffeeAnalysis);

	public List<JCoffeeAnalysis> findCoffeeAnalysisByFactoryEntries(JFactory factory,int firstResult, int maxResults);

	public Long CountCoffeeAnalysisByFactory(JFactory factory);

	public List<JCoffeeAnalysis> findCoffeeAnalysisByFactory(JFactory factory);

	public List<JCoffeeAnalysis> findCoffeeAnalysisByFactoryListAndCoffeeAnalysisDateBetween(List<JFactory> factories, Date startDate, Date endDate);

	public List<JCoffeeAnalysis> findCoffeeAnalysisByFactoryListDateBetweenEntries(List<JFactory> factories, Date startDate, Date endDate,int firstResult, int maxResults);

	public List<JCoffeeAnalysis> findCoffeeAnalysisByReferenceEquals(String reference);

	public List<JCoffeeAnalysis> findNotSentCoffeeAnalysisByReferenceList(List<String> referenceList);

	public List<JCoffeeAnalysis> findStandByCoffeeAnalysisByReferenceList(List<String> referenceList);

	public List<JCoffeeAnalysis> findValidatedCoffeeAnalysisByReferenceList(List<String> referenceList);

	public List<JCoffeeAnalysis> findStandByCoffeeAnalysisByFactoryListAndCoffeeAnalysisDateBetween(List<JFactory> factories, Date startDate, Date endDate);

	public List<JCoffeeAnalysis> findNotSentCoffeeAnalysisByFactoryListAndCoffeeAnalysisDateBetween(List<JFactory> factories, Date startDate, Date endDate);

}
