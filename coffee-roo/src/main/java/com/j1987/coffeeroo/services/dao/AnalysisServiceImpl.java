package com.j1987.coffeeroo.services.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.j1987.coffeeroo.domain.JAnalysis;
import com.j1987.coffeeroo.domain.JBridge;
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
        if (id == null) return null;
        return entityManager.find(JAnalysis.class, id);
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findAnalysisEntries(int, int, java.lang.String)
	 */
	@Override
	public List<JAnalysis> findAnalysisEntries(int firstResult, int maxResults,String productType) {
		
		return entityManager.createQuery("SELECT o FROM JAnalysis o WHERE o.productType = :productType ORDER BY o.creationDate DESC", JAnalysis.class)
				.setParameter("productType", productType)
				.setFirstResult(firstResult)
				.setMaxResults(maxResults)
				.getResultList();
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#persist(com.j1987.coffeeroo.domain.JAnalysis)
	 */
	@Override
	@Transactional
	public void persist(JAnalysis analysis) {
		// TODO Auto-generated method stub
		this.entityManager.persist(analysis);
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
		this.entityManager.flush();		
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#clear()
	 */
	@Override
	public void clear() {
		this.entityManager.clear();		
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#merge(com.j1987.coffeeroo.domain.JAnalysis)
	 */
	@Override
	public JAnalysis merge(JAnalysis analysis) {
        
		JAnalysis merged = this.entityManager.merge(analysis);
        this.entityManager.flush();
        return merged;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(JAnalysis analysis){
		String updateSQLText = "UPDATE JAnalysis d SET d.reference = :reference,"+
								
								"d.status = :status,"+
								"WHERE d.id= :id";
		this.entityManager.createQuery(updateSQLText)
						.setParameter("id", analysis.getId())
						.setParameter("reference", analysis.getReference())
						.setParameter("status", analysis.getStatus())
						.executeUpdate();
	}


	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findAnalysisByFactoryEntries(com.j1987.coffeeroo.domain.JFactory, int, int)
	 */
	@Override
	public List<JAnalysis> findAnalysisByFactoryCodeEntries(String factoryCode, String productType,	int firstResult, int maxResults) {
		return entityManager.createQuery("SELECT o FROM JAnalysis o WHERE o.factoryCode = :factoryCode AND o.productType = :productType ORDER BY o.creationDate DESC", JAnalysis.class)
				.setParameter("factoryCode", factoryCode)
				.setParameter("productType", productType)
				.setFirstResult(firstResult)
				.setMaxResults(maxResults)
				.getResultList();
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findAnalysisByFactory(com.j1987.coffeeroo.domain.JFactory)
	 */
	@Override
	public List<JAnalysis> findAnalysisByFactoryCode(String factoryCode, String productType) {
		return entityManager.createQuery("SELECT o FROM JAnalysis o WHERE o.factoryCode = :factoryCode AND o.productType = :productType ORDER BY o.creationDate DESC", JAnalysis.class)
				.setParameter("factoryCode", factoryCode)
				.setParameter("productType", productType)
				.getResultList();
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findAnalysisByFactoryCodeListAndAnalysisDateBetween(java.util.List, java.util.Date, java.util.Date)
	 */
	@Override
	public List<JAnalysis> findAnalysisByFactoryCodeListAndAnalysisDateBetween(
			List<String> factoriesCode , String productType, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findAnalysisByFactoryCodeListDateBetweenEntries(java.util.List, java.util.Date, java.util.Date, int, int)
	 */
	@Override
	public List<JAnalysis> findAnalysisByFactoryCodeListDateBetweenEntries(
			List<String> factoriesCode , String productType, Date startDate, Date endDate,
			int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findAnalysisByReferenceEquals(java.lang.String)
	 */
	@Override
	public List<JAnalysis> findAnalysisByReferenceEquals(String reference) {
    	return entityManager.createQuery("SELECT o FROM JAnalysis o WHERE o.reference = :reference", JAnalysis.class)
    			.setParameter("reference", reference)
    			.getResultList();
	}
	
	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findAnalysisByReferenceEquals(java.lang.String)
	 */
	@Override
	public List<JAnalysis> findAnalysisByReferenceEqualsAndProductType(String reference,String productType) {
    	return entityManager.createQuery("SELECT o FROM JAnalysis o WHERE o.reference = :reference AND o.productType = :productType", JAnalysis.class)
    			.setParameter("reference", reference)
    			.setParameter("productType", productType)
    			.getResultList();
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findNotSentAnalysisByReferenceList(java.util.List)
	 */
	@Override
	public List<JAnalysis> findRefusedAnalysisByReferenceList(List<String> referenceList) {
    	return entityManager.createQuery("SELECT o FROM JAnalysis o WHERE (o.reference IN :referenceList) AND o.status = :status", JAnalysis.class)
    			.setParameter("referenceList", referenceList)
    			.setParameter("status", false)
    			.getResultList();
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
	public List<JAnalysis> findValidatedAnalysisByReferenceList(List<String> referenceList) {
    	return entityManager.createQuery("SELECT o FROM JAnalysis o WHERE (o.reference IN :referenceList) AND o.status = :status", JAnalysis.class)
    			.setParameter("referenceList", referenceList)
    			.setParameter("status", true)
    			.getResultList();
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findStandByAnalysisByFactoryCodeListAndAnalysisDateBetween(java.util.List, java.util.Date, java.util.Date)
	 */
	@Override
	public List<JAnalysis> findRefusedAnalysisByFactoryCodeListAndAnalysisDateBetween(
			List<String> factoriesCode, String productType, Date startDate, Date endDate) {
		
	   	if (startDate == null) throw new IllegalArgumentException("The startDate argument is required");
        if (endDate == null) throw new IllegalArgumentException("The endDate argument is required");
        
        Date endDateTemp = DateUtils.addDays(endDate, 1);
        
        return entityManager.createQuery("SELECT p FROM JAnalysis AS p WHERE (p.factoryCode IN :factoriesCode) AND (p.status = :status) AND p.dateOfAnalysis BETWEEN :startDate AND :endDate ORDER BY   p.creationDate DESC", JAnalysis.class)
		.setParameter("factoriesCode", factoriesCode)
		.setParameter("startDate", startDate, TemporalType.DATE)
        .setParameter("endDate", endDateTemp, TemporalType.DATE)
        .setParameter("status", false)
        .getResultList();
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findNotSentAnalysisByFactoryCodeListAndAnalysisDateBetween(java.util.List, java.util.Date, java.util.Date)
	 */
	@Override
	public List<JAnalysis> findAllowedAnalysisByFactoryCodeListAndAnalysisDateBetween(
			List<String> factoriesCode, String productType, Date startDate, Date endDate) {
	   	if (startDate == null) throw new IllegalArgumentException("The startDate argument is required");
        if (endDate == null) throw new IllegalArgumentException("The endDate argument is required");
        
        Date endDateTemp = DateUtils.addDays(endDate, 1);
        
        return entityManager.createQuery("SELECT p FROM JAnalysis AS p WHERE (p.factoryCode IN :factoriesCode) AND (p.status = :status) AND p.dateOfAnalysis BETWEEN :startDate AND :endDate ORDER BY   p.creationDate DESC", JAnalysis.class)
		.setParameter("factoriesCode", factoriesCode)
		.setParameter("startDate", startDate, TemporalType.DATE)
        .setParameter("endDate", endDateTemp, TemporalType.DATE)
        .setParameter("status", true)
        .getResultList();
	}



	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
