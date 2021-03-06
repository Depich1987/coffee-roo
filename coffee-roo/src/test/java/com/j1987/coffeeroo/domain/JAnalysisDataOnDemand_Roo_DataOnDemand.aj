// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.j1987.coffeeroo.domain;

import com.j1987.coffeeroo.domain.JAnalysis;
import com.j1987.coffeeroo.domain.JAnalysisDataOnDemand;
import com.j1987.coffeeroo.domain.JBridge;
import com.j1987.coffeeroo.domain.JExporterDataOnDemand;
import com.j1987.coffeeroo.domain.JFirmDataOnDemand;
import com.j1987.coffeeroo.domain.JLocalizationDataOnDemand;
import com.j1987.coffeeroo.domain.JSupplierDataOnDemand;
import com.j1987.coffeeroo.domain.JTourDataOnDemand;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect JAnalysisDataOnDemand_Roo_DataOnDemand {
    
    declare @type: JAnalysisDataOnDemand: @Component;
    
    private Random JAnalysisDataOnDemand.rnd = new SecureRandom();
    
    private List<JAnalysis> JAnalysisDataOnDemand.data;
    
    @Autowired
    JFirmDataOnDemand JAnalysisDataOnDemand.jFirmDataOnDemand;
    
    @Autowired
    JExporterDataOnDemand JAnalysisDataOnDemand.jExporterDataOnDemand;
    
    @Autowired
    JLocalizationDataOnDemand JAnalysisDataOnDemand.jLocalizationDataOnDemand;
    
    @Autowired
    JSupplierDataOnDemand JAnalysisDataOnDemand.jSupplierDataOnDemand;
    
    @Autowired
    JTourDataOnDemand JAnalysisDataOnDemand.jTourDataOnDemand;
    
    public JAnalysis JAnalysisDataOnDemand.getNewTransientJAnalysis(int index) {
        JAnalysis obj = new JAnalysis();
        setAcceptation(obj, index);
        setBridge(obj, index);
        setCalibrageBase(obj, index);
        setCalibrageTamis10(obj, index);
        setCalibrageTamis12(obj, index);
        setCalibrageTamis14(obj, index);
        setCalibrageTamis16(obj, index);
        setCalibrageTamis18(obj, index);
        setCertificationTypeOrProject(obj, index);
        setClassification(obj, index);
        setConformity(obj, index);
        setCreatedBy(obj, index);
        setCreationDate(obj, index);
        setDateOfAnalysis(obj, index);
        setDealerName(obj, index);
        setEndTime(obj, index);
        setExistInBill(obj, index);
        setExporterName(obj, index);
        setFactoryCode(obj, index);
        setFactoryName(obj, index);
        setFevesArdoiseesPlateau1(obj, index);
        setFevesArdoiseesPlateau2(obj, index);
        setFevesArdoiseesPlateau3(obj, index);
        setFevesGermeesPlateau1(obj, index);
        setFevesGermeesPlateau2(obj, index);
        setFevesGermeesPlateau3(obj, index);
        setFevesMiteesPlateau1(obj, index);
        setFevesMiteesPlateau2(obj, index);
        setFevesMiteesPlateau3(obj, index);
        setFevesMoisiesPlateau1(obj, index);
        setFevesMoisiesPlateau2(obj, index);
        setFevesMoisiesPlateau3(obj, index);
        setFevesPar100g(obj, index);
        setFevesPlatesPlateau1(obj, index);
        setFevesPlatesPlateau2(obj, index);
        setFevesPlatesPlateau3(obj, index);
        setFevesViolettePlateau1(obj, index);
        setFevesViolettePlateau2(obj, index);
        setFevesViolettePlateau3(obj, index);
        setModificationDate(obj, index);
        setModifiedBy(obj, index);
        setMoyenneTauxHumidite(obj, index);
        setNetWeightOfProductAccepted(obj, index);
        setNombreFeves(obj, index);
        setNumberLading(obj, index);
        setNumberOfBagAllowed(obj, index);
        setNumberSAIGIC(obj, index);
        setPoidsBrisures(obj, index);
        setPoidsDechetsCerise(obj, index);
        setPoidsDechetsCoques(obj, index);
        setPoidsDechetsDemiCerises(obj, index);
        setPoidsDechetsParches(obj, index);
        setPoidsDechetsPeaux(obj, index);
        setPoidsGrainsAcceptablesDemiSombre(obj, index);
        setPoidsGrainsAcceptablesImmature(obj, index);
        setPoidsGrainsAcceptablesIndesirables(obj, index);
        setPoidsGrainsAcceptablesScolytes(obj, index);
        setPoidsGrainsAcceptablesSpongieux(obj, index);
        setPoidsGrainsAcceptablesVert(obj, index);
        setPoidsHorsNormesBrisures(obj, index);
        setPoidsHorsNormesGrainsDemiNoirs(obj, index);
        setPoidsHorsNormesGrainsNoirs(obj, index);
        setPoidsMatieresEtrangeres(obj, index);
        setPoidsSousTotalDechets(obj, index);
        setPoidsSousTotalGrainsAcceptables(obj, index);
        setPoidsSousTotalHorsNormes(obj, index);
        setPourcentageBrisures(obj, index);
        setPourcentageDechetsCerise(obj, index);
        setPourcentageDechetsCoques(obj, index);
        setPourcentageDechetsDemiCerises(obj, index);
        setPourcentageDechetsParches(obj, index);
        setPourcentageDechetsPeaux(obj, index);
        setPourcentageFevesArdoisees(obj, index);
        setPourcentageFevesGermees(obj, index);
        setPourcentageFevesMitees(obj, index);
        setPourcentageFevesMoisies(obj, index);
        setPourcentageFevesPlates(obj, index);
        setPourcentageFevesViolette(obj, index);
        setPourcentageGradeG0(obj, index);
        setPourcentageGradeG1(obj, index);
        setPourcentageGradeG2(obj, index);
        setPourcentageGradeG3(obj, index);
        setPourcentageGradeG4(obj, index);
        setPourcentageGradeHN(obj, index);
        setPourcentageGrainsAcceptablesDemiSombre(obj, index);
        setPourcentageGrainsAcceptablesImmature(obj, index);
        setPourcentageGrainsAcceptablesIndesirables(obj, index);
        setPourcentageGrainsAcceptablesScolytes(obj, index);
        setPourcentageGrainsAcceptablesSpongieux(obj, index);
        setPourcentageGrainsAcceptablesVert(obj, index);
        setPourcentageHorsNormesBrisures(obj, index);
        setPourcentageHorsNormesGrainsDemiNoirs(obj, index);
        setPourcentageHorsNormesGrainsNoirs(obj, index);
        setPourcentageMatieresEtrangeres(obj, index);
        setPourcentageSousTotalDechets(obj, index);
        setPourcentageSousTotalGrainsAcceptables(obj, index);
        setPourcentageSousTotalHorsNormes(obj, index);
        setPourcentageTotalFevesDefectueuses(obj, index);
        setProductType(obj, index);
        setReference(obj, index);
        setSampleCode(obj, index);
        setSommeTotalFevesDefectueuses(obj, index);
        setStartTime(obj, index);
        setStatus(obj, index);
        setSupplierName(obj, index);
        setTauxHumidite1(obj, index);
        setTauxHumidite2(obj, index);
        setTauxHumidite3(obj, index);
        setTotalFevesArdoisees(obj, index);
        setTotalFevesDefectueusesPlateau1(obj, index);
        setTotalFevesDefectueusesPlateau2(obj, index);
        setTotalFevesDefectueusesPlateau3(obj, index);
        setTotalFevesGermees(obj, index);
        setTotalFevesMitees(obj, index);
        setTotalFevesMoisies(obj, index);
        setTotalFevesPlates(obj, index);
        setTotalFevesViolette(obj, index);
        setTotalOfBagPushed(obj, index);
        setTotalOfReportedBags(obj, index);
        setTruckNumber(obj, index);
        return obj;
    }
    
    public void JAnalysisDataOnDemand.setAcceptation(JAnalysis obj, int index) {
        Boolean acceptation = Boolean.TRUE;
        obj.setAcceptation(acceptation);
    }
    
    public void JAnalysisDataOnDemand.setBridge(JAnalysis obj, int index) {
        JBridge bridge = null;
        obj.setBridge(bridge);
    }
    
    public void JAnalysisDataOnDemand.setCalibrageBase(JAnalysis obj, int index) {
        BigDecimal calibrageBase = BigDecimal.valueOf(index);
        obj.setCalibrageBase(calibrageBase);
    }
    
    public void JAnalysisDataOnDemand.setCalibrageTamis10(JAnalysis obj, int index) {
        BigDecimal calibrageTamis10 = BigDecimal.valueOf(index);
        obj.setCalibrageTamis10(calibrageTamis10);
    }
    
    public void JAnalysisDataOnDemand.setCalibrageTamis12(JAnalysis obj, int index) {
        BigDecimal calibrageTamis12 = BigDecimal.valueOf(index);
        obj.setCalibrageTamis12(calibrageTamis12);
    }
    
    public void JAnalysisDataOnDemand.setCalibrageTamis14(JAnalysis obj, int index) {
        BigDecimal calibrageTamis14 = BigDecimal.valueOf(index);
        obj.setCalibrageTamis14(calibrageTamis14);
    }
    
    public void JAnalysisDataOnDemand.setCalibrageTamis16(JAnalysis obj, int index) {
        BigDecimal calibrageTamis16 = BigDecimal.valueOf(index);
        obj.setCalibrageTamis16(calibrageTamis16);
    }
    
    public void JAnalysisDataOnDemand.setCalibrageTamis18(JAnalysis obj, int index) {
        BigDecimal calibrageTamis18 = BigDecimal.valueOf(index);
        obj.setCalibrageTamis18(calibrageTamis18);
    }
    
    public void JAnalysisDataOnDemand.setCertificationTypeOrProject(JAnalysis obj, int index) {
        String certificationTypeOrProject = "certificationTypeOrProject_" + index;
        obj.setCertificationTypeOrProject(certificationTypeOrProject);
    }
    
    public void JAnalysisDataOnDemand.setClassification(JAnalysis obj, int index) {
        String classification = "classification_" + index;
        obj.setClassification(classification);
    }
    
    public void JAnalysisDataOnDemand.setConformity(JAnalysis obj, int index) {
        Boolean conformity = Boolean.TRUE;
        obj.setConformity(conformity);
    }
    
    public void JAnalysisDataOnDemand.setCreatedBy(JAnalysis obj, int index) {
        String createdBy = "createdBy_" + index;
        obj.setCreatedBy(createdBy);
    }
    
    public void JAnalysisDataOnDemand.setCreationDate(JAnalysis obj, int index) {
        Date creationDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreationDate(creationDate);
    }
    
    public void JAnalysisDataOnDemand.setDateOfAnalysis(JAnalysis obj, int index) {
        Date dateOfAnalysis = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setDateOfAnalysis(dateOfAnalysis);
    }
    
    public void JAnalysisDataOnDemand.setDealerName(JAnalysis obj, int index) {
        String dealerName = "dealerName_" + index;
        obj.setDealerName(dealerName);
    }
    
    public void JAnalysisDataOnDemand.setEndTime(JAnalysis obj, int index) {
        Date endTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setEndTime(endTime);
    }
    
    public void JAnalysisDataOnDemand.setExistInBill(JAnalysis obj, int index) {
        Boolean existInBill = false;
        obj.setExistInBill(existInBill);
    }
    
    public void JAnalysisDataOnDemand.setExporterName(JAnalysis obj, int index) {
        String exporterName = "exporterName_" + index;
        obj.setExporterName(exporterName);
    }
    
    public void JAnalysisDataOnDemand.setFactoryCode(JAnalysis obj, int index) {
        String factoryCode = "factoryCode_" + index;
        obj.setFactoryCode(factoryCode);
    }
    
    public void JAnalysisDataOnDemand.setFactoryName(JAnalysis obj, int index) {
        String factoryName = "factoryName_" + index;
        obj.setFactoryName(factoryName);
    }
    
    public void JAnalysisDataOnDemand.setFevesArdoiseesPlateau1(JAnalysis obj, int index) {
        Long fevesArdoiseesPlateau1 = new Integer(index).longValue();
        obj.setFevesArdoiseesPlateau1(fevesArdoiseesPlateau1);
    }
    
    public void JAnalysisDataOnDemand.setFevesArdoiseesPlateau2(JAnalysis obj, int index) {
        Long fevesArdoiseesPlateau2 = new Integer(index).longValue();
        obj.setFevesArdoiseesPlateau2(fevesArdoiseesPlateau2);
    }
    
    public void JAnalysisDataOnDemand.setFevesArdoiseesPlateau3(JAnalysis obj, int index) {
        Long fevesArdoiseesPlateau3 = new Integer(index).longValue();
        obj.setFevesArdoiseesPlateau3(fevesArdoiseesPlateau3);
    }
    
    public void JAnalysisDataOnDemand.setFevesGermeesPlateau1(JAnalysis obj, int index) {
        Long fevesGermeesPlateau1 = new Integer(index).longValue();
        obj.setFevesGermeesPlateau1(fevesGermeesPlateau1);
    }
    
    public void JAnalysisDataOnDemand.setFevesGermeesPlateau2(JAnalysis obj, int index) {
        Long fevesGermeesPlateau2 = new Integer(index).longValue();
        obj.setFevesGermeesPlateau2(fevesGermeesPlateau2);
    }
    
    public void JAnalysisDataOnDemand.setFevesGermeesPlateau3(JAnalysis obj, int index) {
        Long fevesGermeesPlateau3 = new Integer(index).longValue();
        obj.setFevesGermeesPlateau3(fevesGermeesPlateau3);
    }
    
    public void JAnalysisDataOnDemand.setFevesMiteesPlateau1(JAnalysis obj, int index) {
        Long fevesMiteesPlateau1 = new Integer(index).longValue();
        obj.setFevesMiteesPlateau1(fevesMiteesPlateau1);
    }
    
    public void JAnalysisDataOnDemand.setFevesMiteesPlateau2(JAnalysis obj, int index) {
        Long fevesMiteesPlateau2 = new Integer(index).longValue();
        obj.setFevesMiteesPlateau2(fevesMiteesPlateau2);
    }
    
    public void JAnalysisDataOnDemand.setFevesMiteesPlateau3(JAnalysis obj, int index) {
        Long fevesMiteesPlateau3 = new Integer(index).longValue();
        obj.setFevesMiteesPlateau3(fevesMiteesPlateau3);
    }
    
    public void JAnalysisDataOnDemand.setFevesMoisiesPlateau1(JAnalysis obj, int index) {
        Long fevesMoisiesPlateau1 = new Integer(index).longValue();
        obj.setFevesMoisiesPlateau1(fevesMoisiesPlateau1);
    }
    
    public void JAnalysisDataOnDemand.setFevesMoisiesPlateau2(JAnalysis obj, int index) {
        Long fevesMoisiesPlateau2 = new Integer(index).longValue();
        obj.setFevesMoisiesPlateau2(fevesMoisiesPlateau2);
    }
    
    public void JAnalysisDataOnDemand.setFevesMoisiesPlateau3(JAnalysis obj, int index) {
        Long fevesMoisiesPlateau3 = new Integer(index).longValue();
        obj.setFevesMoisiesPlateau3(fevesMoisiesPlateau3);
    }
    
    public void JAnalysisDataOnDemand.setFevesPar100g(JAnalysis obj, int index) {
        BigDecimal fevesPar100g = BigDecimal.valueOf(index);
        obj.setFevesPar100g(fevesPar100g);
    }
    
    public void JAnalysisDataOnDemand.setFevesPlatesPlateau1(JAnalysis obj, int index) {
        Long fevesPlatesPlateau1 = new Integer(index).longValue();
        obj.setFevesPlatesPlateau1(fevesPlatesPlateau1);
    }
    
    public void JAnalysisDataOnDemand.setFevesPlatesPlateau2(JAnalysis obj, int index) {
        Long fevesPlatesPlateau2 = new Integer(index).longValue();
        obj.setFevesPlatesPlateau2(fevesPlatesPlateau2);
    }
    
    public void JAnalysisDataOnDemand.setFevesPlatesPlateau3(JAnalysis obj, int index) {
        Long fevesPlatesPlateau3 = new Integer(index).longValue();
        obj.setFevesPlatesPlateau3(fevesPlatesPlateau3);
    }
    
    public void JAnalysisDataOnDemand.setFevesViolettePlateau1(JAnalysis obj, int index) {
        Long fevesViolettePlateau1 = new Integer(index).longValue();
        obj.setFevesViolettePlateau1(fevesViolettePlateau1);
    }
    
    public void JAnalysisDataOnDemand.setFevesViolettePlateau2(JAnalysis obj, int index) {
        Long fevesViolettePlateau2 = new Integer(index).longValue();
        obj.setFevesViolettePlateau2(fevesViolettePlateau2);
    }
    
    public void JAnalysisDataOnDemand.setFevesViolettePlateau3(JAnalysis obj, int index) {
        Long fevesViolettePlateau3 = new Integer(index).longValue();
        obj.setFevesViolettePlateau3(fevesViolettePlateau3);
    }
    
    public void JAnalysisDataOnDemand.setModificationDate(JAnalysis obj, int index) {
        Date modificationDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setModificationDate(modificationDate);
    }
    
    public void JAnalysisDataOnDemand.setModifiedBy(JAnalysis obj, int index) {
        String modifiedBy = "modifiedBy_" + index;
        obj.setModifiedBy(modifiedBy);
    }
    
    public void JAnalysisDataOnDemand.setMoyenneTauxHumidite(JAnalysis obj, int index) {
        BigDecimal moyenneTauxHumidite = BigDecimal.valueOf(index);
        obj.setMoyenneTauxHumidite(moyenneTauxHumidite);
    }
    
    public void JAnalysisDataOnDemand.setNetWeightOfProductAccepted(JAnalysis obj, int index) {
        BigDecimal netWeightOfProductAccepted = BigDecimal.valueOf(index);
        obj.setNetWeightOfProductAccepted(netWeightOfProductAccepted);
    }
    
    public void JAnalysisDataOnDemand.setNombreFeves(JAnalysis obj, int index) {
        BigDecimal nombreFeves = BigDecimal.valueOf(index);
        obj.setNombreFeves(nombreFeves);
    }
    
    public void JAnalysisDataOnDemand.setNumberLading(JAnalysis obj, int index) {
        String numberLading = "numberLading_" + index;
        obj.setNumberLading(numberLading);
    }
    
    public void JAnalysisDataOnDemand.setNumberOfBagAllowed(JAnalysis obj, int index) {
        Long numberOfBagAllowed = new Integer(index).longValue();
        obj.setNumberOfBagAllowed(numberOfBagAllowed);
    }
    
    public void JAnalysisDataOnDemand.setNumberSAIGIC(JAnalysis obj, int index) {
        String numberSAIGIC = "numberSAIGIC_" + index;
        obj.setNumberSAIGIC(numberSAIGIC);
    }
    
    public void JAnalysisDataOnDemand.setPoidsBrisures(JAnalysis obj, int index) {
        BigDecimal poidsBrisures = BigDecimal.valueOf(index);
        obj.setPoidsBrisures(poidsBrisures);
    }
    
    public void JAnalysisDataOnDemand.setPoidsDechetsCerise(JAnalysis obj, int index) {
        BigDecimal poidsDechetsCerise = BigDecimal.valueOf(index);
        obj.setPoidsDechetsCerise(poidsDechetsCerise);
    }
    
    public void JAnalysisDataOnDemand.setPoidsDechetsCoques(JAnalysis obj, int index) {
        BigDecimal poidsDechetsCoques = BigDecimal.valueOf(index);
        obj.setPoidsDechetsCoques(poidsDechetsCoques);
    }
    
    public void JAnalysisDataOnDemand.setPoidsDechetsDemiCerises(JAnalysis obj, int index) {
        BigDecimal poidsDechetsDemiCerises = BigDecimal.valueOf(index);
        obj.setPoidsDechetsDemiCerises(poidsDechetsDemiCerises);
    }
    
    public void JAnalysisDataOnDemand.setPoidsDechetsParches(JAnalysis obj, int index) {
        BigDecimal poidsDechetsParches = BigDecimal.valueOf(index);
        obj.setPoidsDechetsParches(poidsDechetsParches);
    }
    
    public void JAnalysisDataOnDemand.setPoidsDechetsPeaux(JAnalysis obj, int index) {
        BigDecimal poidsDechetsPeaux = BigDecimal.valueOf(index);
        obj.setPoidsDechetsPeaux(poidsDechetsPeaux);
    }
    
    public void JAnalysisDataOnDemand.setPoidsGrainsAcceptablesDemiSombre(JAnalysis obj, int index) {
        BigDecimal poidsGrainsAcceptablesDemiSombre = BigDecimal.valueOf(index);
        obj.setPoidsGrainsAcceptablesDemiSombre(poidsGrainsAcceptablesDemiSombre);
    }
    
    public void JAnalysisDataOnDemand.setPoidsGrainsAcceptablesImmature(JAnalysis obj, int index) {
        BigDecimal poidsGrainsAcceptablesImmature = BigDecimal.valueOf(index);
        obj.setPoidsGrainsAcceptablesImmature(poidsGrainsAcceptablesImmature);
    }
    
    public void JAnalysisDataOnDemand.setPoidsGrainsAcceptablesIndesirables(JAnalysis obj, int index) {
        BigDecimal poidsGrainsAcceptablesIndesirables = BigDecimal.valueOf(index);
        obj.setPoidsGrainsAcceptablesIndesirables(poidsGrainsAcceptablesIndesirables);
    }
    
    public void JAnalysisDataOnDemand.setPoidsGrainsAcceptablesScolytes(JAnalysis obj, int index) {
        BigDecimal poidsGrainsAcceptablesScolytes = BigDecimal.valueOf(index);
        obj.setPoidsGrainsAcceptablesScolytes(poidsGrainsAcceptablesScolytes);
    }
    
    public void JAnalysisDataOnDemand.setPoidsGrainsAcceptablesSpongieux(JAnalysis obj, int index) {
        BigDecimal poidsGrainsAcceptablesSpongieux = BigDecimal.valueOf(index);
        obj.setPoidsGrainsAcceptablesSpongieux(poidsGrainsAcceptablesSpongieux);
    }
    
    public void JAnalysisDataOnDemand.setPoidsGrainsAcceptablesVert(JAnalysis obj, int index) {
        BigDecimal poidsGrainsAcceptablesVert = BigDecimal.valueOf(index);
        obj.setPoidsGrainsAcceptablesVert(poidsGrainsAcceptablesVert);
    }
    
    public void JAnalysisDataOnDemand.setPoidsHorsNormesBrisures(JAnalysis obj, int index) {
        BigDecimal poidsHorsNormesBrisures = BigDecimal.valueOf(index);
        obj.setPoidsHorsNormesBrisures(poidsHorsNormesBrisures);
    }
    
    public void JAnalysisDataOnDemand.setPoidsHorsNormesGrainsDemiNoirs(JAnalysis obj, int index) {
        BigDecimal poidsHorsNormesGrainsDemiNoirs = BigDecimal.valueOf(index);
        obj.setPoidsHorsNormesGrainsDemiNoirs(poidsHorsNormesGrainsDemiNoirs);
    }
    
    public void JAnalysisDataOnDemand.setPoidsHorsNormesGrainsNoirs(JAnalysis obj, int index) {
        BigDecimal poidsHorsNormesGrainsNoirs = BigDecimal.valueOf(index);
        obj.setPoidsHorsNormesGrainsNoirs(poidsHorsNormesGrainsNoirs);
    }
    
    public void JAnalysisDataOnDemand.setPoidsMatieresEtrangeres(JAnalysis obj, int index) {
        BigDecimal poidsMatieresEtrangeres = BigDecimal.valueOf(index);
        obj.setPoidsMatieresEtrangeres(poidsMatieresEtrangeres);
    }
    
    public void JAnalysisDataOnDemand.setPoidsSousTotalDechets(JAnalysis obj, int index) {
        BigDecimal poidsSousTotalDechets = BigDecimal.valueOf(index);
        obj.setPoidsSousTotalDechets(poidsSousTotalDechets);
    }
    
    public void JAnalysisDataOnDemand.setPoidsSousTotalGrainsAcceptables(JAnalysis obj, int index) {
        BigDecimal poidsSousTotalGrainsAcceptables = BigDecimal.valueOf(index);
        obj.setPoidsSousTotalGrainsAcceptables(poidsSousTotalGrainsAcceptables);
    }
    
    public void JAnalysisDataOnDemand.setPoidsSousTotalHorsNormes(JAnalysis obj, int index) {
        BigDecimal poidsSousTotalHorsNormes = BigDecimal.valueOf(index);
        obj.setPoidsSousTotalHorsNormes(poidsSousTotalHorsNormes);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageBrisures(JAnalysis obj, int index) {
        BigDecimal pourcentageBrisures = BigDecimal.valueOf(index);
        obj.setPourcentageBrisures(pourcentageBrisures);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageDechetsCerise(JAnalysis obj, int index) {
        BigDecimal pourcentageDechetsCerise = BigDecimal.valueOf(index);
        obj.setPourcentageDechetsCerise(pourcentageDechetsCerise);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageDechetsCoques(JAnalysis obj, int index) {
        BigDecimal pourcentageDechetsCoques = BigDecimal.valueOf(index);
        obj.setPourcentageDechetsCoques(pourcentageDechetsCoques);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageDechetsDemiCerises(JAnalysis obj, int index) {
        BigDecimal pourcentageDechetsDemiCerises = BigDecimal.valueOf(index);
        obj.setPourcentageDechetsDemiCerises(pourcentageDechetsDemiCerises);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageDechetsParches(JAnalysis obj, int index) {
        BigDecimal pourcentageDechetsParches = BigDecimal.valueOf(index);
        obj.setPourcentageDechetsParches(pourcentageDechetsParches);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageDechetsPeaux(JAnalysis obj, int index) {
        BigDecimal pourcentageDechetsPeaux = BigDecimal.valueOf(index);
        obj.setPourcentageDechetsPeaux(pourcentageDechetsPeaux);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageFevesArdoisees(JAnalysis obj, int index) {
        BigDecimal pourcentageFevesArdoisees = BigDecimal.valueOf(index);
        obj.setPourcentageFevesArdoisees(pourcentageFevesArdoisees);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageFevesGermees(JAnalysis obj, int index) {
        BigDecimal pourcentageFevesGermees = BigDecimal.valueOf(index);
        obj.setPourcentageFevesGermees(pourcentageFevesGermees);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageFevesMitees(JAnalysis obj, int index) {
        BigDecimal pourcentageFevesMitees = BigDecimal.valueOf(index);
        obj.setPourcentageFevesMitees(pourcentageFevesMitees);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageFevesMoisies(JAnalysis obj, int index) {
        BigDecimal pourcentageFevesMoisies = BigDecimal.valueOf(index);
        obj.setPourcentageFevesMoisies(pourcentageFevesMoisies);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageFevesPlates(JAnalysis obj, int index) {
        BigDecimal pourcentageFevesPlates = BigDecimal.valueOf(index);
        obj.setPourcentageFevesPlates(pourcentageFevesPlates);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageFevesViolette(JAnalysis obj, int index) {
        BigDecimal pourcentageFevesViolette = BigDecimal.valueOf(index);
        obj.setPourcentageFevesViolette(pourcentageFevesViolette);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageGradeG0(JAnalysis obj, int index) {
        BigDecimal pourcentageGradeG0 = BigDecimal.valueOf(index);
        obj.setPourcentageGradeG0(pourcentageGradeG0);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageGradeG1(JAnalysis obj, int index) {
        BigDecimal pourcentageGradeG1 = BigDecimal.valueOf(index);
        obj.setPourcentageGradeG1(pourcentageGradeG1);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageGradeG2(JAnalysis obj, int index) {
        BigDecimal pourcentageGradeG2 = BigDecimal.valueOf(index);
        obj.setPourcentageGradeG2(pourcentageGradeG2);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageGradeG3(JAnalysis obj, int index) {
        BigDecimal pourcentageGradeG3 = BigDecimal.valueOf(index);
        obj.setPourcentageGradeG3(pourcentageGradeG3);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageGradeG4(JAnalysis obj, int index) {
        BigDecimal pourcentageGradeG4 = BigDecimal.valueOf(index);
        obj.setPourcentageGradeG4(pourcentageGradeG4);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageGradeHN(JAnalysis obj, int index) {
        BigDecimal pourcentageGradeHN = BigDecimal.valueOf(index);
        obj.setPourcentageGradeHN(pourcentageGradeHN);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageGrainsAcceptablesDemiSombre(JAnalysis obj, int index) {
        BigDecimal pourcentageGrainsAcceptablesDemiSombre = BigDecimal.valueOf(index);
        obj.setPourcentageGrainsAcceptablesDemiSombre(pourcentageGrainsAcceptablesDemiSombre);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageGrainsAcceptablesImmature(JAnalysis obj, int index) {
        BigDecimal pourcentageGrainsAcceptablesImmature = BigDecimal.valueOf(index);
        obj.setPourcentageGrainsAcceptablesImmature(pourcentageGrainsAcceptablesImmature);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageGrainsAcceptablesIndesirables(JAnalysis obj, int index) {
        BigDecimal pourcentageGrainsAcceptablesIndesirables = BigDecimal.valueOf(index);
        obj.setPourcentageGrainsAcceptablesIndesirables(pourcentageGrainsAcceptablesIndesirables);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageGrainsAcceptablesScolytes(JAnalysis obj, int index) {
        BigDecimal pourcentageGrainsAcceptablesScolytes = BigDecimal.valueOf(index);
        obj.setPourcentageGrainsAcceptablesScolytes(pourcentageGrainsAcceptablesScolytes);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageGrainsAcceptablesSpongieux(JAnalysis obj, int index) {
        BigDecimal pourcentageGrainsAcceptablesSpongieux = BigDecimal.valueOf(index);
        obj.setPourcentageGrainsAcceptablesSpongieux(pourcentageGrainsAcceptablesSpongieux);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageGrainsAcceptablesVert(JAnalysis obj, int index) {
        BigDecimal pourcentageGrainsAcceptablesVert = BigDecimal.valueOf(index);
        obj.setPourcentageGrainsAcceptablesVert(pourcentageGrainsAcceptablesVert);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageHorsNormesBrisures(JAnalysis obj, int index) {
        BigDecimal pourcentageHorsNormesBrisures = BigDecimal.valueOf(index);
        obj.setPourcentageHorsNormesBrisures(pourcentageHorsNormesBrisures);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageHorsNormesGrainsDemiNoirs(JAnalysis obj, int index) {
        BigDecimal pourcentageHorsNormesGrainsDemiNoirs = BigDecimal.valueOf(index);
        obj.setPourcentageHorsNormesGrainsDemiNoirs(pourcentageHorsNormesGrainsDemiNoirs);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageHorsNormesGrainsNoirs(JAnalysis obj, int index) {
        BigDecimal pourcentageHorsNormesGrainsNoirs = BigDecimal.valueOf(index);
        obj.setPourcentageHorsNormesGrainsNoirs(pourcentageHorsNormesGrainsNoirs);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageMatieresEtrangeres(JAnalysis obj, int index) {
        BigDecimal pourcentageMatieresEtrangeres = BigDecimal.valueOf(index);
        obj.setPourcentageMatieresEtrangeres(pourcentageMatieresEtrangeres);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageSousTotalDechets(JAnalysis obj, int index) {
        BigDecimal pourcentageSousTotalDechets = BigDecimal.valueOf(index);
        obj.setPourcentageSousTotalDechets(pourcentageSousTotalDechets);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageSousTotalGrainsAcceptables(JAnalysis obj, int index) {
        BigDecimal pourcentageSousTotalGrainsAcceptables = BigDecimal.valueOf(index);
        obj.setPourcentageSousTotalGrainsAcceptables(pourcentageSousTotalGrainsAcceptables);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageSousTotalHorsNormes(JAnalysis obj, int index) {
        BigDecimal pourcentageSousTotalHorsNormes = BigDecimal.valueOf(index);
        obj.setPourcentageSousTotalHorsNormes(pourcentageSousTotalHorsNormes);
    }
    
    public void JAnalysisDataOnDemand.setPourcentageTotalFevesDefectueuses(JAnalysis obj, int index) {
        BigDecimal pourcentageTotalFevesDefectueuses = BigDecimal.valueOf(index);
        obj.setPourcentageTotalFevesDefectueuses(pourcentageTotalFevesDefectueuses);
    }
    
    public void JAnalysisDataOnDemand.setProductType(JAnalysis obj, int index) {
        String productType = "productType_" + index;
        obj.setProductType(productType);
    }
    
    public void JAnalysisDataOnDemand.setReference(JAnalysis obj, int index) {
        String reference = "reference_" + index;
        obj.setReference(reference);
    }
    
    public void JAnalysisDataOnDemand.setSampleCode(JAnalysis obj, int index) {
        String sampleCode = "sampleCode_" + index;
        obj.setSampleCode(sampleCode);
    }
    
    public void JAnalysisDataOnDemand.setSommeTotalFevesDefectueuses(JAnalysis obj, int index) {
        Long sommeTotalFevesDefectueuses = new Integer(index).longValue();
        obj.setSommeTotalFevesDefectueuses(sommeTotalFevesDefectueuses);
    }
    
    public void JAnalysisDataOnDemand.setStartTime(JAnalysis obj, int index) {
        Date startTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setStartTime(startTime);
    }
    
    public void JAnalysisDataOnDemand.setStatus(JAnalysis obj, int index) {
        Boolean status = Boolean.TRUE;
        obj.setStatus(status);
    }
    
    public void JAnalysisDataOnDemand.setSupplierName(JAnalysis obj, int index) {
        String supplierName = "supplierName_" + index;
        obj.setSupplierName(supplierName);
    }
    
    public void JAnalysisDataOnDemand.setTauxHumidite1(JAnalysis obj, int index) {
        BigDecimal tauxHumidite1 = BigDecimal.valueOf(index);
        obj.setTauxHumidite1(tauxHumidite1);
    }
    
    public void JAnalysisDataOnDemand.setTauxHumidite2(JAnalysis obj, int index) {
        BigDecimal tauxHumidite2 = BigDecimal.valueOf(index);
        obj.setTauxHumidite2(tauxHumidite2);
    }
    
    public void JAnalysisDataOnDemand.setTauxHumidite3(JAnalysis obj, int index) {
        BigDecimal tauxHumidite3 = BigDecimal.valueOf(index);
        obj.setTauxHumidite3(tauxHumidite3);
    }
    
    public void JAnalysisDataOnDemand.setTotalFevesArdoisees(JAnalysis obj, int index) {
        Long totalFevesArdoisees = new Integer(index).longValue();
        obj.setTotalFevesArdoisees(totalFevesArdoisees);
    }
    
    public void JAnalysisDataOnDemand.setTotalFevesDefectueusesPlateau1(JAnalysis obj, int index) {
        Long totalFevesDefectueusesPlateau1 = new Integer(index).longValue();
        obj.setTotalFevesDefectueusesPlateau1(totalFevesDefectueusesPlateau1);
    }
    
    public void JAnalysisDataOnDemand.setTotalFevesDefectueusesPlateau2(JAnalysis obj, int index) {
        Long totalFevesDefectueusesPlateau2 = new Integer(index).longValue();
        obj.setTotalFevesDefectueusesPlateau2(totalFevesDefectueusesPlateau2);
    }
    
    public void JAnalysisDataOnDemand.setTotalFevesDefectueusesPlateau3(JAnalysis obj, int index) {
        Long totalFevesDefectueusesPlateau3 = new Integer(index).longValue();
        obj.setTotalFevesDefectueusesPlateau3(totalFevesDefectueusesPlateau3);
    }
    
    public void JAnalysisDataOnDemand.setTotalFevesGermees(JAnalysis obj, int index) {
        Long totalFevesGermees = new Integer(index).longValue();
        obj.setTotalFevesGermees(totalFevesGermees);
    }
    
    public void JAnalysisDataOnDemand.setTotalFevesMitees(JAnalysis obj, int index) {
        Long totalFevesMitees = new Integer(index).longValue();
        obj.setTotalFevesMitees(totalFevesMitees);
    }
    
    public void JAnalysisDataOnDemand.setTotalFevesMoisies(JAnalysis obj, int index) {
        Long totalFevesMoisies = new Integer(index).longValue();
        obj.setTotalFevesMoisies(totalFevesMoisies);
    }
    
    public void JAnalysisDataOnDemand.setTotalFevesPlates(JAnalysis obj, int index) {
        Long totalFevesPlates = new Integer(index).longValue();
        obj.setTotalFevesPlates(totalFevesPlates);
    }
    
    public void JAnalysisDataOnDemand.setTotalFevesViolette(JAnalysis obj, int index) {
        Long totalFevesViolette = new Integer(index).longValue();
        obj.setTotalFevesViolette(totalFevesViolette);
    }
    
    public void JAnalysisDataOnDemand.setTotalOfBagPushed(JAnalysis obj, int index) {
        Long totalOfBagPushed = new Integer(index).longValue();
        obj.setTotalOfBagPushed(totalOfBagPushed);
    }
    
    public void JAnalysisDataOnDemand.setTotalOfReportedBags(JAnalysis obj, int index) {
        Long totalOfReportedBags = new Integer(index).longValue();
        obj.setTotalOfReportedBags(totalOfReportedBags);
    }
    
    public void JAnalysisDataOnDemand.setTruckNumber(JAnalysis obj, int index) {
        String truckNumber = "truckNumber_" + index;
        obj.setTruckNumber(truckNumber);
    }
    
    public JAnalysis JAnalysisDataOnDemand.getSpecificJAnalysis(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        JAnalysis obj = data.get(index);
        Long id = obj.getId();
        return JAnalysis.findJAnalysis(id);
    }
    
    public JAnalysis JAnalysisDataOnDemand.getRandomJAnalysis() {
        init();
        JAnalysis obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return JAnalysis.findJAnalysis(id);
    }
    
    public boolean JAnalysisDataOnDemand.modifyJAnalysis(JAnalysis obj) {
        return false;
    }
    
    public void JAnalysisDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = JAnalysis.findJAnalysisEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'JAnalysis' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<JAnalysis>();
        for (int i = 0; i < 10; i++) {
            JAnalysis obj = getNewTransientJAnalysis(i);
            try {
                obj.persist();
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
    
}
