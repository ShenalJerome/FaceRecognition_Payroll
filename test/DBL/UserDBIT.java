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
public class UserDBIT {
    
    public UserDBIT() {
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
     * Test of addAccountants method, of class UserDB.
     */
    @Test
    public void testAddAccountants() {
        System.out.println("addAccountants");
        Users obj = null;
        UserDB instance = new UserDB();
        int expResult = 0;
        int result = instance.addAccountants(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDetails method, of class UserDB.
     */
    @Test
    public void testGetDetails() {
        System.out.println("getDetails");
        Users obj = null;
        UserDB instance = new UserDB();
        ResultSet expResult = null;
        ResultSet result = instance.getDetails(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccountdetails method, of class UserDB.
     */
    @Test
    public void testGetAccountdetails() {
        System.out.println("getAccountdetails");
        Users obj = null;
        UserDB instance = new UserDB();
        ResultSet expResult = null;
        ResultSet result = instance.getAccountdetails(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAlldetails method, of class UserDB.
     */
    @Test
    public void testGetAlldetails() {
        System.out.println("getAlldetails");
        UserDB instance = new UserDB();
        ResultSet expResult = null;
        ResultSet result = instance.getAlldetails();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accountantsTable method, of class UserDB.
     */
    @Test
    public void testAccountantsTable() {
        System.out.println("accountantsTable");
        UserDB instance = new UserDB();
        DefaultTableModel expResult = null;
        DefaultTableModel result = instance.accountantsTable();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateAccountants method, of class UserDB.
     */
    @Test
    public void testUpdateAccountants() {
        System.out.println("updateAccountants");
        Users obj = null;
        UserDB instance = new UserDB();
        int expResult = 0;
        int result = instance.updateAccountants(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
