package com.j1987.coffeeroo.services.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.j1987.coffeeroo.domain.JBridge;
import com.j1987.coffeeroo.domain.JFactory;
import com.j1987.coffeeroo.framework.JUtils;

public class BridgeServiceImpl implements BridgeService {
	
	@PersistenceContext(name=JUtils.DB_PERSISTENCE_UNIT)
	private EntityManager entityManager;

	public BridgeServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	 public long countBridges() {
	        return entityManager.createQuery("SELECT COUNT(d) FROM JBridge d", Long.class).getSingleResult();
	 }
	
	@Override
	public long countBridgesInFactory(JFactory factory) {
		
		return entityManager.createQuery("SELECT COUNT(d) FROM JBridge d WHERE d.factory = :factory", Long.class)
				.setParameter("factory", factory)
				.getSingleResult();
	}
	    
	 @Override
   public List<JBridge> findAllBridges() {
       return entityManager.createQuery("SELECT d FROM JBridge d", JBridge.class).getResultList();
   }
	 @Override
	public List<JBridge> findBridgeInFactoryEntries(JFactory factory,int firstResult, int maxResults) {
	      
		 return entityManager.createQuery("SELECT d FROM JBridge d WHERE d.factory = :factory ORDER BY d.code ASC", JBridge.class)
	          		.setParameter("factory", factory)
				 	.setFirstResult(firstResult)
	          		.setMaxResults(maxResults)
	          		.getResultList();
	}
	    
   @Override
   public JBridge findBridge(Long id) {
       if (id == null) return null;
       return entityManager.find(JBridge.class, id);
   }
   
   @Override
   public List<JBridge> findBridgesByCodeEquals(String code) {
   	
       if (code == null || code.length() == 0) throw new IllegalArgumentException("The code argument is required");
      
       return entityManager.createQuery("SELECT d FROM JBridge AS d WHERE d.code = :code", JBridge.class)
       		.setParameter("code", code)
       		.getResultList();
       
   }
   
   @Override
	public List<JBridge> findAllBridgesInFactory(JFactory factory) {
       
	   return entityManager.createQuery("SELECT d FROM JBridge AS d WHERE d.factory = :factory ORDER BY d.code ASC", JBridge.class)
          		.setParameter("factory", factory)
          		.getResultList();
	}
   
   @Override
   public List<JBridge> findBridgeEntries(int firstResult, int maxResults) {
   	
       return entityManager.createQuery("SELECT d FROM JBridge d ORDER BY d.code ASC", JBridge.class)
       		.setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
   }
   
   @Transactional
   @Override
   public void persist(JBridge bridge) {
       this.entityManager.persist(bridge);
   }
   
   @Transactional
   @Override
   public void remove(Long id) {

           JBridge attached = this.findBridge(id);
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
   public JBridge merge(JBridge bridge) {
       JBridge merged = this.entityManager.merge(bridge);
       this.entityManager.flush();
       return merged;
   }
   
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(JBridge bridge) {
		
   	this.entityManager.createQuery("UPDATE JBridge d SET d.code = :code, d.name = :name, d.priceCocoaAnalysis = :priceCocoaAnalysis, d.priceCoffeeAnalysis = :priceCoffeeAnalysis  ,d.productWeightBag = :productWeightBag WHERE d.id = :id")
   	.setParameter("id", bridge.getId())
   	.setParameter("code", bridge.getCode())
   	.setParameter("name", bridge.getName())
   	.executeUpdate();

	}
   
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
