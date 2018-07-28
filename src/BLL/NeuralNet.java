/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.neuroph.core.*;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
//import org.neuroph.util.data.norm.DecimalScaleNormalizer;
import org.neuroph.core.data.norm.DecimalScaleNormalizer;
import org.neuroph.core.exceptions.VectorSizeMismatchException;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.util.TrainingSetImport;
import org.neuroph.util.TransferFunctionType;


/**
 *
 * @author User
 */

/**
 *
 * @author User
 */
public class NeuralNet {
    
    private static final Logger logger = Logger.getLogger(NeuralNet.class.getName());
    FileHandler fh;
    static File dir = new File(".\\trainingset");
  
     static final String[] extensions = new String[]{//the file extensions
        "gif", "png", "bmp","jpg"
    };
    
    static final FilenameFilter imgFilter = new FilenameFilter() {//to filter the directory with images

        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : extensions) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };
    static DataSet testTra=new DataSet(80, 2);
    /**
     * This method creates the training set
     * @param outputSize the size of the output(the number of customers)
     */
    public void createTrainingset(int outputSize)
    {
        GaborFeature gl=new GaborFeature();
        String imgName,imgId;
        int id,x;
        DataSet trainingSet=new DataSet(80, outputSize);//initialize the dataset
        BufferedImage img=null;
        try
        {
        if(dir.isDirectory())
        {
            for(final File f: dir.listFiles(imgFilter))
            {
                try
                {
                    img = ImageIO.read(f);
                    imgName = f.getName();//name of the image
                    imgId = imgName.substring(0, 1);//id of the image by getting the first letter of the image
                    id = Integer.parseInt(imgId);
                    
                    double[]output=new double[outputSize];
                    Arrays.fill(output,0);
                    output[id-1]=1;
                    
                    double []input=new double[80];
                    input=gl.getFeature(img);
                    trainingSet.addRow(new DataSetRow(input,output));
                    System.out.println((output.length));
                }
                catch(IOException | NumberFormatException | VectorSizeMismatchException e)
                {
                    logger(e);
                    JOptionPane.showMessageDialog(null, "Error creating training set check log file"
                    , "ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
                trainingSet.saveAsTxt("trainingSet.txt",",");
                trainingSet.normalize(new DecimalScaleNormalizer());
                trainingSet.saveAsTxt("trainingdataSet.txt",",");
                testTra=trainingSet;
        }
        catch(Exception ex)
        {
            logger(ex);
            JOptionPane.showMessageDialog(null, "Error creating training set check log file"
                    , "ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public static String[] trainNetwork(int outputSize,int hidden,double lrate,double momentum)
    {
        String error[]=new String[2];
        DataSet trainingSet;
        try
        {
            File file=new File(".\\trainingdataSet.txt");    
            if(!file.exists())
            {                    
                JOptionPane.showMessageDialog(null,"Dataset does not exist","ERROR",
                JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                trainingSet=TrainingSetImport.importFromFile(".\\trainingdataSet.txt",80,outputSize,",");
                if(trainingSet.getOutputSize()!=outputSize)
                {
                    JOptionPane.showMessageDialog(null,"Output sizes dont match create/load dataset first","ERROR",
                    JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    MultiLayerPerceptron neuralNet = new MultiLayerPerceptron(TransferFunctionType.SIGMOID,80,hidden ,outputSize);
                    MomentumBackpropagation backProp = new MomentumBackpropagation() ;
                    backProp.setLearningRate(lrate);
                    backProp.setMomentum(momentum);
                    backProp.setMaxError(0.01);
                    backProp.setMaxIterations(3000);
                    neuralNet.setLearningRule(backProp);
                    neuralNet.learn(trainingSet);
                    neuralNet.save("D:\\Neuroph\\fc\\Neural Networks\\NewNeuralNetwork.nnet");
                    error[0]=String.valueOf(neuralNet.getLearningRule().getPreviousEpochError());
                    error[1]=String.valueOf(neuralNet.getLearningRule().getCurrentIteration());
                }
            }
            //int outputSize=0;
            //outputSize=training.getOutputSize();
        }
        catch(HeadlessException e)
        {
            //*make this non static*/logger(e);
        } catch (IOException | NumberFormatException ex) {
           //logger(ex);
        }
        return error;
    }
    
    /**
     * Method to recognize input faces
     * @param testImage the features of the image to be recognized
     * @param outputsize the output size the network
     * @return the highest values from the recognition 
     */
    public int recognizeFaces(double[] testImage,int outputsize)
    {
        DataSet newData=new DataSet(80, outputsize);
        double[] nOutput=new double[outputsize];
        double[] output=new double[outputsize];
        double[] input=null;
        Arrays.fill(output,0);
        int high=0,id = 0,size=0;
        try
        {
            newData=TrainingSetImport.importFromFile(".\\trainingSet.txt",80,outputsize,",");
            newData.addRow(new DataSetRow(testImage,output));
            newData.normalize(new DecimalScaleNormalizer());
            newData.saveAsTxt("trainingSetNEWW.txt",",");
            size=newData.size();
            DataSetRow inputRow=newData.getRowAt(size-1);
            input=inputRow.getInput();
            
            for(int i=0;i<input.length;i++)
                System.out.println(input[i]);
            
            NeuralNetwork neural=NeuralNetwork.load("D:\\Neuroph\\fc\\Neural Networks\\NewNeuralNetwork.nnet");
            neural.setInput(input);
            neural.calculate();
            nOutput=neural.getOutput();//get the output of the neural network
            
            //method to get the highest id from the output 
            for(int i=1;i<nOutput.length;i++)
            {
                if(nOutput[i]>=nOutput[high])
                {
                    high=i;
                }   
            }
            for(int i=0;i<nOutput.length;i++)
                System.out.println(nOutput[i]);
            id=high+1;
        }
        
        catch(VectorSizeMismatchException e)
        {
            logger(e);
        } catch (IOException | NumberFormatException ex) {
            logger(ex);
        }
        return id;
    }
    
    
    
    private void logger(Exception e)
    {
        try {
            fh = new FileHandler(".\\BLLLogger.log", true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter(); 
            fh.setFormatter(formatter);
            logger.log(Level.SEVERE, e.getMessage(), e);
        } catch (IOException | SecurityException ex) {
            Logger.getLogger(FaceDetector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String args[])
    {
        String x[]=trainNetwork(2,10,0.2,0.7);
        System.out.println("Error : "+x[0]);
        System.out.println("TotalE : "+x[1]);
    }
    
    
    /*public static void createTrainingset2(int outputSize)
    {
        GaborFeature gl=new GaborFeature();
        String imgName,imgId;
        int id,x;
        DataSet trainingSet=new DataSet(80, outputSize);//initialize the dataset
        BufferedImage img=null;
        try
        {
        if(dir.isDirectory())
        {
            for(final File f: dir.listFiles(imgFilter))
            {
                try
                {
                    img = ImageIO.read(f);
                    imgName = f.getName();//name of the image
                    imgId = imgName.substring(0, 1);//id of the image by getting the first letter of the image
                    id = Integer.parseInt(imgId);
                    
                    double[]output=new double[outputSize];
                    Arrays.fill(output,0);
                    output[id-1]=1;
                    
                    double []input=new double[80];
                    input=gl.getFeature(img);
                    /*do the gabor filtering and extract the features and put to dataset
                    trainingSet.addRow(new DataSetRow(input,output));
                    System.out.println((output.length));
                }
                catch(IOException | NumberFormatException | VectorSizeMismatchException e)
                {
                    //logger(e);
                }
            }
        }
                trainingSet.normalize(new DecimalScaleNormalizer());
                trainingSet.saveAsTxt("trainingdataSet.txt",",");
                testTra=trainingSet;
        }
        catch(Exception ex)
        {
            //logger(ex);
        }
    }*/

    public int testingFaces(double[] testImage,int outputsize) {
         DataSet newData=new DataSet(80, outputsize);
        double[] nOutput=new double[outputsize];
        double[] output=new double[outputsize];
        double[] input=null;
        Arrays.fill(output,0);
        int high=0,id = 0,size=0;
        try
        {
            newData=TrainingSetImport.importFromFile(".\\trainingSet.txt",80,outputsize,",");
            newData.addRow(new DataSetRow(testImage,output));
            newData.normalize(new DecimalScaleNormalizer());
            newData.saveAsTxt("trainingSetNEWW.txt",",");
            size=newData.size();
            DataSetRow inputRow=newData.getRowAt(size-1);
            input=inputRow.getInput();
            NeuralNetwork neural=NeuralNetwork.load("D:\\Neuroph\\fc\\Neural Networks\\NewNeuralNetwork.nnet");
            neural.setInput(input);
            neural.calculate();
            nOutput=neural.getOutput();//get the output of the neural network
            
            //method to get the highest id from the output 
            for(int i=1;i<nOutput.length;i++)
            {
                if(nOutput[i]>=nOutput[high])
                {
                    high=i;
                }   
            }
            id=high+1;
            for(int i=0;i<nOutput.length;i++)
                System.out.println(nOutput[i]);   
        }   
        catch(VectorSizeMismatchException e)
        {
            logger(e);
        } catch (IOException | NumberFormatException ex) {
            logger(ex);
        }
        return id;
    }
}
