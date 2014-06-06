// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.j1987.coffeeroo.domain;

import com.j1987.coffeeroo.domain.JBridge;
import com.j1987.coffeeroo.domain.JBridgeDataOnDemand;
import com.j1987.coffeeroo.domain.JBridgeIntegrationTest;
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

privileged aspect JBridgeIntegrationTest_Roo_IntegrationTest {
    
    declare @type: JBridgeIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: JBridgeIntegrationTest: @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");
    
    declare @type: JBridgeIntegrationTest: @Transactional;
    
    @Autowired
    JBridgeDataOnDemand JBridgeIntegrationTest.dod;
    
    @Test
    public void JBridgeIntegrationTest.testCountJBridges() {
        Assert.assertNotNull("Data on demand for 'JBridge' failed to initialize correctly", dod.getRandomJBridge());
        long count = JBridge.countJBridges();
        Assert.assertTrue("Counter for 'JBridge' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void JBridgeIntegrationTest.testFindJBridge() {
        JBridge obj = dod.getRandomJBridge();
        Assert.assertNotNull("Data on demand for 'JBridge' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'JBridge' failed to provide an identifier", id);
        obj = JBridge.findJBridge(id);
        Assert.assertNotNull("Find method for 'JBridge' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'JBridge' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void JBridgeIntegrationTest.testFindAllJBridges() {
        Assert.assertNotNull("Data on demand for 'JBridge' failed to initialize correctly", dod.getRandomJBridge());
        long count = JBridge.countJBridges();
        Assert.assertTrue("Too expensive to perform a find all test for 'JBridge', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<JBridge> result = JBridge.findAllJBridges();
        Assert.assertNotNull("Find all method for 'JBridge' illegally returned null", result);
        Assert.assertTrue("Find all method for 'JBridge' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void JBridgeIntegrationTest.testFindJBridgeEntries() {
        Assert.assertNotNull("Data on demand for 'JBridge' failed to initialize correctly", dod.getRandomJBridge());
        long count = JBridge.countJBridges();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<JBridge> result = JBridge.findJBridgeEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'JBridge' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'JBridge' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void JBridgeIntegrationTest.testFlush() {
        JBridge obj = dod.getRandomJBridge();
        Assert.assertNotNull("Data on demand for 'JBridge' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'JBridge' failed to provide an identifier", id);
        obj = JBridge.findJBridge(id);
        Assert.assertNotNull("Find method for 'JBridge' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyJBridge(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'JBridge' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void JBridgeIntegrationTest.testMergeUpdate() {
        JBridge obj = dod.getRandomJBridge();
        Assert.assertNotNull("Data on demand for 'JBridge' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'JBridge' failed to provide an identifier", id);
        obj = JBridge.findJBridge(id);
        boolean modified =  dod.modifyJBridge(obj);
        Integer currentVersion = obj.getVersion();
        JBridge merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'JBridge' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void JBridgeIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'JBridge' failed to initialize correctly", dod.getRandomJBridge());
        JBridge obj = dod.getNewTransientJBridge(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'JBridge' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'JBridge' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'JBridge' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void JBridgeIntegrationTest.testRemove() {
        JBridge obj = dod.getRandomJBridge();
        Assert.assertNotNull("Data on demand for 'JBridge' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'JBridge' failed to provide an identifier", id);
        obj = JBridge.findJBridge(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'JBridge' with identifier '" + id + "'", JBridge.findJBridge(id));
    }
    
}