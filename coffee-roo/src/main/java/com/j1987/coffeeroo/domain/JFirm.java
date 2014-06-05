package com.j1987.coffeeroo.domain;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "J_FIRM")
public class JFirm {

    /**
     */
    @NotNull
    @Column(unique = true)
    private String dealerCode;
    
    /**
     */
    @NotNull
    private String name;
    
    /**
     */
    private BigDecimal priceCocoaAnalysis = new BigDecimal("855");
    
    
    /**
     */
    private BigDecimal priceCoffeeAnalysis = new BigDecimal("855");
    
    /**
     */
    private BigDecimal productWeightBag = new BigDecimal("70");
    
    /**
     */
    @ManyToMany
    private List<JUser> users = new ArrayList<JUser>();
}
