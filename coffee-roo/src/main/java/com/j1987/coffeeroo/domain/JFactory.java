package com.j1987.coffeeroo.domain;
import java.util.ArrayList;
import java.util.List;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

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
    @ManyToOne
    private JCompany company;

    /**
     */
    @ManyToMany
    private List<JUser> users = new ArrayList<JUser>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL)
    private Set<JBridge> bridges = new HashSet<JBridge>();
}
