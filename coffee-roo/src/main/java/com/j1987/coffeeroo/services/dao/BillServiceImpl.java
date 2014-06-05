package com.j1987.coffeeroo.services.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.j1987.coffeeroo.domain.JBill;
import com.j1987.coffeeroo.framework.JUtils;

public class BillServiceImpl implements BillService {
	
	@PersistenceContext(name=JUtils.DB_PERSISTENCE_UNIT)
	private EntityManager entityManager;

	public BillServiceImpl() {
		// TODO Auto-generated constructor stub
	}
    
	@Override
    public long countBills() {
        return entityManager.createQuery("SELECT COUNT(o) FROM JBill o", Long.class).getSingleResult();
    }
	
	@Override
    public long countBillsByFactoryCode(String factoryCode) {
        return entityManager.createQuery("SELECT COUNT(o) FROM JBill o WHERE o.submission.factoryCode = :factoryCode", Long.class)
        		.setParameter("factoryCode", factoryCode)
        		.getSingleResult();
    }
    
    @Override
    public List<JBill> findAllBills() {
        return entityManager.createQuery("SELECT o FROM JBill o", JBill.class).getResultList();
    }
    
    @Override
    public JBill findBill(Long id) {
        if (id == null) return null;
        return entityManager.find(JBill.class, id);
    }
    
    @Override
    public List<JBill> findBillEntries(int firstResult, int maxResults) {
        return entityManager.createQuery("SELECT o FROM JBill o", JBill.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Override
    public List<JBill> findBillByFactoryCodeEntries(String factoryCode, int firstResult, int maxResults) {
        return entityManager.createQuery("SELECT o FROM JBill o WHERE o.submission.factoryCode = :factoryCode ORDER BY o.dateOfBill DESC", JBill.class)
        		.setParameter("factoryCode", factoryCode)
        		.setFirstResult(firstResult)
        		.setMaxResults(maxResults)
        		.getResultList();
    }
    
    @Override
    public List<JBill> findBillByFactoryCodeAndTourEntries(String factoryCode, Long tourId,int firstResult, int maxResults) {
        return entityManager.createQuery("SELECT o FROM JBill o WHERE o.submission.factoryCode = :factoryCode ORDER BY o.dateOfBill DESC", JBill.class)
        		.setParameter("factoryCode", factoryCode)
        		.setParameter("tourId", tourId)
        		.setFirstResult(firstResult)
        		.setMaxResults(maxResults)
        		.getResultList();
    }
    
    @Override
    public List<JBill> findBillByFactoryCode(String factoryCode) {
        return entityManager.createQuery("SELECT o FROM JBill o WHERE o.submission.factoryCode = :factoryCode ORDER BY o.dateOfBill DESC", JBill.class)
        		.setParameter("factoryCode", factoryCode)
        		.getResultList();
    }
    
    @Override
    public List<JBill> findBillByReference(String reference) {

        return entityManager.createQuery("SELECT o FROM JBill o WHERE o.reference = :reference ORDER BY o.dateOfBill DESC", JBill.class)
        		.setParameter("reference", reference)
        		.getResultList();
    }
    
    @Transactional
    @Override
    public void persist(JBill bill) {
        this.entityManager.persist(bill);
    }
    
    @Transactional
    @Override
    public void remove(Long id) {

        JBill attached = this.findBill(id);
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
    public JBill merge(JBill bill) {
        JBill merged = this.entityManager.merge(bill);
        this.entityManager.flush();
        return merged;
    }
    
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
