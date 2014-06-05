package com.j1987.coffeeroo.services.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.j1987.coffeeroo.domain.JFactory;
import com.j1987.coffeeroo.domain.JSubmissionForApproval;
import com.j1987.coffeeroo.framework.JUtils;

public class SubmissionServiceImpl implements SubmissionService {
	
	@PersistenceContext(name=JUtils.DB_PERSISTENCE_UNIT)
	private EntityManager entityManager;

	public SubmissionServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public long countSubmissionForApprovals() {
        return entityManager.createQuery("SELECT COUNT(o) FROM JSubmissionForApproval o", Long.class).getSingleResult();
    }
    
	@Override
    public List<JSubmissionForApproval> findAllSubmissionForApprovals() {
        return entityManager.createQuery("SELECT o FROM JSubmissionForApproval o", JSubmissionForApproval.class).getResultList();
    }
	
	@Override
    public List<JSubmissionForApproval> findSubmissionForApprovalsByReferenceEquals(String reference) {
        return entityManager.createQuery("SELECT o FROM JSubmissionForApproval o WHERE o.reference = :reference", JSubmissionForApproval.class)
        		.setParameter("reference", reference)
        		.getResultList();
    }
    
	@Override
    public JSubmissionForApproval findSubmissionForApproval(Long id) {
        if (id == null) return null;
        return entityManager.find(JSubmissionForApproval.class, id);
    }
    
	@Override
    public List<JSubmissionForApproval> findSubmissionForApprovalEntries(int firstResult, int maxResults) {
        return entityManager.createQuery("SELECT o FROM JSubmissionForApproval o", JSubmissionForApproval.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
	
	@Override
    public List<JSubmissionForApproval> findSubmissionByFactoryForApprovalEntries(JFactory  factory, int firstResult, int maxResults) {
        return entityManager.createQuery("SELECT o FROM JSubmissionForApproval o WHERE o.analyzesCoffee.factoryEntry = :factory", JSubmissionForApproval.class)
        		.setParameter("factory", factory)
        		.setFirstResult(firstResult)
        		.setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    @Override
    public void persist(JSubmissionForApproval submissionForApproval) {
        this.entityManager.persist(submissionForApproval);
    }
    
    @Transactional
    @Override
    public void remove(Long id) {

        JSubmissionForApproval attached = this.findSubmissionForApproval(id);
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
    public JSubmissionForApproval merge(JSubmissionForApproval submissionForApproval) {
        JSubmissionForApproval merged = this.entityManager.merge(submissionForApproval);
        this.entityManager.flush();
        return merged;
    }
    
    @Override
    @Transactional
    public void update(JSubmissionForApproval submissionForApproval) {
    	
    	this.entityManager.createQuery("UPDATE JSubmissionForApproval o SET o.reference = :reference, o.status = :status, o.description = :description  WHERE o.id = :id")
    	.setParameter("id", submissionForApproval.getId())
    	.setParameter("reference", submissionForApproval.getReference())
    	.setParameter("description", submissionForApproval.getDescription())
    	.setParameter("status", submissionForApproval.getStatus())
    	.executeUpdate();
    	
    }
    
    
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
