/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBL;

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
public class AdminDBIT {
    
    public AdminDBIT() {
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
     * Test of login method, of class AdminDB.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        Admin obj = null;
        AdminDB instance = new AdminDB();
        ResultSet expResult = null;
        ResultSet result = instance.login(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addUser method, of class AdminDB.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        Admin obj = null;
        AdminDB instance = new AdminDB();
        int expResult = 0;
        int result = instance.addUser(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of adminTable method, of class AdminDB.
     */
    @Test
    public void testAdminTable() {
        System.out.println("adminTable");
        AdminDB instance = new AdminDB();
        DefaultTableModel expResult = null;
        DefaultTableModel result = instance.adminTable();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAlldetails method, of class AdminDB.
     */
    @Test
    public void testGetAlldetails() {
        System.out.println("getAlldetails");
        AdminDB instance = new AdminDB();
        ResultSet expResult = null;
        ResultSet result = instance.getAlldetails();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
