package com.healthcare;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit Tests for Healthcare Servlet
 * 
 * PURPOSE: Verify servlet functions correctly
 * 
 * WHY TESTING: Ensures code works as expected before deployment
 * 
 * WHAT IT TESTS:
 * 1. Servlet can be instantiated
 * 2. Servlet info is correct
 */
public class HealthcareServletTest {
    
    /**
     * Test 1: Verify servlet can be created
     * 
     * WHY: If servlet constructor fails, app won't work
     */
    @Test
    public void testServletCreation() {
        // Create new instance of our servlet
        HealthcareServlet servlet = new HealthcareServlet();
        
        // Verify it's not null (was created successfully)
        assertNotNull("Servlet should be created successfully", servlet);
    }
    
    /**
     * Test 2: Verify servlet information is correct
     * 
     * WHY: Servlet should return proper metadata
     */
    @Test
    public void testServletInfo() {
        // Create servlet instance
        HealthcareServlet servlet = new HealthcareServlet();
        
        // Get servlet info
        String info = servlet.getServletInfo();
        
        // Verify info is not null or empty
        assertNotNull("Servlet info should not be null", info);
        assertFalse("Servlet info should not be empty", info.isEmpty());
        
        // Verify info contains expected text
        assertTrue("Servlet info should contain 'Healthcare'", 
                   info.contains("Healthcare"));
    }
}
