package com.j1987.coffeeroo.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.persistence.OneToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "J_SUBMISSION_APPROVAL")
public class JSubmissionForApproval {

    @NotNull
    @Column(name = "REFERENCE", unique = true)
    private String reference;

    /**
     */
    private String description;

    /**
     */
    private String factoryCode;
    
    private long tourId;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date creationDate;

    /**
     */
    private String createdBy;

    private Long status;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<JCoffeeAnalysis> analyzesCoffee = new HashSet<JCoffeeAnalysis>();

    /**
     */
    @OneToOne
    private JBill bill;
}
