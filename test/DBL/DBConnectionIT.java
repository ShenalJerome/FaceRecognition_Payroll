/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBL;

import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Shenal_Jerome
 */
public class DBConnectionIT {
    
    public DBConnectionIT() {
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
     * Test of createConnection method, of class DBConnection.
     */
    @Test
    public void testCreateConnection() {
        System.out.println("createConnection");
        DBConnection instance = new DBConnection();
        Connection expResult = null;
        Connection result = instance.createConnection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addValues method, of class DBConnection.
     */
    @Test
    public void testAddValues() {
        System.out.println("addValues");
        String SQL = "";
        DBConnection instance = new DBConnection();
        int expResult = 0;
        int result = instance.addValues(SQL);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValues method, of class DBConnection.
     */
    @Test
    public void testGetValues() {
        System.out.println("getValues");
        String SQL = "";
        DBConnection instance = new DBConnection();
        ResultSet expResult = null;
        ResultSet result = instance.getValues(SQL);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTables method, of class DBConnection.
     */
    @Test
    public void testGetTables() {
        System.out.println("getTables");
        String SQL = "";
        DBConnection instance = new DBConnection();
        DefaultTableModel expResult = null;
        DefaultTableModel result = instance.getTables(SQL);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
