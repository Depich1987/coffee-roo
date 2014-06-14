package com.j1987.coffeeroo.domain;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "J_COFFEE_ANALYSIS")
public class JCoffeeAnalysis {
	
	@Column(unique = true)
	private String reference;
	
	private Long status;

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
    private BigDecimal netWeightOfProductAccepted = new BigDecimal("0");

    /**
     */
    @ManyToOne
    private JLocalization provenance;
    
    @ManyToOne
    private JTour tour;
    
    @Transient
    private String provenanceName;
    
    @Transient
    private String dealerName;

    /**
     */
    @ManyToOne
    private JFactory factoryEntry;
    
    @Transient
    private String factoryName;

    /**
     */
    @ManyToOne
    private JExporter exporterEntry;
    
    
    @Transient
    private String exporterName;

    /**
     */
    @ManyToOne
    private JSupplier supplierEntry;
    
    @Transient
    private String supplierName;

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
    private Long totalOfBagPushed;

    /**
     */
    private Long totalOfReportedBags;

    /**
     */
    private Long numberOfBagAllowed;

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
    private BigDecimal poidsMatieresEtrangeres = new BigDecimal("0");
    
    /**
     */
    private BigDecimal pourcentageMatieresEtrangeres = new BigDecimal("0");
    
    /**
     */
    private BigDecimal poidsDechetsParches = new BigDecimal("0");
    
    /**
     */
    private BigDecimal pourcentageDechetsParches = new BigDecimal("0");
    
    /**
     */
    private BigDecimal poidsDechetsCerise = new BigDecimal("0");
    
    /**
     */
    private BigDecimal pourcentageDechetsCerise = new BigDecimal("0");
    
    /**
     */
    private BigDecimal poidsDechetsDemiCerises = new BigDecimal("0");
    
    /**
     */
    private BigDecimal pourcentageDechetsDemiCerises = new BigDecimal("0");
    
    /**
     */
    private BigDecimal poidsDechetsCoques = new BigDecimal("0");
    
    /**
     */
    private BigDecimal pourcentageDechetsCoques = new BigDecimal("0");
    
    /**
     */
    private BigDecimal poidsDechetsPeaux = new BigDecimal("0");
    
    /**
     */
    private BigDecimal pourcentageDechetsPeaux = new BigDecimal("0");
    
    /**
     */
    private BigDecimal poidsSousTotalDechets = new BigDecimal("0");
    
    /**
     */
    private BigDecimal pourcentageSousTotalDechets = new BigDecimal("0");
    
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
    private BigDecimal poidsSousTotalGrainsAcceptables;
    
    /**
     */
    private BigDecimal pourcentageSousTotalGrainsAcceptables;

    /**
     */
    private BigDecimal calibrageTamis18;
    
    /**
     */
    private BigDecimal calibrageTamis16;
    
    /**
     */
    private BigDecimal calibrageTamis14;
    
    /**
     */
    private BigDecimal calibrageTamis12;
    
    /**
     */
    private BigDecimal calibrageTamis10;
    
    /**
     */
    private BigDecimal calibrageBase;
    
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
