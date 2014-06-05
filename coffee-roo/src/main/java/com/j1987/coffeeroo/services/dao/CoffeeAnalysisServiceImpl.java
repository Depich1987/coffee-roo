package com.j1987.coffeeroo.services.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.transaction.annotation.Transactional;

import com.j1987.coffeeroo.domain.JCoffeeAnalysis;
import com.j1987.coffeeroo.domain.JFactory;
import com.j1987.coffeeroo.framework.JUtils;

public class CoffeeAnalysisServiceImpl implements CoffeeAnalysisService {
	
	@PersistenceContext(name=JUtils.DB_PERSISTENCE_UNIT)
	private EntityManager entityManager;

	public CoffeeAnalysisServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public long countCoffeeAnalyses() {
        return entityManager.createQuery("SELECT COUNT(o) FROM JCoffeeAnalysis o", Long.class).getSingleResult();
    }
    
	@Override
    public List<JCoffeeAnalysis> findAllCoffeeAnalyses() {
        return entityManager.createQuery("SELECT o FROM JCoffeeAnalysis o ORDER BY o.creationDate DESC", JCoffeeAnalysis.class).getResultList();
    }
    
    @Override
    public JCoffeeAnalysis findCoffeeAnalysis(Long id) {
        if (id == null) return null;
        return entityManager.find(JCoffeeAnalysis.class, id);
    }
    
    @Override
    public List<JCoffeeAnalysis> findCoffeeAnalysisByReferenceEquals(String reference){
    	return entityManager.createQuery("SELECT o FROM JCoffeeAnalysis o WHERE o.reference = :reference", JCoffeeAnalysis.class)
    			.setParameter("reference", reference)
    			.getResultList();
    }
    
    @Override
    public List<JCoffeeAnalysis> findNotSentCoffeeAnalysisByReferenceList(List<String> referenceList){
    	return entityManager.createQuery("SELECT o FROM JCoffeeAnalysis o WHERE (o.reference IN :referenceList) AND o.status = :status", JCoffeeAnalysis.class)
    			.setParameter("referenceList", referenceList)
    			.setParameter("status",Long.valueOf("0"))
    			.getResultList();
    }
    
    @Override
    public List<JCoffeeAnalysis> findStandByCoffeeAnalysisByReferenceList(List<String> referenceList){
    	return entityManager.createQuery("SELECT o FROM JCoffeeAnalysis o WHERE (o.reference IN :referenceList) AND o.status = :status", JCoffeeAnalysis.class)
    			.setParameter("referenceList", referenceList)
    			.setParameter("status",Long.valueOf("1"))
    			.getResultList();
    }
    
    @Override
    public List<JCoffeeAnalysis> findValidatedCoffeeAnalysisByReferenceList(List<String> referenceList){
    	return entityManager.createQuery("SELECT o FROM JCoffeeAnalysis o WHERE (o.reference IN :referenceList) AND o.status = :status", JCoffeeAnalysis.class)
    			.setParameter("referenceList", referenceList)
    			.setParameter("status",Long.valueOf("2"))
    			.getResultList();
    }
    
