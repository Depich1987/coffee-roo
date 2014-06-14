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
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "J_ANALYSIS")
public class JAnalysis {

    @Column(unique = true)
    private String reference;

    private Boolean status;
    
    private Boolean existInBill = false;

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
    private JFirm dealerEntry;

    private String dealerName;

    /**
     */
    private String factoryCode;

    private String factoryName;

    /**
     */
    private JBridge bridge;

    /**
     */
    @ManyToOne
    private JExporter exporterEntry;

    private String exporterName;

    /**
     */
    @ManyToOne
    private JSupplier supplierEntry;

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

    private Boolean acceptation;

    /**
     */
    private Boolean conformity;

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
    private Long totalFevesArdoisees;

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
    private BigDecimal poidsHorsNormesGrainsNoirs = new BigDecimal(0);

    /**
     */
    private BigDecimal pourcentageHorsNormesGrainsNoirs = new BigDecimal(0);

    /**
     */
    private BigDecimal poidsHorsNormesGrainsDemiNoirs = new BigDecimal(0);

    /**
     */
    private BigDecimal pourcentageHorsNormesGrainsDemiNoirs = new BigDecimal(0);

    /**
     */
    private BigDecimal poidsHorsNormesBrisures = new BigDecimal(0);

    /**
     */
    private BigDecimal pourcentageHorsNormesBrisures = new BigDecimal(0);

    /**
     */
    private BigDecimal poidsSousTotalHorsNormes = new BigDecimal(0);

    /**
     */
    private BigDecimal pourcentageSousTotalHorsNormes = new BigDecimal(0);

    /**
     */
    private BigDecimal poidsGrainsAcceptablesVert = new BigDecimal(0);

    /**
     */
    private BigDecimal pourcentageGrainsAcceptablesVert = new BigDecimal(0);

    /**
     */
    private BigDecimal poidsGrainsAcceptablesSpongieux = new BigDecimal(0);

    /**
     */
    private BigDecimal pourcentageGrainsAcceptablesSpongieux = new BigDecimal(0);

    /**
     */
    private BigDecimal poidsGrainsAcceptablesDemiSombre = new BigDecimal(0);

    /**
     */
    private BigDecimal pourcentageGrainsAcceptablesDemiSombre = new BigDecimal(0);

    /**
     */
    private BigDecimal poidsGrainsAcceptablesScolytes = new BigDecimal(0);

    /**
     */
    private BigDecimal pourcentageGrainsAcceptablesScolytes = new BigDecimal(0);

    /**
     */
    private BigDecimal poidsGrainsAcceptablesIndesirables = new BigDecimal(0);

    /**
     */
    private BigDecimal pourcentageGrainsAcceptablesIndesirables = new BigDecimal(0);

    /**
     */
    private BigDecimal poidsGrainsAcceptablesImmature = new BigDecimal(0);

    /**
     */
    private BigDecimal pourcentageGrainsAcceptablesImmature = new BigDecimal(0);

    /**
     */
    private BigDecimal poidsSousTotalGrainsAcceptables = new BigDecimal(0);

    /**
     */
    private BigDecimal pourcentageSousTotalGrainsAcceptables = new BigDecimal(0);

    /**
     */
    private BigDecimal calibrageTamis18 = new BigDecimal(0);

    /**
     */
    private BigDecimal calibrageTamis16 = new BigDecimal(0);

    /**
     */
    private BigDecimal calibrageTamis14 = new BigDecimal(0);

    /**
     */
    private BigDecimal calibrageTamis12 = new BigDecimal(0);

    /**
     */
    private BigDecimal calibrageTamis10 = new BigDecimal(0);

    /**
     */
    private BigDecimal calibrageBase = new BigDecimal(0);

    /**
     */
    private BigDecimal pourcentageGradeG0 = new BigDecimal(0);

    /**
     */
    private BigDecimal pourcentageGradeG1 = new BigDecimal(0);

    /**
     */
    private BigDecimal pourcentageGradeG2 = new BigDecimal(0);

    /**
     */
    private BigDecimal pourcentageGradeG3 = new BigDecimal(0);

    /**
     */
    private BigDecimal pourcentageGradeG4 = new BigDecimal(0);

    /**
     */
    private BigDecimal pourcentageGradeHN = new BigDecimal(0);

    /**
     */
    private String certificationTypeOrProject;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<JBill> bills = new HashSet<JBill>();
}
