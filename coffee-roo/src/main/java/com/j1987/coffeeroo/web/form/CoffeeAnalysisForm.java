package com.j1987.coffeeroo.web.form;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class CoffeeAnalysisForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5487322124302987462L;
	
	private Long id;
	
	private Long tourId;
	
	private int version;
	
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
    private BigDecimal netWeightOfProductAccepted;

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
    private BigDecimal poidsMatieresEtrangeres = new BigDecimal(0);
    
    /**
     */
    private BigDecimal pourcentageMatieresEtrangeres = new BigDecimal(0);
    
    /**
     */
    private BigDecimal poidsDechetsParches = new BigDecimal(0);
    
    /**
     */
    private BigDecimal pourcentageDechetsParches = new BigDecimal(0);
    
    /**
     */
    private BigDecimal poidsDechetsCerise = new BigDecimal(0);
    
    /**
     */
    private BigDecimal pourcentageDechetsCerise = new BigDecimal(0);
    
    /**
     */
    private BigDecimal poidsDechetsDemiCerises = new BigDecimal(0);
    
    /**
     */
    private BigDecimal pourcentageDechetsDemiCerises = new BigDecimal(0);
    
    /**
     */
    private BigDecimal poidsDechetsCoques = new BigDecimal(0);
    
    /**
     */
    private BigDecimal pourcentageDechetsCoques = new BigDecimal(0);
    
    /**
     */
    private BigDecimal poidsDechetsPeaux = new BigDecimal(0);
    
    /**
     */
    private BigDecimal pourcentageDechetsPeaux = new BigDecimal(0);
    
    /**
     */
    private BigDecimal poidsSousTotalDechets = new BigDecimal(0);
    
    /**
     */
    private BigDecimal pourcentageSousTotalDechets = new BigDecimal(0);
    
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
    private BigDecimal CalibrageTamis18 = new BigDecimal(0);
    
    /**
     */
    private BigDecimal CalibrageTamis16 = new BigDecimal(0);
    
    /**
     */
    private BigDecimal CalibrageTamis14 = new BigDecimal(0);
    
    /**
     */
    private BigDecimal CalibrageTamis12 = new BigDecimal(0);
    
    /**
     */
    private BigDecimal CalibrageTamis10 = new BigDecimal(0);
    
    /**
     */
    private BigDecimal CalibrageBase = new BigDecimal(0);
    
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
    
    private Long acceptation;
    
    private Boolean confirmity;
    
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


	public CoffeeAnalysisForm() {
		// TODO Auto-generated constructor stub
	}

	public String getDateOfAnalysis() {
		return dateOfAnalysis;
	}

	public void setDateOfAnalysis(String dateOfAnalysis) {
		this.dateOfAnalysis = dateOfAnalysis;
	}

	public String getNumberLading() {
		return numberLading;
	}

	public void setNumberLading(String numberLading) {
		this.numberLading = numberLading;
	}

	public String getTruckNumber() {
		return truckNumber;
	}

	public void setTruckNumber(String truckNumber) {
		this.truckNumber = truckNumber;
	}

	public BigDecimal getNetWeightOfProductAccepted() {
		return netWeightOfProductAccepted;
	}

	public void setNetWeightOfProductAccepted(BigDecimal netWeightOfProductAccepted) {
		this.netWeightOfProductAccepted = netWeightOfProductAccepted;
	}

	public Long getProvenance() {
		return provenance;
	}

	public void setProvenance(Long provenance) {
		this.provenance = provenance;
	}

	public String getDealerCode() {
		return dealerCode;
	}

	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public String getExporterCode() {
		return exporterCode;
	}

	public void setExporterCode(String exporterCode) {
		this.exporterCode = exporterCode;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Long getTotalOfBagPushed() {
		return totalOfBagPushed;
	}

	public void setTotalOfBagPushed(Long totalOfBagPushed) {
		this.totalOfBagPushed = totalOfBagPushed;
	}

	public Long getTotalOfReportedBags() {
		return totalOfReportedBags;
	}

	public void setTotalOfReportedBags(Long totalOfReportedBags) {
		this.totalOfReportedBags = totalOfReportedBags;
	}

	public Long getNumberOfBagAllowed() {
		return numberOfBagAllowed;
	}

	public void setNumberOfBagAllowed(Long numberOfBagAllowed) {
		this.numberOfBagAllowed = numberOfBagAllowed;
	}

	public String getSampleCode() {
		return sampleCode;
	}

	public void setSampleCode(String sampleCode) {
		this.sampleCode = sampleCode;
	}

	public String getNumberSAIGIC() {
		return numberSAIGIC;
	}

	public void setNumberSAIGIC(String numberSAIGIC) {
		this.numberSAIGIC = numberSAIGIC;
	}

	public BigDecimal getPoidsMatieresEtrangeres() {
		return poidsMatieresEtrangeres;
	}

	public void setPoidsMatieresEtrangeres(BigDecimal poidsMatieresEtrangeres) {
		this.poidsMatieresEtrangeres = poidsMatieresEtrangeres;
	}

	public BigDecimal getPourcentageMatieresEtrangeres() {
		return pourcentageMatieresEtrangeres;
	}

	public void setPourcentageMatieresEtrangeres(
			BigDecimal pourcentageMatieresEtrangeres) {
		this.pourcentageMatieresEtrangeres = pourcentageMatieresEtrangeres;
	}

	public BigDecimal getPoidsDechetsParches() {
		return poidsDechetsParches;
	}

	public void setPoidsDechetsParches(BigDecimal poidsDechetsParches) {
		this.poidsDechetsParches = poidsDechetsParches;
	}

	public BigDecimal getPourcentageDechetsParches() {
		return pourcentageDechetsParches;
	}

	public void setPourcentageDechetsParches(BigDecimal pourcentageDechetsParches) {
		this.pourcentageDechetsParches = pourcentageDechetsParches;
	}

	public BigDecimal getPoidsDechetsCerise() {
		return poidsDechetsCerise;
	}

	public void setPoidsDechetsCerise(BigDecimal poidsDechetsCerise) {
		this.poidsDechetsCerise = poidsDechetsCerise;
	}

	public BigDecimal getPourcentageDechetsCerise() {
		return pourcentageDechetsCerise;
	}

	public void setPourcentageDechetsCerise(BigDecimal pourcentageDechetsCerise) {
		this.pourcentageDechetsCerise = pourcentageDechetsCerise;
	}

	public BigDecimal getPoidsDechetsDemiCerises() {
		return poidsDechetsDemiCerises;
	}

	public void setPoidsDechetsDemiCerises(BigDecimal poidsDechetsDemiCerises) {
		this.poidsDechetsDemiCerises = poidsDechetsDemiCerises;
	}

	public BigDecimal getPourcentageDechetsDemiCerises() {
		return pourcentageDechetsDemiCerises;
	}

	public void setPourcentageDechetsDemiCerises(
			BigDecimal pourcentageDechetsDemiCerises) {
		this.pourcentageDechetsDemiCerises = pourcentageDechetsDemiCerises;
	}

	public BigDecimal getPoidsDechetsCoques() {
		return poidsDechetsCoques;
	}

	public void setPoidsDechetsCoques(BigDecimal poidsDechetsCoques) {
		this.poidsDechetsCoques = poidsDechetsCoques;
	}

	public BigDecimal getPourcentageDechetsCoques() {
		return pourcentageDechetsCoques;
	}

	public void setPourcentageDechetsCoques(BigDecimal pourcentageDechetsCoques) {
		this.pourcentageDechetsCoques = pourcentageDechetsCoques;
	}

	public BigDecimal getPoidsDechetsPeaux() {
		return poidsDechetsPeaux;
	}

	public void setPoidsDechetsPeaux(BigDecimal poidsDechetsPeaux) {
		this.poidsDechetsPeaux = poidsDechetsPeaux;
	}

	public BigDecimal getPourcentageDechetsPeaux() {
		return pourcentageDechetsPeaux;
	}

	public void setPourcentageDechetsPeaux(BigDecimal pourcentageDechetsPeaux) {
		this.pourcentageDechetsPeaux = pourcentageDechetsPeaux;
	}

	public BigDecimal getPoidsSousTotalDechets() {
		return poidsSousTotalDechets;
	}

	public void setPoidsSousTotalDechets(BigDecimal poidsSousTotalDechets) {
		this.poidsSousTotalDechets = poidsSousTotalDechets;
	}

	public BigDecimal getPourcentageSousTotalDechets() {
		return pourcentageSousTotalDechets;
	}

	public void setPourcentageSousTotalDechets(
			BigDecimal pourcentageSousTotalDechets) {
		this.pourcentageSousTotalDechets = pourcentageSousTotalDechets;
	}

	public BigDecimal getPoidsHorsNormesGrainsNoirs() {
		return poidsHorsNormesGrainsNoirs;
	}

	public void setPoidsHorsNormesGrainsNoirs(BigDecimal poidsHorsNormesGrainsNoirs) {
		this.poidsHorsNormesGrainsNoirs = poidsHorsNormesGrainsNoirs;
	}

	public BigDecimal getPourcentageHorsNormesGrainsNoirs() {
		return pourcentageHorsNormesGrainsNoirs;
	}

	public void setPourcentageHorsNormesGrainsNoirs(
			BigDecimal pourcentageHorsNormesGrainsNoirs) {
		this.pourcentageHorsNormesGrainsNoirs = pourcentageHorsNormesGrainsNoirs;
	}

	public BigDecimal getPoidsHorsNormesGrainsDemiNoirs() {
		return poidsHorsNormesGrainsDemiNoirs;
	}

	public void setPoidsHorsNormesGrainsDemiNoirs(
			BigDecimal poidsHorsNormesGrainsDemiNoirs) {
		this.poidsHorsNormesGrainsDemiNoirs = poidsHorsNormesGrainsDemiNoirs;
	}

	public BigDecimal getPourcentageHorsNormesGrainsDemiNoirs() {
		return pourcentageHorsNormesGrainsDemiNoirs;
	}

	public void setPourcentageHorsNormesGrainsDemiNoirs(
			BigDecimal pourcentageHorsNormesGrainsDemiNoirs) {
		this.pourcentageHorsNormesGrainsDemiNoirs = pourcentageHorsNormesGrainsDemiNoirs;
	}

	public BigDecimal getPoidsHorsNormesBrisures() {
		return poidsHorsNormesBrisures;
	}

	public void setPoidsHorsNormesBrisures(BigDecimal poidsHorsNormesBrisures) {
		this.poidsHorsNormesBrisures = poidsHorsNormesBrisures;
	}

	public BigDecimal getPourcentageHorsNormesBrisures() {
		return pourcentageHorsNormesBrisures;
	}

	public void setPourcentageHorsNormesBrisures(
			BigDecimal pourcentageHorsNormesBrisures) {
		this.pourcentageHorsNormesBrisures = pourcentageHorsNormesBrisures;
	}

	public BigDecimal getPoidsSousTotalHorsNormes() {
		return poidsSousTotalHorsNormes;
	}

	public void setPoidsSousTotalHorsNormes(BigDecimal poidsSousTotalHorsNormes) {
		this.poidsSousTotalHorsNormes = poidsSousTotalHorsNormes;
	}

	public BigDecimal getPourcentageSousTotalHorsNormes() {
		return pourcentageSousTotalHorsNormes;
	}

	public void setPourcentageSousTotalHorsNormes(
			BigDecimal pourcentageSousTotalHorsNormes) {
		this.pourcentageSousTotalHorsNormes = pourcentageSousTotalHorsNormes;
	}

	public BigDecimal getPoidsGrainsAcceptablesVert() {
		return poidsGrainsAcceptablesVert;
	}

	public void setPoidsGrainsAcceptablesVert(BigDecimal poidsGrainsAcceptablesVert) {
		this.poidsGrainsAcceptablesVert = poidsGrainsAcceptablesVert;
	}

	public BigDecimal getPourcentageGrainsAcceptablesVert() {
		return pourcentageGrainsAcceptablesVert;
	}

	public void setPourcentageGrainsAcceptablesVert(
			BigDecimal pourcentageGrainsAcceptablesVert) {
		this.pourcentageGrainsAcceptablesVert = pourcentageGrainsAcceptablesVert;
	}

	public BigDecimal getPoidsGrainsAcceptablesSpongieux() {
		return poidsGrainsAcceptablesSpongieux;
	}

	public void setPoidsGrainsAcceptablesSpongieux(
			BigDecimal poidsGrainsAcceptablesSpongieux) {
		this.poidsGrainsAcceptablesSpongieux = poidsGrainsAcceptablesSpongieux;
	}

	public BigDecimal getPourcentageGrainsAcceptablesSpongieux() {
		return pourcentageGrainsAcceptablesSpongieux;
	}

	public void setPourcentageGrainsAcceptablesSpongieux(
			BigDecimal pourcentageGrainsAcceptablesSpongieux) {
		this.pourcentageGrainsAcceptablesSpongieux = pourcentageGrainsAcceptablesSpongieux;
	}

	public BigDecimal getPoidsGrainsAcceptablesDemiSombre() {
		return poidsGrainsAcceptablesDemiSombre;
	}

	public void setPoidsGrainsAcceptablesDemiSombre(
			BigDecimal poidsGrainsAcceptablesDemiSombre) {
		this.poidsGrainsAcceptablesDemiSombre = poidsGrainsAcceptablesDemiSombre;
	}

	public BigDecimal getPourcentageGrainsAcceptablesDemiSombre() {
		return pourcentageGrainsAcceptablesDemiSombre;
	}

	public void setPourcentageGrainsAcceptablesDemiSombre(
			BigDecimal pourcentageGrainsAcceptablesDemiSombre) {
		this.pourcentageGrainsAcceptablesDemiSombre = pourcentageGrainsAcceptablesDemiSombre;
	}

	public BigDecimal getPoidsGrainsAcceptablesScolytes() {
		return poidsGrainsAcceptablesScolytes;
	}

	public void setPoidsGrainsAcceptablesScolytes(
			BigDecimal poidsGrainsAcceptablesScolytes) {
		this.poidsGrainsAcceptablesScolytes = poidsGrainsAcceptablesScolytes;
	}

	public BigDecimal getPourcentageGrainsAcceptablesScolytes() {
		return pourcentageGrainsAcceptablesScolytes;
	}

	public void setPourcentageGrainsAcceptablesScolytes(
			BigDecimal pourcentageGrainsAcceptablesScolytes) {
		this.pourcentageGrainsAcceptablesScolytes = pourcentageGrainsAcceptablesScolytes;
	}

	public BigDecimal getPoidsGrainsAcceptablesIndesirables() {
		return poidsGrainsAcceptablesIndesirables;
	}

	public void setPoidsGrainsAcceptablesIndesirables(
			BigDecimal poidsGrainsAcceptablesIndesirables) {
		this.poidsGrainsAcceptablesIndesirables = poidsGrainsAcceptablesIndesirables;
	}

	public BigDecimal getPourcentageGrainsAcceptablesIndesirables() {
		return pourcentageGrainsAcceptablesIndesirables;
	}

	public void setPourcentageGrainsAcceptablesIndesirables(
			BigDecimal pourcentageGrainsAcceptablesIndesirables) {
		this.pourcentageGrainsAcceptablesIndesirables = pourcentageGrainsAcceptablesIndesirables;
	}

	public BigDecimal getPoidsGrainsAcceptablesImmature() {
		return poidsGrainsAcceptablesImmature;
	}

	public void setPoidsGrainsAcceptablesImmature(
			BigDecimal poidsGrainsAcceptablesImmature) {
		this.poidsGrainsAcceptablesImmature = poidsGrainsAcceptablesImmature;
	}

	public BigDecimal getPourcentageGrainsAcceptablesImmature() {
		return pourcentageGrainsAcceptablesImmature;
	}

	public void setPourcentageGrainsAcceptablesImmature(
			BigDecimal pourcentageGrainsAcceptablesImmature) {
		this.pourcentageGrainsAcceptablesImmature = pourcentageGrainsAcceptablesImmature;
	}

	public BigDecimal getCalibrageTamis18() {
		return CalibrageTamis18;
	}

	public void setCalibrageTamis18(BigDecimal calibrageTamis18) {
		CalibrageTamis18 = calibrageTamis18;
	}

	public BigDecimal getCalibrageTamis16() {
		return CalibrageTamis16;
	}

	public void setCalibrageTamis16(BigDecimal calibrageTamis16) {
		CalibrageTamis16 = calibrageTamis16;
	}

	public BigDecimal getCalibrageTamis14() {
		return CalibrageTamis14;
	}

	public void setCalibrageTamis14(BigDecimal calibrageTamis14) {
		CalibrageTamis14 = calibrageTamis14;
	}

	public BigDecimal getCalibrageTamis12() {
		return CalibrageTamis12;
	}

	public void setCalibrageTamis12(BigDecimal calibrageTamis12) {
		CalibrageTamis12 = calibrageTamis12;
	}

	public BigDecimal getCalibrageTamis10() {
		return CalibrageTamis10;
	}

	public void setCalibrageTamis10(BigDecimal calibrageTamis10) {
		CalibrageTamis10 = calibrageTamis10;
	}

	public BigDecimal getCalibrageBase() {
		return CalibrageBase;
	}

	public void setCalibrageBase(BigDecimal calibrageBase) {
		CalibrageBase = calibrageBase;
	}

	public BigDecimal getPourcentageGradeG0() {
		return pourcentageGradeG0;
	}

	public void setPourcentageGradeG0(BigDecimal pourcentageGradeG0) {
		this.pourcentageGradeG0 = pourcentageGradeG0;
	}

	public BigDecimal getPourcentageGradeG1() {
		return pourcentageGradeG1;
	}

	public void setPourcentageGradeG1(BigDecimal pourcentageGradeG1) {
		this.pourcentageGradeG1 = pourcentageGradeG1;
	}

	public BigDecimal getPourcentageGradeG2() {
		return pourcentageGradeG2;
	}

	public void setPourcentageGradeG2(BigDecimal pourcentageGradeG2) {
		this.pourcentageGradeG2 = pourcentageGradeG2;
	}

	public BigDecimal getPourcentageGradeG3() {
		return pourcentageGradeG3;
	}

	public void setPourcentageGradeG3(BigDecimal pourcentageGradeG3) {
		this.pourcentageGradeG3 = pourcentageGradeG3;
	}

	public BigDecimal getPourcentageGradeG4() {
		return pourcentageGradeG4;
	}

	public void setPourcentageGradeG4(BigDecimal pourcentageGradeG4) {
		this.pourcentageGradeG4 = pourcentageGradeG4;
	}

	public BigDecimal getPourcentageGradeHN() {
		return pourcentageGradeHN;
	}

	public void setPourcentageGradeHN(BigDecimal pourcentageGradeHN) {
		this.pourcentageGradeHN = pourcentageGradeHN;
	}

	public Long getAcceptation() {
		return acceptation;
	}

	public void setAcceptation(Long acceptation) {
		this.acceptation = acceptation;
	}

	public Boolean getConfirmity() {
		return confirmity;
	}

	public void setConfirmity(Boolean confirmity) {
		this.confirmity = confirmity;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTourId() {
		return tourId;
	}

	public void setTourId(Long tourId) {
		this.tourId = tourId;
	}

	public String getBridgeCode() {
		return bridgeCode;
	}

	public void setBridgeCode(String bridgeCode) {
		this.bridgeCode = bridgeCode;
	}

	public BigDecimal getPoidsSousTotalGrainsAcceptables() {
		return poidsSousTotalGrainsAcceptables;
	}

	public void setPoidsSousTotalGrainsAcceptables(
			BigDecimal poidsSousTotalGrainsAcceptables) {
		this.poidsSousTotalGrainsAcceptables = poidsSousTotalGrainsAcceptables;
	}

	public BigDecimal getPourcentageSousTotalGrainsAcceptables() {
		return pourcentageSousTotalGrainsAcceptables;
	}

	public void setPourcentageSousTotalGrainsAcceptables(
			BigDecimal pourcentageSousTotalGrainsAcceptables) {
		this.pourcentageSousTotalGrainsAcceptables = pourcentageSousTotalGrainsAcceptables;
	}

	public BigDecimal getTauxHumidite1() {
		return tauxHumidite1;
	}

	public void setTauxHumidite1(BigDecimal tauxHumidite1) {
		this.tauxHumidite1 = tauxHumidite1;
	}

	public BigDecimal getTauxHumidite2() {
		return tauxHumidite2;
	}

	public void setTauxHumidite2(BigDecimal tauxHumidite2) {
		this.tauxHumidite2 = tauxHumidite2;
	}

	public BigDecimal getTauxHumidite3() {
		return tauxHumidite3;
	}

	public void setTauxHumidite3(BigDecimal tauxHumidite3) {
		this.tauxHumidite3 = tauxHumidite3;
	}

	public BigDecimal getMoyenneTauxHumidite() {
		return moyenneTauxHumidite;
	}

	public void setMoyenneTauxHumidite(BigDecimal moyenneTauxHumidite) {
		this.moyenneTauxHumidite = moyenneTauxHumidite;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	
	
}
