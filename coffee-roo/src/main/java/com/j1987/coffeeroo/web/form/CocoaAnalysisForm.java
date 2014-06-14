package com.j1987.coffeeroo.web.form;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class CocoaAnalysisForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3250848435655271408L;
	
	private Long id;
	
	private int version;
	
	private Long tourId;
	
	@NotNull
	private String reference;
	
	/**
     */
    @NotNull
    private String dateOfAnalysis;

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
    private BigDecimal netWeightOfProductAccepted = new BigDecimal(0);
    
    
    private BigDecimal poidsMatieresEtrangeres = new BigDecimal(0);
    
    private BigDecimal pourcentageMatieresEtrangeres = new BigDecimal(0);

    /**
     */
    private Long provenance;

    /**
     */
    private String dealerCode;

    /**
     */
    private String factoryCode;
    
    /**
     */
    private String bridgeCode;

    /**
     */
    private String exporterCode;

    /**
     */
    private String supplierCode;

    /**
     */
    private String startTime;

    /**
     */
    private String endTime;

    /**
     */
    private Long totalOfBagPushed = Long.valueOf(0);

    /**
     */
    private Long totalOfReportedBags = Long.valueOf(0);

    /**
     */
    private Long numberOfBagAllowed = Long.valueOf(0);

    /**
     */
    @NotNull
    private String sampleCode;

    /**
     */
    private String numberSAIGIC;
    
    /**
     */
    private String certificationTypeOrProject;
    
    
    /**
     */
    private String classification;
    
    /**
     */
    private Boolean conformity;
    
    /**
     */
    private Long acceptation;
    
    private Boolean status;
    
    /*
	 * 
	 */
	private BigDecimal tauxHumidite1 = new BigDecimal(0);
	/*
	 * 
	 */
	private BigDecimal tauxHumidite2 = new BigDecimal(0);
	/*
	 * 
	 */
	private BigDecimal tauxHumidite3 = new BigDecimal(0);
	/*
	 * 
	 */
	private BigDecimal moyenneTauxHumidite = new BigDecimal(0);
	/*
	 * 
	 */
	private BigDecimal nombreFeves = new BigDecimal(0);
	/*
	 * 
	 */
	private BigDecimal fevesPar100g = new BigDecimal(0);
	/*
	 * 
	 */
	private BigDecimal poidsBrisures = new BigDecimal(0);
	/*
	 * 
	 */
	private BigDecimal pourcentageBrisures = new BigDecimal(0);
	/*
	 * 
	 */
	private Long fevesMoisiesPlateau1 = Long.valueOf(0);
	/*
	 * 
	 */
	private Long fevesMoisiesPlateau2 = Long.valueOf(0);
	/*
	 * 
	 */
	private Long fevesMoisiesPlateau3 = Long.valueOf(0);
	/*
	 * 
	 */
	private Long totalFevesMoisies = Long.valueOf(0);
	/*
	 * 
	 */
	private BigDecimal pourcentageFevesMoisies = new BigDecimal(0);
	/*
	 * 
	 */
	private Long fevesArdoiseesPlateau1 = Long.valueOf(0);
	/*
	 * 
	 */
	private Long fevesArdoiseesPlateau2 = Long.valueOf(0);
	/*
	 * 
	 */
	private Long fevesArdoiseesPlateau3 = Long.valueOf(0);
	/*
	 * 
	 */
	private Long totalFevesArdoisees = Long.valueOf(0);
	/*
	 * 
	 */
	private BigDecimal pourcentageFevesArdoisees = new BigDecimal(0);
	/*
	 * 
	 */
	private Long fevesMiteesPlateau1 = Long.valueOf(0);
	/*
	 * 
	 */
	private Long fevesMiteesPlateau2 = Long.valueOf(0);
	/*
	 * 
	 */
	private Long fevesMiteesPlateau3 = Long.valueOf(0);
	/*
	 * 
	 */
	private Long totalFevesMitees = Long.valueOf(0);
	/*
	 * 
	 */
	private BigDecimal pourcentageFevesMitees = new BigDecimal(0);
	/*
	 * 
	 */
	private Long fevesGermeesPlateau1 = Long.valueOf(0);
	/*
	 * 
	 */
	private Long fevesGermeesPlateau2 = Long.valueOf(0);
	/*
	 * 
	 */
	private Long fevesGermeesPlateau3 = Long.valueOf(0);
	/*
	 * 
	 */
	private Long totalFevesGermees = Long.valueOf(0);
	/*
	 * 
	 */
	private BigDecimal pourcentageFevesGermees = new BigDecimal(0);
	/*
	 * 
	 */
	private Long fevesPlatesPlateau1 = Long.valueOf(0);
	/*
	 * 
	 */
	private Long fevesPlatesPlateau2 = Long.valueOf(0);
	/*
	 * 
	 */
	private Long fevesPlatesPlateau3 = Long.valueOf(0);
	/*
	 * 
	 */
	private Long totalFevesPlates = Long.valueOf(0);
	/*
	 * 
	 */
	private BigDecimal pourcentageFevesPlates = new BigDecimal(0);
	/*
	 * 
	 */
	private Long totalFevesDefectueusesPlateau1 = Long.valueOf(0);
	/*
	 * 
	 */
	private Long totalFevesDefectueusesPlateau2 = Long.valueOf(0);
	/*
	 * 
	 */
	private Long totalFevesDefectueusesPlateau3 = Long.valueOf(0);
	/*
	 * 
	 */
	private Long sommeTotalFevesDefectueuses = Long.valueOf(0);
	/*
	 * 
	 */
	private BigDecimal pourcentageTotalFevesDefectueuses = new BigDecimal(0);
	/*
	 * 
	 */
	private Long fevesViolettePlateau1 = Long.valueOf(0);
	/*
	 * 
	 */
	private Long fevesViolettePlateau2 = Long.valueOf(0);
	/*
	 * 
	 */
	private Long fevesViolettePlateau3 = Long.valueOf(0);
	/*
	 * 
	 */
	private Long totalFevesViolette = Long.valueOf(0);
	/*
	 * 
	 */
	private BigDecimal pourcentageFevesViolette = new BigDecimal(0);



	public CocoaAnalysisForm() {
		// TODO Auto-generated constructor stub
	}



	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}



	/**
	 * @return the tourId
	 */
	public Long getTourId() {
		return tourId;
	}



	/**
	 * @param tourId the tourId to set
	 */
	public void setTourId(Long tourId) {
		this.tourId = tourId;
	}



	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}



	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}



	/**
	 * @return the dateOfAnalysis
	 */
	public String getDateOfAnalysis() {
		return dateOfAnalysis;
	}



	/**
	 * @param dateOfAnalysis the dateOfAnalysis to set
	 */
	public void setDateOfAnalysis(String dateOfAnalysis) {
		this.dateOfAnalysis = dateOfAnalysis;
	}



	/**
	 * @return the numberLading
	 */
	public String getNumberLading() {
		return numberLading;
	}



	/**
	 * @param numberLading the numberLading to set
	 */
	public void setNumberLading(String numberLading) {
		this.numberLading = numberLading;
	}



	/**
	 * @return the truckNumber
	 */
	public String getTruckNumber() {
		return truckNumber;
	}



	/**
	 * @param truckNumber the truckNumber to set
	 */
	public void setTruckNumber(String truckNumber) {
		this.truckNumber = truckNumber;
	}



	/**
	 * @return the netWeightOfProductAccepted
	 */
	public BigDecimal getNetWeightOfProductAccepted() {
		return netWeightOfProductAccepted;
	}



	/**
	 * @param netWeightOfProductAccepted the netWeightOfProductAccepted to set
	 */
	public void setNetWeightOfProductAccepted(BigDecimal netWeightOfProductAccepted) {
		this.netWeightOfProductAccepted = netWeightOfProductAccepted;
	}



	/**
	 * @return the provenance
	 */
	public Long getProvenance() {
		return provenance;
	}



	/**
	 * @param provenance the provenance to set
	 */
	public void setProvenance(Long provenance) {
		this.provenance = provenance;
	}



	/**
	 * @return the dealerCode
	 */
	public String getDealerCode() {
		return dealerCode;
	}



	/**
	 * @param dealerCode the dealerCode to set
	 */
	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}



	/**
	 * @return the factoryCode
	 */
	public String getFactoryCode() {
		return factoryCode;
	}



	/**
	 * @param factoryCode the factoryCode to set
	 */
	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}



	/**
	 * @return the bridgeCode
	 */
	public String getBridgeCode() {
		return bridgeCode;
	}



	/**
	 * @param bridgeCode the bridgeCode to set
	 */
	public void setBridgeCode(String bridgeCode) {
		this.bridgeCode = bridgeCode;
	}



	/**
	 * @return the exporterCode
	 */
	public String getExporterCode() {
		return exporterCode;
	}



	/**
	 * @param exporterCode the exporterCode to set
	 */
	public void setExporterCode(String exporterCode) {
		this.exporterCode = exporterCode;
	}



	/**
	 * @return the supplierCode
	 */
	public String getSupplierCode() {
		return supplierCode;
	}



	/**
	 * @param supplierCode the supplierCode to set
	 */
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}



	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}



	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}



	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}



	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}



	/**
	 * @return the totalOfBagPushed
	 */
	public Long getTotalOfBagPushed() {
		return totalOfBagPushed;
	}



	/**
	 * @param totalOfBagPushed the totalOfBagPushed to set
	 */
	public void setTotalOfBagPushed(Long totalOfBagPushed) {
		this.totalOfBagPushed = totalOfBagPushed;
	}



	/**
	 * @return the totalOfReportedBags
	 */
	public Long getTotalOfReportedBags() {
		return totalOfReportedBags;
	}



	/**
	 * @param totalOfReportedBags the totalOfReportedBags to set
	 */
	public void setTotalOfReportedBags(Long totalOfReportedBags) {
		this.totalOfReportedBags = totalOfReportedBags;
	}



	/**
	 * @return the numberOfBagAllowed
	 */
	public Long getNumberOfBagAllowed() {
		return numberOfBagAllowed;
	}



	/**
	 * @param numberOfBagAllowed the numberOfBagAllowed to set
	 */
	public void setNumberOfBagAllowed(Long numberOfBagAllowed) {
		this.numberOfBagAllowed = numberOfBagAllowed;
	}



	/**
	 * @return the sampleCode
	 */
	public String getSampleCode() {
		return sampleCode;
	}



	/**
	 * @param sampleCode the sampleCode to set
	 */
	public void setSampleCode(String sampleCode) {
		this.sampleCode = sampleCode;
	}



	/**
	 * @return the numberSAIGIC
	 */
	public String getNumberSAIGIC() {
		return numberSAIGIC;
	}



	/**
	 * @param numberSAIGIC the numberSAIGIC to set
	 */
	public void setNumberSAIGIC(String numberSAIGIC) {
		this.numberSAIGIC = numberSAIGIC;
	}



	/**
	 * @return the certificationTypeOrProject
	 */
	public String getCertificationTypeOrProject() {
		return certificationTypeOrProject;
	}



	/**
	 * @param certificationTypeOrProject the certificationTypeOrProject to set
	 */
	public void setCertificationTypeOrProject(String certificationTypeOrProject) {
		this.certificationTypeOrProject = certificationTypeOrProject;
	}



	/**
	 * @return the classification
	 */
	public String getClassification() {
		return classification;
	}



	/**
	 * @param classification the classification to set
	 */
	public void setClassification(String classification) {
		this.classification = classification;
	}



	/**
	 * @return the conformity
	 */
	public Boolean getConformity() {
		return conformity;
	}



	/**
	 * @param conformity the conformity to set
	 */
	public void setConformity(Boolean conformity) {
		this.conformity = conformity;
	}



	/**
	 * @return the acceptation
	 */
	public Long getAcceptation() {
		return acceptation;
	}



	/**
	 * @param acceptation the acceptation to set
	 */
	public void setAcceptation(Long acceptation) {
		this.acceptation = acceptation;
	}



	/**
	 * @return the tauxHumidite1
	 */
	public BigDecimal getTauxHumidite1() {
		return tauxHumidite1;
	}



	/**
	 * @param tauxHumidite1 the tauxHumidite1 to set
	 */
	public void setTauxHumidite1(BigDecimal tauxHumidite1) {
		this.tauxHumidite1 = tauxHumidite1;
	}



	/**
	 * @return the tauxHumidite2
	 */
	public BigDecimal getTauxHumidite2() {
		return tauxHumidite2;
	}



	/**
	 * @param tauxHumidite2 the tauxHumidite2 to set
	 */
	public void setTauxHumidite2(BigDecimal tauxHumidite2) {
		this.tauxHumidite2 = tauxHumidite2;
	}



	/**
	 * @return the tauxHumidite3
	 */
	public BigDecimal getTauxHumidite3() {
		return tauxHumidite3;
	}



	/**
	 * @param tauxHumidite3 the tauxHumidite3 to set
	 */
	public void setTauxHumidite3(BigDecimal tauxHumidite3) {
		this.tauxHumidite3 = tauxHumidite3;
	}



	/**
	 * @return the moyenneTauxHumidite
	 */
	public BigDecimal getMoyenneTauxHumidite() {
		return moyenneTauxHumidite;
	}



	/**
	 * @param moyenneTauxHumidite the moyenneTauxHumidite to set
	 */
	public void setMoyenneTauxHumidite(BigDecimal moyenneTauxHumidite) {
		this.moyenneTauxHumidite = moyenneTauxHumidite;
	}



	/**
	 * @return the nombreFeves
	 */
	public BigDecimal getNombreFeves() {
		return nombreFeves;
	}



	/**
	 * @param nombreFeves the nombreFeves to set
	 */
	public void setNombreFeves(BigDecimal nombreFeves) {
		this.nombreFeves = nombreFeves;
	}



	/**
	 * @return the fevesPar100g
	 */
	public BigDecimal getFevesPar100g() {
		return fevesPar100g;
	}



	/**
	 * @param fevesPar100g the fevesPar100g to set
	 */
	public void setFevesPar100g(BigDecimal fevesPar100g) {
		this.fevesPar100g = fevesPar100g;
	}



	/**
	 * @return the poidsBrisures
	 */
	public BigDecimal getPoidsBrisures() {
		return poidsBrisures;
	}



	/**
	 * @param poidsBrisures the poidsBrisures to set
	 */
	public void setPoidsBrisures(BigDecimal poidsBrisures) {
		this.poidsBrisures = poidsBrisures;
	}



	/**
	 * @return the pourcentageBrisures
	 */
	public BigDecimal getPourcentageBrisures() {
		return pourcentageBrisures;
	}



	/**
	 * @param pourcentageBrisures the pourcentageBrisures to set
	 */
	public void setPourcentageBrisures(BigDecimal pourcentageBrisures) {
		this.pourcentageBrisures = pourcentageBrisures;
	}



	/**
	 * @return the fevesMoisiesPlateau1
	 */
	public Long getFevesMoisiesPlateau1() {
		return fevesMoisiesPlateau1;
	}



	/**
	 * @param fevesMoisiesPlateau1 the fevesMoisiesPlateau1 to set
	 */
	public void setFevesMoisiesPlateau1(Long fevesMoisiesPlateau1) {
		this.fevesMoisiesPlateau1 = fevesMoisiesPlateau1;
	}



	/**
	 * @return the fevesMoisiesPlateau2
	 */
	public Long getFevesMoisiesPlateau2() {
		return fevesMoisiesPlateau2;
	}



	/**
	 * @param fevesMoisiesPlateau2 the fevesMoisiesPlateau2 to set
	 */
	public void setFevesMoisiesPlateau2(Long fevesMoisiesPlateau2) {
		this.fevesMoisiesPlateau2 = fevesMoisiesPlateau2;
	}



	/**
	 * @return the fevesMoisiesPlateau3
	 */
	public Long getFevesMoisiesPlateau3() {
		return fevesMoisiesPlateau3;
	}



	/**
	 * @param fevesMoisiesPlateau3 the fevesMoisiesPlateau3 to set
	 */
	public void setFevesMoisiesPlateau3(Long fevesMoisiesPlateau3) {
		this.fevesMoisiesPlateau3 = fevesMoisiesPlateau3;
	}



	/**
	 * @return the totalFevesMoisies
	 */
	public Long getTotalFevesMoisies() {
		return totalFevesMoisies;
	}



	/**
	 * @param totalFevesMoisies the totalFevesMoisies to set
	 */
	public void setTotalFevesMoisies(Long totalFevesMoisies) {
		this.totalFevesMoisies = totalFevesMoisies;
	}



	/**
	 * @return the pourcentageFevesMoisies
	 */
	public BigDecimal getPourcentageFevesMoisies() {
		return pourcentageFevesMoisies;
	}



	/**
	 * @param pourcentageFevesMoisies the pourcentageFevesMoisies to set
	 */
	public void setPourcentageFevesMoisies(BigDecimal pourcentageFevesMoisies) {
		this.pourcentageFevesMoisies = pourcentageFevesMoisies;
	}



	/**
	 * @return the fevesArdoiseesPlateau1
	 */
	public Long getFevesArdoiseesPlateau1() {
		return fevesArdoiseesPlateau1;
	}



	/**
	 * @param fevesArdoiseesPlateau1 the fevesArdoiseesPlateau1 to set
	 */
	public void setFevesArdoiseesPlateau1(Long fevesArdoiseesPlateau1) {
		this.fevesArdoiseesPlateau1 = fevesArdoiseesPlateau1;
	}



	/**
	 * @return the fevesArdoiseesPlateau2
	 */
	public Long getFevesArdoiseesPlateau2() {
		return fevesArdoiseesPlateau2;
	}



	/**
	 * @param fevesArdoiseesPlateau2 the fevesArdoiseesPlateau2 to set
	 */
	public void setFevesArdoiseesPlateau2(Long fevesArdoiseesPlateau2) {
		this.fevesArdoiseesPlateau2 = fevesArdoiseesPlateau2;
	}



	/**
	 * @return the fevesArdoiseesPlateau3
	 */
	public Long getFevesArdoiseesPlateau3() {
		return fevesArdoiseesPlateau3;
	}



	/**
	 * @param fevesArdoiseesPlateau3 the fevesArdoiseesPlateau3 to set
	 */
	public void setFevesArdoiseesPlateau3(Long fevesArdoiseesPlateau3) {
		this.fevesArdoiseesPlateau3 = fevesArdoiseesPlateau3;
	}



	/**
	 * @return the totalfevesArdoisees
	 */
	public Long getTotalFevesArdoisees() {
		return totalFevesArdoisees;
	}



	/**
	 * @param totalfevesArdoisees the totalfevesArdoisees to set
	 */
	public void setTotalFevesArdoisees(Long totalfevesArdoisees) {
		this.totalFevesArdoisees = totalfevesArdoisees;
	}



	/**
	 * @return the pourcentageFevesArdoisees
	 */
	public BigDecimal getPourcentageFevesArdoisees() {
		return pourcentageFevesArdoisees;
	}



	/**
	 * @param pourcentageFevesArdoisees the pourcentageFevesArdoisees to set
	 */
	public void setPourcentageFevesArdoisees(BigDecimal pourcentageFevesArdoisees) {
		this.pourcentageFevesArdoisees = pourcentageFevesArdoisees;
	}



	/**
	 * @return the fevesMiteesPlateau1
	 */
	public Long getFevesMiteesPlateau1() {
		return fevesMiteesPlateau1;
	}



	/**
	 * @param fevesMiteesPlateau1 the fevesMiteesPlateau1 to set
	 */
	public void setFevesMiteesPlateau1(Long fevesMiteesPlateau1) {
		this.fevesMiteesPlateau1 = fevesMiteesPlateau1;
	}



	/**
	 * @return the fevesMiteesPlateau2
	 */
	public Long getFevesMiteesPlateau2() {
		return fevesMiteesPlateau2;
	}



	/**
	 * @param fevesMiteesPlateau2 the fevesMiteesPlateau2 to set
	 */
	public void setFevesMiteesPlateau2(Long fevesMiteesPlateau2) {
		this.fevesMiteesPlateau2 = fevesMiteesPlateau2;
	}



	/**
	 * @return the fevesMiteesPlateau3
	 */
	public Long getFevesMiteesPlateau3() {
		return fevesMiteesPlateau3;
	}



	/**
	 * @param fevesMiteesPlateau3 the fevesMiteesPlateau3 to set
	 */
	public void setFevesMiteesPlateau3(Long fevesMiteesPlateau3) {
		this.fevesMiteesPlateau3 = fevesMiteesPlateau3;
	}



	/**
	 * @return the totalFevesMitees
	 */
	public Long getTotalFevesMitees() {
		return totalFevesMitees;
	}



	/**
	 * @param totalFevesMitees the totalFevesMitees to set
	 */
	public void setTotalFevesMitees(Long totalFevesMitees) {
		this.totalFevesMitees = totalFevesMitees;
	}



	/**
	 * @return the pourcentageFevesMitees
	 */
	public BigDecimal getPourcentageFevesMitees() {
		return pourcentageFevesMitees;
	}



	/**
	 * @param pourcentageFevesMitees the pourcentageFevesMitees to set
	 */
	public void setPourcentageFevesMitees(BigDecimal pourcentageFevesMitees) {
		this.pourcentageFevesMitees = pourcentageFevesMitees;
	}



	/**
	 * @return the fevesGermeesPlateau1
	 */
	public Long getFevesGermeesPlateau1() {
		return fevesGermeesPlateau1;
	}



	/**
	 * @param fevesGermeesPlateau1 the fevesGermeesPlateau1 to set
	 */
	public void setFevesGermeesPlateau1(Long fevesGermeesPlateau1) {
		this.fevesGermeesPlateau1 = fevesGermeesPlateau1;
	}



	/**
	 * @return the fevesGermeesPlateau2
	 */
	public Long getFevesGermeesPlateau2() {
		return fevesGermeesPlateau2;
	}



	/**
	 * @param fevesGermeesPlateau2 the fevesGermeesPlateau2 to set
	 */
	public void setFevesGermeesPlateau2(Long fevesGermeesPlateau2) {
		this.fevesGermeesPlateau2 = fevesGermeesPlateau2;
	}



	/**
	 * @return the fevesGermeesPlateau3
	 */
	public Long getFevesGermeesPlateau3() {
		return fevesGermeesPlateau3;
	}



	/**
	 * @param fevesGermeesPlateau3 the fevesGermeesPlateau3 to set
	 */
	public void setFevesGermeesPlateau3(Long fevesGermeesPlateau3) {
		this.fevesGermeesPlateau3 = fevesGermeesPlateau3;
	}



	/**
	 * @return the totalFevesGermees
	 */
	public Long getTotalFevesGermees() {
		return totalFevesGermees;
	}



	/**
	 * @param totalFevesGermees the totalFevesGermees to set
	 */
	public void setTotalFevesGermees(Long totalFevesGermees) {
		this.totalFevesGermees = totalFevesGermees;
	}



	/**
	 * @return the pourcentageFevesGermees
	 */
	public BigDecimal getPourcentageFevesGermees() {
		return pourcentageFevesGermees;
	}



	/**
	 * @param pourcentageFevesGermees the pourcentageFevesGermees to set
	 */
	public void setPourcentageFevesGermees(BigDecimal pourcentageFevesGermees) {
		this.pourcentageFevesGermees = pourcentageFevesGermees;
	}



	/**
	 * @return the fevesPlatesPlateau1
	 */
	public Long getFevesPlatesPlateau1() {
		return fevesPlatesPlateau1;
	}



	/**
	 * @param fevesPlatesPlateau1 the fevesPlatesPlateau1 to set
	 */
	public void setFevesPlatesPlateau1(Long fevesPlatesPlateau1) {
		this.fevesPlatesPlateau1 = fevesPlatesPlateau1;
	}



	/**
	 * @return the fevesPlatesPlateau2
	 */
	public Long getFevesPlatesPlateau2() {
		return fevesPlatesPlateau2;
	}



	/**
	 * @param fevesPlatesPlateau2 the fevesPlatesPlateau2 to set
	 */
	public void setFevesPlatesPlateau2(Long fevesPlatesPlateau2) {
		this.fevesPlatesPlateau2 = fevesPlatesPlateau2;
	}



	/**
	 * @return the fevesPlatesPlateau3
	 */
	public Long getFevesPlatesPlateau3() {
		return fevesPlatesPlateau3;
	}



	/**
	 * @param fevesPlatesPlateau3 the fevesPlatesPlateau3 to set
	 */
	public void setFevesPlatesPlateau3(Long fevesPlatesPlateau3) {
		this.fevesPlatesPlateau3 = fevesPlatesPlateau3;
	}



	/**
	 * @return the totalFevesPlates
	 */
	public Long getTotalFevesPlates() {
		return totalFevesPlates;
	}



	/**
	 * @param totalFevesPlates the totalFevesPlates to set
	 */
	public void setTotalFevesPlates(Long totalFevesPlates) {
		this.totalFevesPlates = totalFevesPlates;
	}



	/**
	 * @return the pourcentageFevesPlates
	 */
	public BigDecimal getPourcentageFevesPlates() {
		return pourcentageFevesPlates;
	}



	/**
	 * @param pourcentageFevesPlates the pourcentageFevesPlates to set
	 */
	public void setPourcentageFevesPlates(BigDecimal pourcentageFevesPlates) {
		this.pourcentageFevesPlates = pourcentageFevesPlates;
	}



	/**
	 * @return the totalFevesDefectueusesPlateau1
	 */
	public Long getTotalFevesDefectueusesPlateau1() {
		return totalFevesDefectueusesPlateau1;
	}



	/**
	 * @param totalFevesDefectueusesPlateau1 the totalFevesDefectueusesPlateau1 to set
	 */
	public void setTotalFevesDefectueusesPlateau1(
			Long totalFevesDefectueusesPlateau1) {
		this.totalFevesDefectueusesPlateau1 = totalFevesDefectueusesPlateau1;
	}



	/**
	 * @return the totalFevesDefectueusesPlateau2
	 */
	public Long getTotalFevesDefectueusesPlateau2() {
		return totalFevesDefectueusesPlateau2;
	}



	/**
	 * @param totalFevesDefectueusesPlateau2 the totalFevesDefectueusesPlateau2 to set
	 */
	public void setTotalFevesDefectueusesPlateau2(
			Long totalFevesDefectueusesPlateau2) {
		this.totalFevesDefectueusesPlateau2 = totalFevesDefectueusesPlateau2;
	}



	/**
	 * @return the totalFevesDefectueusesPlateau3
	 */
	public Long getTotalFevesDefectueusesPlateau3() {
		return totalFevesDefectueusesPlateau3;
	}



	/**
	 * @param totalFevesDefectueusesPlateau3 the totalFevesDefectueusesPlateau3 to set
	 */
	public void setTotalFevesDefectueusesPlateau3(
			Long totalFevesDefectueusesPlateau3) {
		this.totalFevesDefectueusesPlateau3 = totalFevesDefectueusesPlateau3;
	}



	/**
	 * @return the sommeTotalFevesDefectueuses
	 */
	public Long getSommeTotalFevesDefectueuses() {
		return sommeTotalFevesDefectueuses;
	}



	/**
	 * @param sommeTotalFevesDefectueuses the sommeTotalFevesDefectueuses to set
	 */
	public void setSommeTotalFevesDefectueuses(Long sommeTotalFevesDefectueuses) {
		this.sommeTotalFevesDefectueuses = sommeTotalFevesDefectueuses;
	}



	/**
	 * @return the pourcentageTotalFevesDefectueuses
	 */
	public BigDecimal getPourcentageTotalFevesDefectueuses() {
		return pourcentageTotalFevesDefectueuses;
	}



	/**
	 * @param pourcentageTotalFevesDefectueuses the pourcentageTotalFevesDefectueuses to set
	 */
	public void setPourcentageTotalFevesDefectueuses(
			BigDecimal pourcentageTotalFevesDefectueuses) {
		this.pourcentageTotalFevesDefectueuses = pourcentageTotalFevesDefectueuses;
	}



	/**
	 * @return the fevesViolettePlateau1
	 */
	public Long getFevesViolettePlateau1() {
		return fevesViolettePlateau1;
	}



	/**
	 * @param fevesViolettePlateau1 the fevesViolettePlateau1 to set
	 */
	public void setFevesViolettePlateau1(Long fevesViolettePlateau1) {
		this.fevesViolettePlateau1 = fevesViolettePlateau1;
	}



	/**
	 * @return the fevesViolettePlateau2
	 */
	public Long getFevesViolettePlateau2() {
		return fevesViolettePlateau2;
	}



	/**
	 * @param fevesViolettePlateau2 the fevesViolettePlateau2 to set
	 */
	public void setFevesViolettePlateau2(Long fevesViolettePlateau2) {
		this.fevesViolettePlateau2 = fevesViolettePlateau2;
	}



	/**
	 * @return the fevesViolettePlateau3
	 */
	public Long getFevesViolettePlateau3() {
		return fevesViolettePlateau3;
	}



	/**
	 * @param fevesViolettePlateau3 the fevesViolettePlateau3 to set
	 */
	public void setFevesViolettePlateau3(Long fevesViolettePlateau3) {
		this.fevesViolettePlateau3 = fevesViolettePlateau3;
	}



	/**
	 * @return the totalFevesViolette
	 */
	public Long getTotalFevesViolette() {
		return totalFevesViolette;
	}



	/**
	 * @param totalFevesViolette the totalFevesViolette to set
	 */
	public void setTotalFevesViolette(Long totalFevesViolette) {
		this.totalFevesViolette = totalFevesViolette;
	}



	/**
	 * @return the pourcentageFevesViolette
	 */
	public BigDecimal getPourcentageFevesViolette() {
		return pourcentageFevesViolette;
	}



	/**
	 * @param pourcentageFevesViolette the pourcentageFevesViolette to set
	 */
	public void setPourcentageFevesViolette(BigDecimal pourcentageFevesViolette) {
		this.pourcentageFevesViolette = pourcentageFevesViolette;
	}



	/**
	 * @return the poidsMatieresEtrangeres
	 */
	public BigDecimal getPoidsMatieresEtrangeres() {
		return poidsMatieresEtrangeres;
	}



	/**
	 * @param poidsMatieresEtrangeres the poidsMatieresEtrangeres to set
	 */
	public void setPoidsMatieresEtrangeres(BigDecimal poidsMatieresEtrangeres) {
		this.poidsMatieresEtrangeres = poidsMatieresEtrangeres;
	}



	/**
	 * @return the pourcentageMatieresEtrangeres
	 */
	public BigDecimal getPourcentageMatieresEtrangeres() {
		return this.pourcentageMatieresEtrangeres;
	}



	/**
	 * @param pourcentageMatieresEtrangeres the pourcentageMatieresEtrangeres to set
	 */
	public void setPourcentageMatieresEtrangeres(
			BigDecimal pourcentageMatieresEtrangeres) {
		this.pourcentageMatieresEtrangeres = pourcentageMatieresEtrangeres;
	}



	/**
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}



	/**
	 * @param status the status to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}



	public int getVersion() {
		return version;
	}



	public void setVersion(int version) {
		this.version = version;
	}
	
	

}
