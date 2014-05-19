// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.j1987.coffeeroo.domain;

import com.j1987.coffeeroo.domain.JFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect JFactory_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager JFactory.entityManager;
    
    public static final EntityManager JFactory.entityManager() {
        EntityManager em = new JFactory().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long JFactory.countJFactorys() {
        return entityManager().createQuery("SELECT COUNT(o) FROM JFactory o", Long.class).getSingleResult();
    }
    
    public static List<JFactory> JFactory.findAllJFactorys() {
        return entityManager().createQuery("SELECT o FROM JFactory o", JFactory.class).getResultList();
    }
    
    public static JFactory JFactory.findJFactory(Long id) {
        if (id == null) return null;
        return entityManager().find(JFactory.class, id);
    }
    
    public static List<JFactory> JFactory.findJFactoryEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM JFactory o", JFactory.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void JFactory.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void JFactory.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            JFactory attached = JFactory.findJFactory(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void JFactory.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void JFactory.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public JFactory JFactory.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        JFactory merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
