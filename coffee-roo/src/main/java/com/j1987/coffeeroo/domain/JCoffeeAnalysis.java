package com.j1987.coffeeroo.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import javax.persistence.ManyToOne;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "J_COFFEE_ANALYSIS")
public class JCoffeeAnalysis {

    /**
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date dateOfAnalysis;

    /**
     */
    @NotNull
    private String numberLading;

    /**
     */
    @NotNull
    private String truckNumber;

    /**
     */
    private BigDecimal netWeightOfProductAccepted;

    /**
     */
    @ManyToOne
    private JLocalization provenance;

    /**
     */
    @ManyToOne
    private JDealer dealerEntry;

    /**
     */
    @ManyToOne
    private JFactory factoryEntry;

    /**
     */
    @ManyToOne
    private JExporter exporterEntry;

    /**
     */
    @ManyToOne
    private JSupplier supplierEntry;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MS")
    private Date startTime;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MS")
    private Date endTime;

    /**
     */
    private BigInteger totalOfBagPushed;

    /**
     */
    private BigInteger totalOfReportedBags;

    /**
     */
    private BigInteger numberOfBagAllowed;

    /**
     */
    @NotNull
    private String sampleCode;

    /**
     */
    private String numberSAIGIC;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date creationDate;

    /**
     */
    private String createdBy;

    /**
     */
    private String modifiedBy;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date modificationDate;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private List<JSubmissionForApproval> submissionsForApproval = new ArrayList<JSubmissionForApproval>();
}
