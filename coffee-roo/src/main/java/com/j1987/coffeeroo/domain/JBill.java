package com.j1987.coffeeroo.domain;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "J_BILL")
public class JBill {

    /**
     */
    @NotNull
    @Column(unique = true)
    private String reference;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date dateOfBill;

    @Transient
    private String dateOfBillAsString;

    @Transient
    private String tourName;

    @Transient
    private String factoryCode;

    @Transient
    private String factoryName;

    @Transient
    private String exporterName;

    /**
     */
    @NotNull
    private String periodTitle;

    /**
     */
    private String area;

    /**
     */
    private BigDecimal totalWeightProductAllowed;

    /**
     */
    private BigDecimal totalWeightProductPushed;

    /**
     */
    private BigDecimal amountWithoutTaxes;

    /**
     */
    @OneToOne
    private JSubmissionForApproval submission;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<JAnalysis> analyzes = new HashSet<JAnalysis>();
}
