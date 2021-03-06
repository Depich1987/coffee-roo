// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.j1987.coffeeroo.domain;

import com.j1987.coffeeroo.domain.JLocalization;
import com.j1987.coffeeroo.domain.JLocalizationDataOnDemand;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;

privileged aspect JLocalizationDataOnDemand_Roo_DataOnDemand {
    
    declare @type: JLocalizationDataOnDemand: @Component;
    
    private Random JLocalizationDataOnDemand.rnd = new SecureRandom();
    
    private List<JLocalization> JLocalizationDataOnDemand.data;
    
    public JLocalization JLocalizationDataOnDemand.getNewTransientJLocalization(int index) {
        JLocalization obj = new JLocalization();
        setDescription(obj, index);
        setName(obj, index);
        return obj;
    }
    
    public void JLocalizationDataOnDemand.setDescription(JLocalization obj, int index) {
        String description = "description_" + index;
        obj.setDescription(description);
    }
    
    public void JLocalizationDataOnDemand.setName(JLocalization obj, int index) {
        String name = "name_" + index;
        obj.setName(name);
    }
    
    public JLocalization JLocalizationDataOnDemand.getSpecificJLocalization(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        JLocalization obj = data.get(index);
        Long id = obj.getId();
        return JLocalization.findJLocalization(id);
    }
    
    public JLocalization JLocalizationDataOnDemand.getRandomJLocalization() {
        init();
        JLocalization obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return JLocalization.findJLocalization(id);
    }
    
    public boolean JLocalizationDataOnDemand.modifyJLocalization(JLocalization obj) {
        return false;
    }
    
    public void JLocalizationDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = JLocalization.findJLocalizationEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'JLocalization' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<JLocalization>();
        for (int i = 0; i < 10; i++) {
            JLocalization obj = getNewTransientJLocalization(i);
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
