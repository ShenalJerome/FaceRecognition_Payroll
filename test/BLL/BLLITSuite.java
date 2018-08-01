/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

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
@Suite.SuiteClasses({BLL.NeuralNetIT.class, BLL.PreProcessIT.class, BLL.FaceDetectorIT.class, BLL.GaborFeatureIT.class, BLL.UtilIT.class, BLL.FeatureExtractionIT.class})
public class BLLITSuite {

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
