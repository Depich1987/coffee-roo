// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.j1987.coffeeroo.domain;

import com.j1987.coffeeroo.domain.JExporter;
import com.j1987.coffeeroo.domain.JExporterDataOnDemand;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;

privileged aspect JExporterDataOnDemand_Roo_DataOnDemand {
    
    declare @type: JExporterDataOnDemand: @Component;
    
    private Random JExporterDataOnDemand.rnd = new SecureRandom();
    
    private List<JExporter> JExporterDataOnDemand.data;
    
    public JExporter JExporterDataOnDemand.getNewTransientJExporter(int index) {
        JExporter obj = new JExporter();
        setCode(obj, index);
        setDescription(obj, index);
        setName(obj, index);
        return obj;
    }
    
    public void JExporterDataOnDemand.setCode(JExporter obj, int index) {
        String code = "code_" + index;
        obj.setCode(code);
    }
    
    public void JExporterDataOnDemand.setDescription(JExporter obj, int index) {
        String description = "description_" + index;
        obj.setDescription(description);
    }
    
    public void JExporterDataOnDemand.setName(JExporter obj, int index) {
        String name = "name_" + index;
        obj.setName(name);
    }
    
    public JExporter JExporterDataOnDemand.getSpecificJExporter(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        JExporter obj = data.get(index);
        Long id = obj.getId();
        return JExporter.findJExporter(id);
    }
    
    public JExporter JExporterDataOnDemand.getRandomJExporter() {
        init();
        JExporter obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return JExporter.findJExporter(id);
    }
    
    public boolean JExporterDataOnDemand.modifyJExporter(JExporter obj) {
        return false;
    }
    
    public void JExporterDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = JExporter.findJExporterEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'JExporter' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<JExporter>();
        for (int i = 0; i < 10; i++) {
            JExporter obj = getNewTransientJExporter(i);
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