    @Override
    public List<JCoffeeAnalysis> findCoffeeAnalysisEntries(int firstResult, int maxResults) {
        return entityManager.createQuery("SELECT o FROM JCoffeeAnalysis o ORDER BY o.creationDate DESC", JCoffeeAnalysis.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Override
    public List<JCoffeeAnalysis> findCoffeeAnalysisByFactoryEntries(JFactory factory,int firstResult, int maxResults) {
        return entityManager.createQuery("SELECT o FROM JCoffeeAnalysis o WHERE o.factoryEntry = :factory ORDER BY o.creationDate DESC", JCoffeeAnalysis.class)
        		.setParameter("factory", factory)
        		.setFirstResult(firstResult)
        		.setMaxResults(maxResults)
        		.getResultList();
    }
    
    @Override
    public List<JCoffeeAnalysis> findCoffeeAnalysisByFactory(JFactory factory) {
        return entityManager.createQuery("SELECT o FROM JCoffeeAnalysis o WHERE o.factoryEntry = :factory ORDER BY o.creationDate DESC", JCoffeeAnalysis.class)
        		.setParameter("factory", factory)
        		.getResultList();
    }
    
    @Override
    public Long CountCoffeeAnalysisByFactory(JFactory factory) {
        return entityManager.createQuery("SELECT COUNT(o) FROM JCoffeeAnalysis o WHERE o.factoryEntry = :factory ORDER BY o.creationDate DESC", Long.class)
        		.setParameter("factory", factory)
        		.getSingleResult();
    }
    
    @Override
    public List<JCoffeeAnalysis> findCoffeeAnalysisByFactoryListDateBetweenEntries(List<JFactory> factories, Date startDate, Date endDate,	int firstResult, int maxResults) {


        if (startDate == null) throw new IllegalArgumentException("The startDate argument is required");
        if (endDate == null) throw new IllegalArgumentException("The endDate argument is required");
        
        Date endDateTemp = DateUtils.addDays(endDate, 1);
        
        return entityManager.createQuery("SELECT p FROM JCoffeeAnalysis AS p WHERE (p.factoryEntry IN :factories) AND p.dateOfAnalysis BETWEEN :startDate AND :endDate ORDER BY  p.creationDate DESC", JCoffeeAnalysis.class)
		.setParameter("factories", factories)
		.setParameter("startDate", startDate, TemporalType.DATE)
        .setParameter("endDate", endDateTemp, TemporalType.DATE)
        .setFirstResult(firstResult)
        .setMaxResults(maxResults)
        .getResultList();
    }
    
    @Override
    public List<JCoffeeAnalysis> findCoffeeAnalysisByFactoryListAndCoffeeAnalysisDateBetween(List<JFactory> factories, Date startDate, Date endDate) {
        
    	if (startDate == null) throw new IllegalArgumentException("The startDate argument is required");
        if (endDate == null) throw new IllegalArgumentException("The endDate argument is required");
        
        Date endDateTemp = DateUtils.addDays(endDate, 1);
        
        return entityManager.createQuery("SELECT p FROM JCoffeeAnalysis AS p WHERE (p.factoryEntry IN :factories) AND p.dateOfAnalysis BETWEEN :startDate AND :endDate ORDER BY   p.creationDate DESC", JCoffeeAnalysis.class)
		.setParameter("factories", factories)
		.setParameter("startDate", startDate, TemporalType.DATE)
        .setParameter("endDate", endDateTemp, TemporalType.DATE)
        .getResultList();
    }
    
    @Override
    public List<JCoffeeAnalysis> findStandByCoffeeAnalysisByFactoryListAndCoffeeAnalysisDateBetween(List<JFactory> factories, Date startDate, Date endDate) {
        
    	if (startDate == null) throw new IllegalArgumentException("The startDate argument is required");
        if (endDate == null) throw new IllegalArgumentException("The endDate argument is required");
        
        Date endDateTemp = DateUtils.addDays(endDate, 1);
        
        return entityManager.createQuery("SELECT p FROM JCoffeeAnalysis AS p WHERE (p.factoryEntry IN :factories) AND (p.status = :status) AND p.dateOfAnalysis BETWEEN :startDate AND :endDate ORDER BY   p.creationDate DESC", JCoffeeAnalysis.class)
		.setParameter("factories", factories)
		.setParameter("startDate", startDate, TemporalType.DATE)
        .setParameter("endDate", endDateTemp, TemporalType.DATE)
        .setParameter("status", Long.valueOf("1"))
        .getResultList();
    }
    
    @Override
    public List<JCoffeeAnalysis> findNotSentCoffeeAnalysisByFactoryListAndCoffeeAnalysisDateBetween(List<JFactory> factories, Date startDate, Date endDate) {
        
    	if (startDate == null) throw new IllegalArgumentException("The startDate argument is required");
        if (endDate == null) throw new IllegalArgumentException("The endDate argument is required");
        
        Date endDateTemp = DateUtils.addDays(endDate, 1);
        
        return entityManager.createQuery("SELECT p FROM JCoffeeAnalysis AS p WHERE (p.factoryEntry IN :factories) AND (p.status = :status) AND p.dateOfAnalysis BETWEEN :startDate AND :endDate ORDER BY   p.creationDate DESC", JCoffeeAnalysis.class)
		.setParameter("factories", factories)
		.setParameter("startDate", startDate, TemporalType.DATE)
        .setParameter("endDate", endDateTemp, TemporalType.DATE)
        .setParameter("status", Long.valueOf("0"))
        .getResultList();
    }
    
    @Transactional
    @Override
    public void persist(JCoffeeAnalysis coffeeAnalysis) {
        this.entityManager.persist(coffeeAnalysis);
    }
    
    @Transactional
    @Override
    public void remove(Long id) {
            JCoffeeAnalysis attached = this.findCoffeeAnalysis(id);
            this.entityManager.remove(attached);
    }
    
    @Transactional
    @Override
    public void flush() {
        this.entityManager.flush();
    }
    
    @Transactional
    @Override
    public void clear() {
        this.entityManager.clear();
    }
    
    @Transactional
    @Override
    public JCoffeeAnalysis merge(JCoffeeAnalysis coffeeAnalysis) {
        JCoffeeAnalysis merged = this.entityManager.merge(coffeeAnalysis);
        this.entityManager.flush();
        return merged;
    }
    
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
