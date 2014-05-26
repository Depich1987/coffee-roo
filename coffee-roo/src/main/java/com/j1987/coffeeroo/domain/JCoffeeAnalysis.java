package com.j1987.coffeeroo.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import java.util.Date;

import javax.persistence.Column;
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
	
	@Column(unique = true)
	private String reference;

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
    
    /**
     */
    private BigDecimal poidsMatieresEtrangeres;
    
    /**
     */
    private BigDecimal pourcentageMatieresEtrangeres;
    
    /**
     */
    private BigDecimal poidsDechetsParches;
    
    /**
     */
    private BigDecimal pourcentageDechetsParches;
    
    /**
     */
    private BigDecimal poidsDechetsCerise;
    
    /**
     */
    private BigDecimal pourcentageDechetsCerise;
    
    /**
     */
    private BigDecimal poidsDechetsDemiCerises;
    
    /**
     */
    private BigDecimal pourcentageDechetsDemiCerises;
    
    /**
     */
    private BigDecimal poidsDechetsCoques;
    
    /**
     */
    private BigDecimal pourcentageDechetsCoques;
    
    /**
     */
    private BigDecimal poidsDechetsPeaux;
    
    /**
     */
    private BigDecimal pourcentageDechetsPeaux;
    
    /**
     */
    private BigDecimal poidsSousTotalDechets;
    
    /**
     */
    private BigDecimal pourcentageSousTotalDechets;
    
    /**
     */
    private BigDecimal poidsHorsNormesGrainsNoirs;
    
    /**
     */
    private BigDecimal pourcentageHorsNormesGrainsNoirs;
    
    /**
     */
    private BigDecimal poidsHorsNormesGrainsDemiNoirs;
    
    /**
     */
    private BigDecimal pourcentageHorsNormesGrainsDemiNoirs;
    
    /**
     */
    private BigDecimal poidsHorsNormesBrisures;
    
    /**
     */
    private BigDecimal pourcentageHorsNormesBrisures;
    
    /**
     */
    private BigDecimal poidsSousTotalHorsNormes;
    
    /**
     */
    private BigDecimal pourcentageSousTotalHorsNormes;
    
    /**
     */
    private BigDecimal poidsGrainsAcceptablesVert;
    
    /**
     */
    private BigDecimal pourcentageGrainsAcceptablesVert;
    
    /**
     */
    private BigDecimal poidsGrainsAcceptablesSpongieux;
    
    /**
     */
    private BigDecimal pourcentageGrainsAcceptablesSpongieux;
    
    /**
     */
    private BigDecimal poidsGrainsAcceptablesDemiSombre;
    
    /**
     */
    private BigDecimal pourcentageGrainsAcceptablesDemiSombre;
    
    /**
     */
    private BigDecimal poidsGrainsAcceptablesScolytes;
    
    /**
     */
    private BigDecimal pourcentageGrainsAcceptablesScolytes;
    
    /**
     */
    private BigDecimal poidsGrainsAcceptablesIndesirables;
    
    /**
     */
    private BigDecimal pourcentageGrainsAcceptablesIndesirables;
    
    /**
     */
    private BigDecimal poidsGrainsAcceptablesImmature;
    
    /**
     */
    private BigDecimal pourcentageGrainsAcceptablesImmature;

    /**
     */
    private BigDecimal CalibrageTamis18;
    
    /**
     */
    private BigDecimal CalibrageTamis16;
    
    /**
     */
    private BigDecimal CalibrageTamis14;
    
    /**
     */
    private BigDecimal CalibrageTamis12;
    
    /**
     */
    private BigDecimal CalibrageTamis10;
    
    /**
     */
    private BigDecimal CalibrageBase;
    
    /**
     */
    private BigDecimal pourcentageGradeG0;
    
    /**
     */
    private BigDecimal pourcentageGradeG1;
    
    /**
     */
    private BigDecimal pourcentageGradeG2;
    
    /**
     */
    private BigDecimal pourcentageGradeG3;
    
    /**
     */
    private BigDecimal pourcentageGradeG4;
    
    /**
     */
    private BigDecimal pourcentageGradeHN;
    
    /**
     */
    private String confirmiteOui;
    
    /**
     */
    private String confirmiteNon;
    
    private Boolean acceptation;
    
    private Boolean confirmation;
    
    /**
     */
    private String acceptationOui;
    
    /**
     */
    private String acceptationNon;
}
