package com.j1987.coffeeroo.domain;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "J_FACTORY")
public class JFactory {

    /**
     */
    @NotNull
    @Column(unique = true)
    private String code;

    /**
     */
    @NotNull
    private String name;

    /**
     */
    private String description;

    /**
     */
    @ManyToMany
    private List<JUser> users = new ArrayList<JUser>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL)
    private Set<JBridge> bridges = new HashSet<JBridge>();
}
