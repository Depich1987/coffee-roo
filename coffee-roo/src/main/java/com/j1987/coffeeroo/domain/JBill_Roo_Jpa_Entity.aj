// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.j1987.coffeeroo.domain;

import com.j1987.coffeeroo.domain.JBill;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

privileged aspect JBill_Roo_Jpa_Entity {
    
    declare @type: JBill: @Entity;
    
    declare @type: JBill: @Table(name = "J_BILL");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long JBill.id;
    
    @Version
    @Column(name = "version")
    private Integer JBill.version;
    
    public Long JBill.getId() {
        return this.id;
    }
    
    public void JBill.setId(Long id) {
        this.id = id;
    }
    
    public Integer JBill.getVersion() {
        return this.version;
    }
    
    public void JBill.setVersion(Integer version) {
        this.version = version;
    }
    
}