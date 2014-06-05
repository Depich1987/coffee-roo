package com.j1987.coffeeroo.services.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.j1987.coffeeroo.domain.JAnalysis;
import com.j1987.coffeeroo.domain.JBridge;
import com.j1987.coffeeroo.domain.JFactory;
import com.j1987.coffeeroo.framework.JUtils;

public class AnalysisServiceImpl implements AnalysisService{
	
	@PersistenceContext(name=JUtils.DB_PERSISTENCE_UNIT)
	private EntityManager entityManager;

	public AnalysisServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#countAnalyses(java.lang.String)
	 */
	@Override
	public long countAnalyses(String productType) {
		
		return entityManager.createQuery("SELECT COUNT(o) FROM JAnalysis o WHERE o.productType = :productType", Long.class)
				.setParameter("productType", productType)
				.getSingleResult();
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#countAnalysesInFactory(java.lang.String, java.lang.String)
	 */
	@Override
	public long countAnalysesInFactory(String factoryCode, String productType) {
		
		return entityManager.createQuery("SELECT COUNT(o) FROM JAnalysis o WHERE o.productType = :productType AND o.factoryCode = :factoryCode", Long.class)
				.setParameter("productType", productType)
				.setParameter("factoryCode", factoryCode)
				.getSingleResult();
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#countAnalysesInBridge(com.j1987.coffeeroo.domain.JBridge, java.lang.String)
	 */
	@Override
	public long countAnalysesInBridge(JBridge bridge, String productType) {
		
		return entityManager.createQuery("SELECT COUNT(o) FROM JAnalysis o WHERE o.productType = :productType AND o.bridge = :bridge", Long.class)
				.setParameter("productType", productType)
				.setParameter("bridge", bridge)
				.getSingleResult();
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findAllAnalyses(java.lang.String)
	 */
	@Override
	public List<JAnalysis> findAllAnalyses(String productType) {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findAnalysis(java.lang.Long, java.lang.String)
	 */
	@Override
	public JAnalysis findAnalysis(Long id, String productType) {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findAnalysisEntries(int, int, java.lang.String)
	 */
	@Override
	public List<JAnalysis> findAnalysisEntries(int firstResult, int maxResults,
			String productType) {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#persist(com.j1987.coffeeroo.domain.JAnalysis)
	 */
	@Override
	public void persist(JAnalysis analysis) {
		// TODO Auto-generated method stub
		
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#remove(java.lang.Long)
	 */
	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub
		
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#flush()
	 */
	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#clear()
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#merge(com.j1987.coffeeroo.domain.JAnalysis)
	 */
	@Override
	public JAnalysis merge(JAnalysis coffeeAnalysis) {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findAnalysisByFactoryEntries(com.j1987.coffeeroo.domain.JFactory, int, int)
	 */
	@Override
	public List<JAnalysis> findAnalysisByFactoryEntries(JFactory factory,
			int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findAnalysisByFactory(com.j1987.coffeeroo.domain.JFactory)
	 */
	@Override
	public List<JAnalysis> findAnalysisByFactory(JFactory factory) {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findAnalysisByFactoryCodeListAndAnalysisDateBetween(java.util.List, java.util.Date, java.util.Date)
	 */
	@Override
	public List<JAnalysis> findAnalysisByFactoryCodeListAndAnalysisDateBetween(
			List<String> factoriesCode, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findAnalysisByFactoryCodeListDateBetweenEntries(java.util.List, java.util.Date, java.util.Date, int, int)
	 */
	@Override
	public List<JAnalysis> findAnalysisByFactoryCodeListDateBetweenEntries(
			List<String> factoriesCode, Date startDate, Date endDate,
			int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findAnalysisByReferenceEquals(java.lang.String)
	 */
	@Override
	public List<JAnalysis> findAnalysisByReferenceEquals(String reference) {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findNotSentAnalysisByReferenceList(java.util.List)
	 */
	@Override
	public List<JAnalysis> findNotSentAnalysisByReferenceList(
			List<String> referenceList) {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findStandByAnalysisByReferenceList(java.util.List)
	 */
	@Override
	public List<JAnalysis> findStandByAnalysisByReferenceList(
			List<String> referenceList) {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findValidatedAnalysisByReferenceList(java.util.List)
	 */
	@Override
	public List<JAnalysis> findValidatedAnalysisByReferenceList(
			List<String> referenceList) {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findStandByAnalysisByFactoryCodeListAndAnalysisDateBetween(java.util.List, java.util.Date, java.util.Date)
	 */
	@Override
	public List<JAnalysis> findStandByAnalysisByFactoryCodeListAndAnalysisDateBetween(
			List<String> factoriesCode, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findNotSentAnalysisByFactoryCodeListAndAnalysisDateBetween(java.util.List, java.util.Date, java.util.Date)
	 */
	@Override
	public List<JAnalysis> findNotSentAnalysisByFactoryCodeListAndAnalysisDateBetween(
			List<String> factoriesCode, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}



	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
