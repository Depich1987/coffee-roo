package com.j1987.coffeeroo.services.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.j1987.coffeeroo.domain.JFirm;
import com.j1987.coffeeroo.framework.JUtils;

public class FirmServiceImpl implements FirmService {
	
	@PersistenceContext(name=JUtils.DB_PERSISTENCE_UNIT)
	private EntityManager entityManager;

	public FirmServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	 public long countFirms() {
	        return entityManager.createQuery("SELECT COUNT(d) FROM JFirm d", Long.class).getSingleResult();
	 }
	    
	 @Override
   public List<JFirm> findAllFirms() {
       return entityManager.createQuery("SELECT d FROM JFirm d", JFirm.class).getResultList();
   }
	    
   @Override
   public JFirm findFirm(Long id) {
       if (id == null) return null;
       return entityManager.find(JFirm.class, id);
   }
   
   @Override
   public List<JFirm> findFirmsByCodeEquals(String code) {
   	
       if (code == null || code.length() == 0) throw new IllegalArgumentException("The code argument is required");
      
       return entityManager.createQuery("SELECT d FROM JFirm AS d WHERE d.dealerCode = :code", JFirm.class)
       		.setParameter("code", code)
       		.getResultList();
       
   }
   
   @Override
   public List<JFirm> findFirmEntries(int firstResult, int maxResults) {
   	
       return entityManager.createQuery("SELECT d FROM JFirm d", JFirm.class)
       		.setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
   }
   
   @Transactional
   @Override
   public void persist(JFirm firm) {
       this.entityManager.persist(firm);
   }
   
   @Transactional
   @Override
   public void remove(Long id) {

           JFirm attached = this.findFirm(id);
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
   public JFirm merge(JFirm firm) {
       JFirm merged = this.entityManager.merge(firm);
       this.entityManager.flush();
       return merged;
   }
   
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(JFirm firm) {
		
   	this.entityManager.createQuery("UPDATE JFirm d SET d.dealerCode = :code, d.name = :name, d.priceCocoaAnalysis = :priceCocoaAnalysis, d.priceCoffeeAnalysis = :priceCoffeeAnalysis  ,d.productWeightBag = :productWeightBag WHERE d.id = :id")
   	.setParameter("id", firm.getId())
   	.setParameter("code", firm.getDealerCode())
   	.setParameter("name", firm.getName())
   	.setParameter("priceCocoaAnalysis",firm.getPriceCocoaAnalysis())
   	.setParameter("priceCoffeeAnalysis",firm.getPriceCoffeeAnalysis())
   	.setParameter("productWeightBag", firm.getProductWeightBag())
   	.executeUpdate();

	}
   
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
