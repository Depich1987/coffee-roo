package com.j1987.coffeeroo.domain;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "J_COMPANY")
public class JCompany {

    /**
     */
    private String name;
    
    /**
     */
    private String numberCC;
    
    /**
     */
    private String taxationRegime;
    
    /**
     */
    private String taxCenter;
    
    /**
     */
    private BigDecimal cocoaPrice;
    
    
    /**
     */
    private BigDecimal coffeePrice;
    
    /**
     */
    private String description;
    
    /**
     */
    @ManyToMany
    private List<JUser> users = new ArrayList<JUser>();

}
