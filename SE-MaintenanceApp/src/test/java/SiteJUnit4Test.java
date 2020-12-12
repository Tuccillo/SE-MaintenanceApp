/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.team14.se.maintenanceapp.Site;
import java.sql.Connection;
import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author domal
 */
public class SiteJUnit4Test {
    
    public SiteJUnit4Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class Site.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Site instance = new Site("");
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Site.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Site instance = new Site("");
        instance.setName(name);
    }

    /**
     * Test of toString method, of class Site.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Site instance = new Site("");
        String expResult = "Site{" + "name=" + instance.getName() + '}';
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSites method, of class Site.
     */
    @Test
    public void testGetSites() throws Exception {
        System.out.println("getSites");
        Connection conn = null;
        LinkedList<Site> expResult = null;
        LinkedList<Site> result = Site.getSites(conn);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSite method, of class Site.
     */
    @Test
    public void testAddSite() throws Exception {
        System.out.println("addSite");
        Connection conn = null;
        Site site = null;
        Site instance = null;
        instance.addSite(conn, site);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeSite method, of class Site.
     */
    @Test
    public void testRemoveSite() throws Exception {
        System.out.println("removeSite");
        Connection conn = null;
        Site site = null;
        Site instance = null;
        instance.removeSite(conn, site);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateSite method, of class Site.
     */
    @Test
    public void testUpdateSite() throws Exception {
        System.out.println("updateSite");
        Connection conn = null;
        Site site = null;
        String oldName = "";
        Site instance = null;
        instance.updateSite(conn, site, oldName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}