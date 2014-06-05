// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.j1987.coffeeroo.domain;

import com.j1987.coffeeroo.domain.JFirm;
import com.j1987.coffeeroo.domain.JFirmDataOnDemand;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;

privileged aspect JFirmDataOnDemand_Roo_DataOnDemand {
    
    declare @type: JFirmDataOnDemand: @Component;
    
    private Random JFirmDataOnDemand.rnd = new SecureRandom();
    
    private List<JFirm> JFirmDataOnDemand.data;
    
    public JFirm JFirmDataOnDemand.getNewTransientJFirm(int index) {
        JFirm obj = new JFirm();
        setDealerCode(obj, index);
        setName(obj, index);
        setPriceCocoaAnalysis(obj, index);
        setPriceCoffeeAnalysis(obj, index);
        setProductWeightBag(obj, index);
        return obj;
    }
    
    public void JFirmDataOnDemand.setDealerCode(JFirm obj, int index) {
        String dealerCode = "dealerCode_" + index;
        obj.setDealerCode(dealerCode);
    }
    
    public void JFirmDataOnDemand.setName(JFirm obj, int index) {
        String name = "name_" + index;
        obj.setName(name);
    }
    
    public void JFirmDataOnDemand.setPriceCocoaAnalysis(JFirm obj, int index) {
        BigDecimal priceCocoaAnalysis = BigDecimal.valueOf(index);
        obj.setPriceCocoaAnalysis(priceCocoaAnalysis);
    }
    
    public void JFirmDataOnDemand.setPriceCoffeeAnalysis(JFirm obj, int index) {
        BigDecimal priceCoffeeAnalysis = BigDecimal.valueOf(index);
        obj.setPriceCoffeeAnalysis(priceCoffeeAnalysis);
    }
    
    public void JFirmDataOnDemand.setProductWeightBag(JFirm obj, int index) {
        BigDecimal productWeightBag = BigDecimal.valueOf(index);
        obj.setProductWeightBag(productWeightBag);
    }
    
    public JFirm JFirmDataOnDemand.getSpecificJFirm(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        JFirm obj = data.get(index);
        Long id = obj.getId();
        return JFirm.findJFirm(id);
    }
    
    public JFirm JFirmDataOnDemand.getRandomJFirm() {
        init();
        JFirm obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return JFirm.findJFirm(id);
    }
    
    public boolean JFirmDataOnDemand.modifyJFirm(JFirm obj) {
        return false;
    }
    
    public void JFirmDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = JFirm.findJFirmEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'JFirm' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<JFirm>();
        for (int i = 0; i < 10; i++) {
            JFirm obj = getNewTransientJFirm(i);
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
