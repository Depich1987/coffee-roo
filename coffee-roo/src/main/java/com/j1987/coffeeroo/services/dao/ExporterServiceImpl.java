package com.j1987.coffeeroo.services.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.j1987.coffeeroo.domain.JExporter;
import com.j1987.coffeeroo.framework.JUtils;

public class ExporterServiceImpl implements ExporterService {
	
	@PersistenceContext(name=JUtils.DB_PERSISTENCE_UNIT)
	private EntityManager entityManager;

	public ExporterServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	 public long countExporters() {
	        return entityManager.createQuery("SELECT COUNT(e) FROM JExporter e", Long.class).getSingleResult();
	 }
	    
	 @Override
    public List<JExporter> findAllExporters() {
        return entityManager.createQuery("SELECT e FROM JExporter e", JExporter.class).getResultList();
    }
	    
    @Override
    public JExporter findExporter(Long id) {
        if (id == null) return null;
        return entityManager.find(JExporter.class, id);
    }
    
    @Override
    public List<JExporter> findExportersByCodeEquals(String code) {
    	
        if (code == null || code.length() == 0) throw new IllegalArgumentException("The code argument is required");
       
        return entityManager.createQuery("SELECT e FROM JExporter AS e WHERE e.code = :code", JExporter.class)
        		.setParameter("code", code)
        		.getResultList();
        
    }
    
    @Override
    public List<JExporter> findExporterEntries(int firstResult, int maxResults) {
    	
        return entityManager.createQuery("SELECT e FROM JExporter e", JExporter.class)
        		.setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    @Override
    public void persist(JExporter exporter) {
        this.entityManager.persist(exporter);
    }
    
    @Transactional
    @Override
    public void remove(Long id) {

            JExporter attached = this.findExporter(id);
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
    public JExporter merge(JExporter exporter) {
        JExporter merged = this.entityManager.merge(exporter);
        this.entityManager.flush();
        return merged;
    }
    
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(JExporter exporter) {
		
    	this.entityManager.createQuery("UPDATE JExporter e SET e.code = :code, e.name = :name,  e.description = :description WHERE e.id = :id")
    	.setParameter("id", exporter.getId())
    	.setParameter("code", exporter.getCode())
    	.setParameter("name", exporter.getName())
    	.setParameter("description",exporter.getDescription())
    	.executeUpdate();

	}
    
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
