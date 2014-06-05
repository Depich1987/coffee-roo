// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.j1987.coffeeroo.domain;

import com.j1987.coffeeroo.domain.JCoffeeAnalysis;
import com.j1987.coffeeroo.domain.JCoffeeAnalysisDataOnDemand;
import com.j1987.coffeeroo.domain.JDealerDataOnDemand;
import com.j1987.coffeeroo.domain.JExporterDataOnDemand;
import com.j1987.coffeeroo.domain.JFactoryDataOnDemand;
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

privileged aspect JCoffeeAnalysisDataOnDemand_Roo_DataOnDemand {
    
    declare @type: JCoffeeAnalysisDataOnDemand: @Component;
    
    private Random JCoffeeAnalysisDataOnDemand.rnd = new SecureRandom();
    
    private List<JCoffeeAnalysis> JCoffeeAnalysisDataOnDemand.data;
    
    @Autowired
    JDealerDataOnDemand JCoffeeAnalysisDataOnDemand.jDealerDataOnDemand;
    
    @Autowired
    JExporterDataOnDemand JCoffeeAnalysisDataOnDemand.jExporterDataOnDemand;
    
    @Autowired
    JFactoryDataOnDemand JCoffeeAnalysisDataOnDemand.jFactoryDataOnDemand;
    
    @Autowired
    JLocalizationDataOnDemand JCoffeeAnalysisDataOnDemand.jLocalizationDataOnDemand;
    
    @Autowired
    JSupplierDataOnDemand JCoffeeAnalysisDataOnDemand.jSupplierDataOnDemand;
    
    @Autowired
    JTourDataOnDemand JCoffeeAnalysisDataOnDemand.jTourDataOnDemand;
    
    public JCoffeeAnalysis JCoffeeAnalysisDataOnDemand.getNewTransientJCoffeeAnalysis(int index) {
        JCoffeeAnalysis obj = new JCoffeeAnalysis();
        setAcceptation(obj, index);
        setAcceptationNon(obj, index);
        setAcceptationOui(obj, index);
        setCalibrageBase(obj, index);
        setCalibrageTamis10(obj, index);
        setCalibrageTamis12(obj, index);
        setCalibrageTamis14(obj, index);
        setCalibrageTamis16(obj, index);
        setCalibrageTamis18(obj, index);
        setConfirmation(obj, index);
        setConfirmiteNon(obj, index);
        setConfirmiteOui(obj, index);
        setCreatedBy(obj, index);
        setCreationDate(obj, index);
        setDateOfAnalysis(obj, index);
        setEndTime(obj, index);
        setModificationDate(obj, index);
        setModifiedBy(obj, index);
        setNetWeightOfProductAccepted(obj, index);
        setNumberLading(obj, index);
        setNumberOfBagAllowed(obj, index);
        setNumberSAIGIC(obj, index);
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
        setPoidsSousTotalHorsNormes(obj, index);
        setPourcentageDechetsCerise(obj, index);
        setPourcentageDechetsCoques(obj, index);
        setPourcentageDechetsDemiCerises(obj, index);
        setPourcentageDechetsParches(obj, index);
        setPourcentageDechetsPeaux(obj, index);
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
        setPourcentageSousTotalHorsNormes(obj, index);
        setReference(obj, index);
        setSampleCode(obj, index);
        setStartTime(obj, index);
        setStatus(obj, index);
        setTotalOfBagPushed(obj, index);
        setTotalOfReportedBags(obj, index);
        setTruckNumber(obj, index);
        return obj;
    }
    
    public void JCoffeeAnalysisDataOnDemand.setAcceptation(JCoffeeAnalysis obj, int index) {
        Boolean acceptation = Boolean.TRUE;
        obj.setAcceptation(acceptation);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setAcceptationNon(JCoffeeAnalysis obj, int index) {
        String acceptationNon = "acceptationNon_" + index;
        obj.setAcceptationNon(acceptationNon);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setAcceptationOui(JCoffeeAnalysis obj, int index) {
        String acceptationOui = "acceptationOui_" + index;
        obj.setAcceptationOui(acceptationOui);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setCalibrageBase(JCoffeeAnalysis obj, int index) {
        BigDecimal calibrageBase = BigDecimal.valueOf(index);
        obj.setCalibrageBase(calibrageBase);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setCalibrageTamis10(JCoffeeAnalysis obj, int index) {
        BigDecimal calibrageTamis10 = BigDecimal.valueOf(index);
        obj.setCalibrageTamis10(calibrageTamis10);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setCalibrageTamis12(JCoffeeAnalysis obj, int index) {
        BigDecimal calibrageTamis12 = BigDecimal.valueOf(index);
        obj.setCalibrageTamis12(calibrageTamis12);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setCalibrageTamis14(JCoffeeAnalysis obj, int index) {
        BigDecimal calibrageTamis14 = BigDecimal.valueOf(index);
        obj.setCalibrageTamis14(calibrageTamis14);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setCalibrageTamis16(JCoffeeAnalysis obj, int index) {
        BigDecimal calibrageTamis16 = BigDecimal.valueOf(index);
        obj.setCalibrageTamis16(calibrageTamis16);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setCalibrageTamis18(JCoffeeAnalysis obj, int index) {
        BigDecimal calibrageTamis18 = BigDecimal.valueOf(index);
        obj.setCalibrageTamis18(calibrageTamis18);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setConfirmation(JCoffeeAnalysis obj, int index) {
        Boolean confirmation = Boolean.TRUE;
        obj.setConfirmation(confirmation);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setConfirmiteNon(JCoffeeAnalysis obj, int index) {
        String confirmiteNon = "confirmiteNon_" + index;
        obj.setConfirmiteNon(confirmiteNon);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setConfirmiteOui(JCoffeeAnalysis obj, int index) {
        String confirmiteOui = "confirmiteOui_" + index;
        obj.setConfirmiteOui(confirmiteOui);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setCreatedBy(JCoffeeAnalysis obj, int index) {
        String createdBy = "createdBy_" + index;
        obj.setCreatedBy(createdBy);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setCreationDate(JCoffeeAnalysis obj, int index) {
        Date creationDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreationDate(creationDate);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setDateOfAnalysis(JCoffeeAnalysis obj, int index) {
        Date dateOfAnalysis = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setDateOfAnalysis(dateOfAnalysis);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setEndTime(JCoffeeAnalysis obj, int index) {
        Date endTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setEndTime(endTime);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setModificationDate(JCoffeeAnalysis obj, int index) {
        Date modificationDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setModificationDate(modificationDate);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setModifiedBy(JCoffeeAnalysis obj, int index) {
        String modifiedBy = "modifiedBy_" + index;
        obj.setModifiedBy(modifiedBy);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setNetWeightOfProductAccepted(JCoffeeAnalysis obj, int index) {
        BigDecimal netWeightOfProductAccepted = BigDecimal.valueOf(index);
        obj.setNetWeightOfProductAccepted(netWeightOfProductAccepted);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setNumberLading(JCoffeeAnalysis obj, int index) {
        String numberLading = "numberLading_" + index;
        obj.setNumberLading(numberLading);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setNumberOfBagAllowed(JCoffeeAnalysis obj, int index) {
        Long numberOfBagAllowed = new Integer(index).longValue();
        obj.setNumberOfBagAllowed(numberOfBagAllowed);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setNumberSAIGIC(JCoffeeAnalysis obj, int index) {
        String numberSAIGIC = "numberSAIGIC_" + index;
        obj.setNumberSAIGIC(numberSAIGIC);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPoidsDechetsCerise(JCoffeeAnalysis obj, int index) {
        BigDecimal poidsDechetsCerise = BigDecimal.valueOf(index);
        obj.setPoidsDechetsCerise(poidsDechetsCerise);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPoidsDechetsCoques(JCoffeeAnalysis obj, int index) {
        BigDecimal poidsDechetsCoques = BigDecimal.valueOf(index);
        obj.setPoidsDechetsCoques(poidsDechetsCoques);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPoidsDechetsDemiCerises(JCoffeeAnalysis obj, int index) {
        BigDecimal poidsDechetsDemiCerises = BigDecimal.valueOf(index);
        obj.setPoidsDechetsDemiCerises(poidsDechetsDemiCerises);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPoidsDechetsParches(JCoffeeAnalysis obj, int index) {
        BigDecimal poidsDechetsParches = BigDecimal.valueOf(index);
        obj.setPoidsDechetsParches(poidsDechetsParches);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPoidsDechetsPeaux(JCoffeeAnalysis obj, int index) {
        BigDecimal poidsDechetsPeaux = BigDecimal.valueOf(index);
        obj.setPoidsDechetsPeaux(poidsDechetsPeaux);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPoidsGrainsAcceptablesDemiSombre(JCoffeeAnalysis obj, int index) {
        BigDecimal poidsGrainsAcceptablesDemiSombre = BigDecimal.valueOf(index);
        obj.setPoidsGrainsAcceptablesDemiSombre(poidsGrainsAcceptablesDemiSombre);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPoidsGrainsAcceptablesImmature(JCoffeeAnalysis obj, int index) {
        BigDecimal poidsGrainsAcceptablesImmature = BigDecimal.valueOf(index);
        obj.setPoidsGrainsAcceptablesImmature(poidsGrainsAcceptablesImmature);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPoidsGrainsAcceptablesIndesirables(JCoffeeAnalysis obj, int index) {
        BigDecimal poidsGrainsAcceptablesIndesirables = BigDecimal.valueOf(index);
        obj.setPoidsGrainsAcceptablesIndesirables(poidsGrainsAcceptablesIndesirables);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPoidsGrainsAcceptablesScolytes(JCoffeeAnalysis obj, int index) {
        BigDecimal poidsGrainsAcceptablesScolytes = BigDecimal.valueOf(index);
        obj.setPoidsGrainsAcceptablesScolytes(poidsGrainsAcceptablesScolytes);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPoidsGrainsAcceptablesSpongieux(JCoffeeAnalysis obj, int index) {
        BigDecimal poidsGrainsAcceptablesSpongieux = BigDecimal.valueOf(index);
        obj.setPoidsGrainsAcceptablesSpongieux(poidsGrainsAcceptablesSpongieux);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPoidsGrainsAcceptablesVert(JCoffeeAnalysis obj, int index) {
        BigDecimal poidsGrainsAcceptablesVert = BigDecimal.valueOf(index);
        obj.setPoidsGrainsAcceptablesVert(poidsGrainsAcceptablesVert);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPoidsHorsNormesBrisures(JCoffeeAnalysis obj, int index) {
        BigDecimal poidsHorsNormesBrisures = BigDecimal.valueOf(index);
        obj.setPoidsHorsNormesBrisures(poidsHorsNormesBrisures);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPoidsHorsNormesGrainsDemiNoirs(JCoffeeAnalysis obj, int index) {
        BigDecimal poidsHorsNormesGrainsDemiNoirs = BigDecimal.valueOf(index);
        obj.setPoidsHorsNormesGrainsDemiNoirs(poidsHorsNormesGrainsDemiNoirs);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPoidsHorsNormesGrainsNoirs(JCoffeeAnalysis obj, int index) {
        BigDecimal poidsHorsNormesGrainsNoirs = BigDecimal.valueOf(index);
        obj.setPoidsHorsNormesGrainsNoirs(poidsHorsNormesGrainsNoirs);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPoidsMatieresEtrangeres(JCoffeeAnalysis obj, int index) {
        BigDecimal poidsMatieresEtrangeres = BigDecimal.valueOf(index);
        obj.setPoidsMatieresEtrangeres(poidsMatieresEtrangeres);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPoidsSousTotalDechets(JCoffeeAnalysis obj, int index) {
        BigDecimal poidsSousTotalDechets = BigDecimal.valueOf(index);
        obj.setPoidsSousTotalDechets(poidsSousTotalDechets);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPoidsSousTotalHorsNormes(JCoffeeAnalysis obj, int index) {
        BigDecimal poidsSousTotalHorsNormes = BigDecimal.valueOf(index);
        obj.setPoidsSousTotalHorsNormes(poidsSousTotalHorsNormes);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPourcentageDechetsCerise(JCoffeeAnalysis obj, int index) {
        BigDecimal pourcentageDechetsCerise = BigDecimal.valueOf(index);
        obj.setPourcentageDechetsCerise(pourcentageDechetsCerise);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPourcentageDechetsCoques(JCoffeeAnalysis obj, int index) {
        BigDecimal pourcentageDechetsCoques = BigDecimal.valueOf(index);
        obj.setPourcentageDechetsCoques(pourcentageDechetsCoques);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPourcentageDechetsDemiCerises(JCoffeeAnalysis obj, int index) {
        BigDecimal pourcentageDechetsDemiCerises = BigDecimal.valueOf(index);
        obj.setPourcentageDechetsDemiCerises(pourcentageDechetsDemiCerises);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPourcentageDechetsParches(JCoffeeAnalysis obj, int index) {
        BigDecimal pourcentageDechetsParches = BigDecimal.valueOf(index);
        obj.setPourcentageDechetsParches(pourcentageDechetsParches);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPourcentageDechetsPeaux(JCoffeeAnalysis obj, int index) {
        BigDecimal pourcentageDechetsPeaux = BigDecimal.valueOf(index);
        obj.setPourcentageDechetsPeaux(pourcentageDechetsPeaux);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPourcentageGradeG0(JCoffeeAnalysis obj, int index) {
        BigDecimal pourcentageGradeG0 = BigDecimal.valueOf(index);
        obj.setPourcentageGradeG0(pourcentageGradeG0);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPourcentageGradeG1(JCoffeeAnalysis obj, int index) {
        BigDecimal pourcentageGradeG1 = BigDecimal.valueOf(index);
        obj.setPourcentageGradeG1(pourcentageGradeG1);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPourcentageGradeG2(JCoffeeAnalysis obj, int index) {
        BigDecimal pourcentageGradeG2 = BigDecimal.valueOf(index);
        obj.setPourcentageGradeG2(pourcentageGradeG2);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPourcentageGradeG3(JCoffeeAnalysis obj, int index) {
        BigDecimal pourcentageGradeG3 = BigDecimal.valueOf(index);
        obj.setPourcentageGradeG3(pourcentageGradeG3);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPourcentageGradeG4(JCoffeeAnalysis obj, int index) {
        BigDecimal pourcentageGradeG4 = BigDecimal.valueOf(index);
        obj.setPourcentageGradeG4(pourcentageGradeG4);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPourcentageGradeHN(JCoffeeAnalysis obj, int index) {
        BigDecimal pourcentageGradeHN = BigDecimal.valueOf(index);
        obj.setPourcentageGradeHN(pourcentageGradeHN);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPourcentageGrainsAcceptablesDemiSombre(JCoffeeAnalysis obj, int index) {
        BigDecimal pourcentageGrainsAcceptablesDemiSombre = BigDecimal.valueOf(index);
        obj.setPourcentageGrainsAcceptablesDemiSombre(pourcentageGrainsAcceptablesDemiSombre);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPourcentageGrainsAcceptablesImmature(JCoffeeAnalysis obj, int index) {
        BigDecimal pourcentageGrainsAcceptablesImmature = BigDecimal.valueOf(index);
        obj.setPourcentageGrainsAcceptablesImmature(pourcentageGrainsAcceptablesImmature);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPourcentageGrainsAcceptablesIndesirables(JCoffeeAnalysis obj, int index) {
        BigDecimal pourcentageGrainsAcceptablesIndesirables = BigDecimal.valueOf(index);
        obj.setPourcentageGrainsAcceptablesIndesirables(pourcentageGrainsAcceptablesIndesirables);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPourcentageGrainsAcceptablesScolytes(JCoffeeAnalysis obj, int index) {
        BigDecimal pourcentageGrainsAcceptablesScolytes = BigDecimal.valueOf(index);
        obj.setPourcentageGrainsAcceptablesScolytes(pourcentageGrainsAcceptablesScolytes);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPourcentageGrainsAcceptablesSpongieux(JCoffeeAnalysis obj, int index) {
        BigDecimal pourcentageGrainsAcceptablesSpongieux = BigDecimal.valueOf(index);
        obj.setPourcentageGrainsAcceptablesSpongieux(pourcentageGrainsAcceptablesSpongieux);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPourcentageGrainsAcceptablesVert(JCoffeeAnalysis obj, int index) {
        BigDecimal pourcentageGrainsAcceptablesVert = BigDecimal.valueOf(index);
        obj.setPourcentageGrainsAcceptablesVert(pourcentageGrainsAcceptablesVert);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPourcentageHorsNormesBrisures(JCoffeeAnalysis obj, int index) {
        BigDecimal pourcentageHorsNormesBrisures = BigDecimal.valueOf(index);
        obj.setPourcentageHorsNormesBrisures(pourcentageHorsNormesBrisures);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPourcentageHorsNormesGrainsDemiNoirs(JCoffeeAnalysis obj, int index) {
        BigDecimal pourcentageHorsNormesGrainsDemiNoirs = BigDecimal.valueOf(index);
        obj.setPourcentageHorsNormesGrainsDemiNoirs(pourcentageHorsNormesGrainsDemiNoirs);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPourcentageHorsNormesGrainsNoirs(JCoffeeAnalysis obj, int index) {
        BigDecimal pourcentageHorsNormesGrainsNoirs = BigDecimal.valueOf(index);
        obj.setPourcentageHorsNormesGrainsNoirs(pourcentageHorsNormesGrainsNoirs);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPourcentageMatieresEtrangeres(JCoffeeAnalysis obj, int index) {
        BigDecimal pourcentageMatieresEtrangeres = BigDecimal.valueOf(index);
        obj.setPourcentageMatieresEtrangeres(pourcentageMatieresEtrangeres);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPourcentageSousTotalDechets(JCoffeeAnalysis obj, int index) {
        BigDecimal pourcentageSousTotalDechets = BigDecimal.valueOf(index);
        obj.setPourcentageSousTotalDechets(pourcentageSousTotalDechets);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setPourcentageSousTotalHorsNormes(JCoffeeAnalysis obj, int index) {
        BigDecimal pourcentageSousTotalHorsNormes = BigDecimal.valueOf(index);
        obj.setPourcentageSousTotalHorsNormes(pourcentageSousTotalHorsNormes);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setReference(JCoffeeAnalysis obj, int index) {
        String reference = "reference_" + index;
        obj.setReference(reference);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setSampleCode(JCoffeeAnalysis obj, int index) {
        String sampleCode = "sampleCode_" + index;
        obj.setSampleCode(sampleCode);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setStartTime(JCoffeeAnalysis obj, int index) {
        Date startTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setStartTime(startTime);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setStatus(JCoffeeAnalysis obj, int index) {
        Long status = new Integer(index).longValue();
        obj.setStatus(status);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setTotalOfBagPushed(JCoffeeAnalysis obj, int index) {
        Long totalOfBagPushed = new Integer(index).longValue();
        obj.setTotalOfBagPushed(totalOfBagPushed);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setTotalOfReportedBags(JCoffeeAnalysis obj, int index) {
        Long totalOfReportedBags = new Integer(index).longValue();
        obj.setTotalOfReportedBags(totalOfReportedBags);
    }
    
    public void JCoffeeAnalysisDataOnDemand.setTruckNumber(JCoffeeAnalysis obj, int index) {
        String truckNumber = "truckNumber_" + index;
        obj.setTruckNumber(truckNumber);
    }
    
    public JCoffeeAnalysis JCoffeeAnalysisDataOnDemand.getSpecificJCoffeeAnalysis(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        JCoffeeAnalysis obj = data.get(index);
        Long id = obj.getId();
        return JCoffeeAnalysis.findJCoffeeAnalysis(id);
    }
    
    public JCoffeeAnalysis JCoffeeAnalysisDataOnDemand.getRandomJCoffeeAnalysis() {
        init();
        JCoffeeAnalysis obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return JCoffeeAnalysis.findJCoffeeAnalysis(id);
    }
    
    public boolean JCoffeeAnalysisDataOnDemand.modifyJCoffeeAnalysis(JCoffeeAnalysis obj) {
        return false;
    }
    
    public void JCoffeeAnalysisDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = JCoffeeAnalysis.findJCoffeeAnalysisEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'JCoffeeAnalysis' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<JCoffeeAnalysis>();
        for (int i = 0; i < 10; i++) {
            JCoffeeAnalysis obj = getNewTransientJCoffeeAnalysis(i);
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
