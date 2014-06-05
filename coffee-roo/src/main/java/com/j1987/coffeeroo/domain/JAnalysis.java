package com.j1987.coffeeroo.domain;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
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
@RooJpaActiveRecord(table = "J_ANALYSIS")
public class JAnalysis {
	
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
    @ManyToOne
    private JLocalization provenance;
    
    @ManyToOne
    private JTour tour;
    
    @Transient
    private String provenanceName;

    /**
     */
    @ManyToOne
    private JDealer dealerEntry;
    
    @Transient
    private String dealerName;

    /**
     */
    private String factoryCode;
    
    /**
     */
    @Transient
    private String factoryName;
    
    
    /**
     */
    private JBridge bridge;


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
    private String productType;
    
    /**********************************
     *************** COCOA ************ 
     **********************************/
    /*
	 * 
	 */
	private BigDecimal tauxHumidite1;
	/*
	 * 
	 */
	private BigDecimal tauxHumidite2;
	/*
	 * 
	 */
	private BigDecimal tauxHumidite3;
	/*
	 * 
	 */
	private BigDecimal moyenneTauxHumidite;
	/*
	 * 
	 */
	private BigDecimal nombreFeves;
	/*
	 * 
	 */
	private BigDecimal fevesPar100g;
	/*
	 * 
	 */
	private BigDecimal poidsBrisures;
	/*
	 * 
	 */
	private BigDecimal pourcentageBrisures;
	/*
	 * 
	 */
	private Long fevesMoisiesPlateau1;
	/*
	 * 
	 */
	private Long fevesMoisiesPlateau2;
	/*
	 * 
	 */
	private Long fevesMoisiesPlateau3;
	/*
	 * 
	 */
	private Long totalFevesMoisies;
	/*
	 * 
	 */
	private BigDecimal pourcentageFevesMoisies;
	/*
	 * 
	 */
	private Long fevesArdoiseesPlateau1;
	/*
	 * 
	 */
	private Long fevesArdoiseesPlateau2;
	/*
	 * 
	 */
	private Long fevesArdoiseesPlateau3;
	/*
	 * 
	 */
	private Long TotalfevesArdoisees;
	/*
	 * 
	 */
	private BigDecimal pourcentageFevesArdoisees;
	/*
	 * 
	 */
	private Long fevesMiteesPlateau1;
	/*
	 * 
	 */
	private Long fevesMiteesPlateau2;
	/*
	 * 
	 */
	private Long fevesMiteesPlateau3;
	/*
	 * 
	 */
	private Long totalFevesMitees;
	/*
	 * 
	 */
	private BigDecimal pourcentageFevesMitees;
	/*
	 * 
	 */
	private Long fevesGermeesPlateau1;
	/*
	 * 
	 */
	private Long fevesGermeesPlateau2;
	/*
	 * 
	 */
	private Long fevesGermeesPlateau3;
	/*
	 * 
	 */
	private Long totalFevesGermees;
	/*
	 * 
	 */
	private BigDecimal pourcentageFevesGermees;
	/*
	 * 
	 */
	private Long fevesPlatesPlateau1;
	/*
	 * 
	 */
	private Long fevesPlatesPlateau2;
	/*
	 * 
	 */
	private Long fevesPlatesPlateau3;
	/*
	 * 
	 */
	private Long totalFevesPlates;
	/*
	 * 
	 */
	private BigDecimal pourcentageFevesPlates;
	/*
	 * 
	 */
	private Long totalFevesDefectueusesPlateau1;
	/*
	 * 
	 */
	private Long totalFevesDefectueusesPlateau2;
	/*
	 * 
	 */
	private Long totalFevesDefectueusesPlateau3;
	/*
	 * 
	 */
	private Long sommeTotalFevesDefectueuses;
	/*
	 * 
	 */
	private BigDecimal pourcentageTotalFevesDefectueuses;
	/*
	 * 
	 */
	private Long fevesViolettePlateau1;
	/*
	 * 
	 */
	private Long fevesViolettePlateau2;
	/*
	 * 
	 */
	private Long fevesViolettePlateau3;
	/*
	 * 
	 */
	private Long totalFevesViolette;
	/*
	 * 
	 */
	private BigDecimal pourcentageFevesViolette;
	/*
	 * 
	 */
	private String classification;
	/*
	 * 
	 */
	private Boolean conformite;


	/*********************************************************************
	  								COFFEE
	 *********************************************************************/
   
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

    
    private Boolean acceptation;
    
    private Boolean confirmation;


}
