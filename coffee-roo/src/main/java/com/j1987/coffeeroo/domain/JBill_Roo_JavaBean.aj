// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.j1987.coffeeroo.domain;

import com.j1987.coffeeroo.domain.JAnalysis;
import com.j1987.coffeeroo.domain.JBill;
import com.j1987.coffeeroo.domain.JSubmissionForApproval;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

privileged aspect JBill_Roo_JavaBean {
    
    public String JBill.getReference() {
        return this.reference;
    }
    
    public void JBill.setReference(String reference) {
        this.reference = reference;
    }
    
    public Date JBill.getDateOfBill() {
        return this.dateOfBill;
    }
    
    public void JBill.setDateOfBill(Date dateOfBill) {
        this.dateOfBill = dateOfBill;
    }
    
    public String JBill.getDateOfBillAsString() {
        return this.dateOfBillAsString;
    }
    
    public void JBill.setDateOfBillAsString(String dateOfBillAsString) {
        this.dateOfBillAsString = dateOfBillAsString;
    }
    
    public String JBill.getTourName() {
        return this.tourName;
    }
    
    public void JBill.setTourName(String tourName) {
        this.tourName = tourName;
    }
    
    public String JBill.getFactoryCode() {
        return this.factoryCode;
    }
    
    public void JBill.setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
    }
    
    public String JBill.getFactoryName() {
        return this.factoryName;
    }
    
    public void JBill.setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }
    
    public String JBill.getExporterName() {
        return this.exporterName;
    }
    
    public void JBill.setExporterName(String exporterName) {
        this.exporterName = exporterName;
    }
    
    public String JBill.getPeriodTitle() {
        return this.periodTitle;
    }
    
    public void JBill.setPeriodTitle(String periodTitle) {
        this.periodTitle = periodTitle;
    }
    
    public String JBill.getArea() {
        return this.area;
    }
    
    public void JBill.setArea(String area) {
        this.area = area;
    }
    
    public BigDecimal JBill.getTotalWeightProductAllowed() {
        return this.totalWeightProductAllowed;
    }
    
    public void JBill.setTotalWeightProductAllowed(BigDecimal totalWeightProductAllowed) {
        this.totalWeightProductAllowed = totalWeightProductAllowed;
    }
    
    public BigDecimal JBill.getTotalWeightProductPushed() {
        return this.totalWeightProductPushed;
    }
    
    public void JBill.setTotalWeightProductPushed(BigDecimal totalWeightProductPushed) {
        this.totalWeightProductPushed = totalWeightProductPushed;
    }
    
    public BigDecimal JBill.getAmountWithoutTaxes() {
        return this.amountWithoutTaxes;
    }
    
    public void JBill.setAmountWithoutTaxes(BigDecimal amountWithoutTaxes) {
        this.amountWithoutTaxes = amountWithoutTaxes;
    }
    
    public JSubmissionForApproval JBill.getSubmission() {
        return this.submission;
    }
    
    public void JBill.setSubmission(JSubmissionForApproval submission) {
        this.submission = submission;
    }
    
    public Set<JAnalysis> JBill.getAnalyzes() {
        return this.analyzes;
    }
    
    public void JBill.setAnalyzes(Set<JAnalysis> analyzes) {
        this.analyzes = analyzes;
    }
    
}
