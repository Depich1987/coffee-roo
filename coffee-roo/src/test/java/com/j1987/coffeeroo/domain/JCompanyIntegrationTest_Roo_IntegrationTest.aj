// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.j1987.coffeeroo.domain;

import com.j1987.coffeeroo.domain.JCompany;
import com.j1987.coffeeroo.domain.JCompanyDataOnDemand;
import com.j1987.coffeeroo.domain.JCompanyIntegrationTest;
import java.util.Iterator;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect JCompanyIntegrationTest_Roo_IntegrationTest {
    
    declare @type: JCompanyIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: JCompanyIntegrationTest: @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");
    
    declare @type: JCompanyIntegrationTest: @Transactional;
    
    @Autowired
    JCompanyDataOnDemand JCompanyIntegrationTest.dod;
    
    @Test
    public void JCompanyIntegrationTest.testCountJCompanys() {
        Assert.assertNotNull("Data on demand for 'JCompany' failed to initialize correctly", dod.getRandomJCompany());
        long count = JCompany.countJCompanys();
        Assert.assertTrue("Counter for 'JCompany' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void JCompanyIntegrationTest.testFindJCompany() {
        JCompany obj = dod.getRandomJCompany();
        Assert.assertNotNull("Data on demand for 'JCompany' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'JCompany' failed to provide an identifier", id);
        obj = JCompany.findJCompany(id);
        Assert.assertNotNull("Find method for 'JCompany' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'JCompany' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void JCompanyIntegrationTest.testFindAllJCompanys() {
        Assert.assertNotNull("Data on demand for 'JCompany' failed to initialize correctly", dod.getRandomJCompany());
        long count = JCompany.countJCompanys();
        Assert.assertTrue("Too expensive to perform a find all test for 'JCompany', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<JCompany> result = JCompany.findAllJCompanys();
        Assert.assertNotNull("Find all method for 'JCompany' illegally returned null", result);
        Assert.assertTrue("Find all method for 'JCompany' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void JCompanyIntegrationTest.testFindJCompanyEntries() {
        Assert.assertNotNull("Data on demand for 'JCompany' failed to initialize correctly", dod.getRandomJCompany());
        long count = JCompany.countJCompanys();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<JCompany> result = JCompany.findJCompanyEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'JCompany' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'JCompany' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void JCompanyIntegrationTest.testFlush() {
        JCompany obj = dod.getRandomJCompany();
        Assert.assertNotNull("Data on demand for 'JCompany' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'JCompany' failed to provide an identifier", id);
        obj = JCompany.findJCompany(id);
        Assert.assertNotNull("Find method for 'JCompany' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyJCompany(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'JCompany' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void JCompanyIntegrationTest.testMergeUpdate() {
        JCompany obj = dod.getRandomJCompany();
        Assert.assertNotNull("Data on demand for 'JCompany' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'JCompany' failed to provide an identifier", id);
        obj = JCompany.findJCompany(id);
        boolean modified =  dod.modifyJCompany(obj);
        Integer currentVersion = obj.getVersion();
        JCompany merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'JCompany' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void JCompanyIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'JCompany' failed to initialize correctly", dod.getRandomJCompany());
        JCompany obj = dod.getNewTransientJCompany(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'JCompany' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'JCompany' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'JCompany' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void JCompanyIntegrationTest.testRemove() {
        JCompany obj = dod.getRandomJCompany();
        Assert.assertNotNull("Data on demand for 'JCompany' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'JCompany' failed to provide an identifier", id);
        obj = JCompany.findJCompany(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'JCompany' with identifier '" + id + "'", JCompany.findJCompany(id));
    }
    
}
