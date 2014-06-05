package com.j1987.coffeeroo.domain;
import java.util.ArrayList;
import java.util.List;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "J_TOUR")
public class JTour {

    /**
     */
    @NotNull
    private String name;

    /**
     */
    private String description;
    
    @OneToMany
    private List<JCoffeeAnalysis> analysis =  new ArrayList<JCoffeeAnalysis>();
}
