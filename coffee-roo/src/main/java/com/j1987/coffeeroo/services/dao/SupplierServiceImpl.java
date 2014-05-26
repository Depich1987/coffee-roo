package com.j1987.coffeeroo.services.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.j1987.coffeeroo.domain.JSupplier;
import com.j1987.coffeeroo.framework.JUtils;

public class SupplierServiceImpl implements SupplierService {
	
	@PersistenceContext(name=JUtils.DB_PERSISTENCE_UNIT)
	private EntityManager entityManager;

	public SupplierServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	 public long countSuppliers() {
	        return entityManager.createQuery("SELECT COUNT(s) FROM JSupplier s", Long.class).getSingleResult();
	 }
	    
	 @Override
    public List<JSupplier> findAllSuppliers() {
        return entityManager.createQuery("SELECT s FROM JSupplier s", JSupplier.class).getResultList();
    }
	    
    @Override
    public JSupplier findSupplier(Long id) {
        if (id == null) return null;
        return entityManager.find(JSupplier.class, id);
    }
    
    @Override
    public List<JSupplier> findSuppliersByCodeEquals(String code) {
    	
        if (code == null || code.length() == 0) throw new IllegalArgumentException("The code argument is required");
       
        return entityManager.createQuery("SELECT s FROM JSupplier AS s WHERE s.code = :code", JSupplier.class)
        		.setParameter("code", code)
        		.getResultList();
        
    }
    
    @Override
    public List<JSupplier> findSupplierEntries(int firstResult, int maxResults) {
    	
        return entityManager.createQuery("SELECT s FROM JSupplier s", JSupplier.class)
        		.setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    @Override
    public void persist(JSupplier supplier) {
        this.entityManager.persist(supplier);
    }
    
    @Transactional
    @Override
    public void remove(Long id) {

            JSupplier attached = this.findSupplier(id);
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
    public JSupplier merge(JSupplier supplier) {
        JSupplier merged = this.entityManager.merge(supplier);
        this.entityManager.flush();
        return merged;
    }
    
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(JSupplier supplier) {
		
    	this.entityManager.createQuery("UPDATE JSupplier s SET s.code = :code, s.name = :name,  s.description = :description WHERE s.id = :id")
    	.setParameter("id", supplier.getId())
    	.setParameter("code", supplier.getCode())
    	.setParameter("name", supplier.getName())
    	.setParameter("description",supplier.getDescription())
    	.executeUpdate();

	}
    
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
