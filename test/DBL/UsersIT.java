/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBL;

import java.awt.image.BufferedImage;
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
public class UsersIT {
    
    public UsersIT() {
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
     * Test of getName method, of class Users.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Users instance = new Users();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Users.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Users instance = new Users();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAddress method, of class Users.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        Users instance = new Users();
        String expResult = "";
        String result = instance.getAddress();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAddress method, of class Users.
     */
    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        String address = "";
        Users instance = new Users();
        instance.setAddress(address);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMobile method, of class Users.
     */
    @Test
    public void testGetMobile() {
        System.out.println("getMobile");
        Users instance = new Users();
        int expResult = 0;
        int result = instance.getMobile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMobile method, of class Users.
     */
    @Test
    public void testSetMobile() {
        System.out.println("setMobile");
        int mobile = 0;
        Users instance = new Users();
        instance.setMobile(mobile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmployeeID method, of class Users.
     */
    @Test
    public void testGetEmployeeID() {
        System.out.println("getEmployeeID");
        Users instance = new Users();
        String expResult = "";
        String result = instance.getEmployeeID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmployeeID method, of class Users.
     */
    @Test
    public void testSetEmployeeID() {
        System.out.println("setEmployeeID");
        String employeeID = "";
        Users instance = new Users();
        instance.setEmployeeID(employeeID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getImg1 method, of class Users.
     */
    @Test
    public void testGetImg1() {
        System.out.println("getImg1");
        Users instance = new Users();
        BufferedImage expResult = null;
        BufferedImage result = instance.getImg1();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setImg1 method, of class Users.
     */
    @Test
    public void testSetImg1() {
        System.out.println("setImg1");
        BufferedImage img1 = null;
        Users instance = new Users();
        instance.setImg1(img1);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getImg2 method, of class Users.
     */
    @Test
    public void testGetImg2() {
        System.out.println("getImg2");
        Users instance = new Users();
        BufferedImage expResult = null;
        BufferedImage result = instance.getImg2();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setImg2 method, of class Users.
     */
    @Test
    public void testSetImg2() {
        System.out.println("setImg2");
        BufferedImage img2 = null;
        Users instance = new Users();
        instance.setImg2(img2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getImg3 method, of class Users.
     */
    @Test
    public void testGetImg3() {
        System.out.println("getImg3");
        Users instance = new Users();
        BufferedImage expResult = null;
        BufferedImage result = instance.getImg3();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setImg3 method, of class Users.
     */
    @Test
    public void testSetImg3() {
        System.out.println("setImg3");
        BufferedImage img3 = null;
        Users instance = new Users();
        instance.setImg3(img3);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getID method, of class Users.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Users instance = new Users();
        int expResult = 0;
        int result = instance.getID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setID method, of class Users.
     */
    @Test
    public void testSetID() {
        System.out.println("setID");
        int ID = 0;
        Users instance = new Users();
        instance.setID(ID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class Users.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Users instance = new Users();
        int expResult = 0;
        int result = instance.getPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPassword method, of class Users.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        int password = 0;
        Users instance = new Users();
        instance.setPassword(password);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
