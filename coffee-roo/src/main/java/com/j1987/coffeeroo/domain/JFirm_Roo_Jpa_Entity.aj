// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.j1987.coffeeroo.domain;

import com.j1987.coffeeroo.domain.JFirm;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

privileged aspect JFirm_Roo_Jpa_Entity {
    
    declare @type: JFirm: @Entity;
    
    declare @type: JFirm: @Table(name = "J_FIRM");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long JFirm.id;
    
    @Version
    @Column(name = "version")
    private Integer JFirm.version;
    
    public Long JFirm.getId() {
        return this.id;
    }
    
    public void JFirm.setId(Long id) {
        this.id = id;
    }
    
    public Integer JFirm.getVersion() {
        return this.version;
    }
    
    public void JFirm.setVersion(Integer version) {
        this.version = version;
    }
    
}