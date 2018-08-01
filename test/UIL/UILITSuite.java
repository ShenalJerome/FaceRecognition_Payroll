/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIL;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Shenal_Jerome
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({UIL.HomeIT.class, UIL.AdminFormIT.class, UIL.UserVerificationIT.class, UIL.PayRoll_SystemIT.class, UIL.LoginIT.class, UIL.RegisterUserIT.class})
public class UILITSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
