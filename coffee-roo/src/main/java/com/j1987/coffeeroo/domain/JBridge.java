package com.j1987.coffeeroo.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "J_BRIDGE")
public class JBridge {

    /**
     */
    @NotNull
    @Column(unique = true)
    private String code;

    /**
     */
    private String name;

    /**
     */
    @ManyToOne
    @JoinColumn(nullable = true)
    private JFactory factory;
}
