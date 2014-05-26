package com.j1987.coffeeroo.services.dao;

import java.util.Date;
import java.util.List;

import com.j1987.coffeeroo.domain.JCoffeeAnalysis;
import com.j1987.coffeeroo.domain.JFactory;

public interface CoffeeAnalysisService {

	long countCoffeeAnalyses();

	List<JCoffeeAnalysis> findAllCoffeeAnalyses();

	JCoffeeAnalysis findCoffeeAnalysis(Long id);

	List<JCoffeeAnalysis> findCoffeeAnalysisEntries(int firstResult,
			int maxResults);

	void persist(JCoffeeAnalysis coffeeAnalysis);

	void remove(Long id);

	void flush();

	void clear();

	JCoffeeAnalysis merge(JCoffeeAnalysis coffeeAnalysis);

	List<JCoffeeAnalysis> findCoffeeAnalysisByFactoryEntries(JFactory factory,
			int firstResult, int maxResults);

	Long CountCoffeeAnalysisByFactory(JFactory factory);

	List<JCoffeeAnalysis> findCoffeeAnalysisByFactory(JFactory factory);

	List<JCoffeeAnalysis> findCoffeeAnalysisByFactoryListAndCoffeeAnalysisDateBetween(
			List<JFactory> factories, Date startDate, Date endDate);

	List<JCoffeeAnalysis> findCoffeeAnalysisByFactoryListDateBetweenEntries(
			List<JFactory> factories, Date startDate, Date endDate,
			int firstResult, int maxResults);

	List<JCoffeeAnalysis> findCoffeeAnalysisByReferenceEquals(String reference);

}
