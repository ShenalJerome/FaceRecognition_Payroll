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
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Shenal_Jerome
 */
public class NeuralNetIT {
    
    public NeuralNetIT() {
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
     * Test of createTrainingset method, of class NeuralNet.
     */
    @Test
    public void testCreateTrainingset() {
        System.out.println("createTrainingset");
        int outputSize = 0;
        NeuralNet instance = new NeuralNet();
        instance.createTrainingset(outputSize);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of trainNetwork method, of class NeuralNet.
     */
    @Test
    public void testTrainNetwork() {
        System.out.println("trainNetwork");
        int outputSize = 0;
        int hidden = 0;
        double lrate = 0.0;
        double momentum = 0.0;
        String[] expResult = null;
        String[] result = NeuralNet.trainNetwork(outputSize, hidden, lrate, momentum);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of recognizeFaces method, of class NeuralNet.
     */
    @Test
    public void testRecognizeFaces() {
        System.out.println("recognizeFaces");
        double[] testImage = null;
        int outputsize = 0;
        NeuralNet instance = new NeuralNet();
        int expResult = 0;
        int result = instance.recognizeFaces(testImage, outputsize);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class NeuralNet.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        NeuralNet.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testingFaces method, of class NeuralNet.
     */
    @Test
    public void testTestingFaces() {
        System.out.println("testingFaces");
        double[] testImage = null;
        int outputsize = 0;
        NeuralNet instance = new NeuralNet();
        int expResult = 0;
        int result = instance.testingFaces(testImage, outputsize);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
