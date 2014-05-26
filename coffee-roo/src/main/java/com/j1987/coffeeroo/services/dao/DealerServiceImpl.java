package com.j1987.coffeeroo.services.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.j1987.coffeeroo.domain.JDealer;
import com.j1987.coffeeroo.framework.JUtils;

public class DealerServiceImpl implements DealerService {
	
	@PersistenceContext(name=JUtils.DB_PERSISTENCE_UNIT)
	private EntityManager entityManager;

	public DealerServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	 public long countDealers() {
	        return entityManager.createQuery("SELECT COUNT(d) FROM JDealer d", Long.class).getSingleResult();
	 }
	    
	 @Override
    public List<JDealer> findAllDealers() {
        return entityManager.createQuery("SELECT d FROM JDealer d", JDealer.class).getResultList();
    }
	    
    @Override
    public JDealer findDealer(Long id) {
        if (id == null) return null;
        return entityManager.find(JDealer.class, id);
    }
    
    @Override
    public List<JDealer> findDealersByCodeEquals(String code) {
    	
        if (code == null || code.length() == 0) throw new IllegalArgumentException("The code argument is required");
       
        return entityManager.createQuery("SELECT d FROM JDealer AS d WHERE d.code = :code", JDealer.class)
        		.setParameter("code", code)
        		.getResultList();
        
    }
    
    @Override
    public List<JDealer> findDealerEntries(int firstResult, int maxResults) {
    	
        return entityManager.createQuery("SELECT d FROM JDealer d", JDealer.class)
        		.setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    @Override
    public void persist(JDealer dealer) {
        this.entityManager.persist(dealer);
    }
    
    @Transactional
    @Override
    public void remove(Long id) {

            JDealer attached = this.findDealer(id);
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
    public JDealer merge(JDealer dealer) {
        JDealer merged = this.entityManager.merge(dealer);
        this.entityManager.flush();
        return merged;
    }
    
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(JDealer dealer) {
		
    	this.entityManager.createQuery("UPDATE JDealer d SET d.code = :code, d.name = :name,  d.description = :description WHERE d.id = :id")
    	.setParameter("id", dealer.getId())
    	.setParameter("code", dealer.getCode())
    	.setParameter("name", dealer.getName())
    	.setParameter("description",dealer.getDescription())
    	.executeUpdate();

	}
    
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
