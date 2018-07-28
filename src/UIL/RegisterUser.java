/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UIL;

import BLL.FaceDetector;
import BLL.GaborFeature;
import BLL.NeuralNet;
import BLL.PreProcess;
import BLL.Util;
import DBL.Users;
import DBL.UserDB;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.VideoInputFrameGrabber;

/**
 *
 * @author User
 */
public class RegisterUser extends javax.swing.JFrame {

     private static final Logger logger = Logger.getLogger(RegisterUser.class.getName());
     FileHandler fh;
    //definitions for image capture
    FrameGrabber grabber;
    IplImage ipimg;
    OpenCVFrameConverter.ToIplImage converter=new OpenCVFrameConverter.ToIplImage();
    BufferedImage bImg,captured,result;
    BufferedImage histImg,custImage[];
    static int i,uid,noi;
      
     File file;
    String imgname,imgid,imgnid;
    int id,output;
    
    class captureImage implements Runnable{
        protected volatile boolean runn = false;
        protected volatile FrameGrabber gr;
        Util uobj=new Util();
        @Override
        public void run() {
            try
            {
                grabber=new VideoInputFrameGrabber(0);
                grabber.start();
                while(runn)
                {
                    Frame frame=grabber.grab();
                    ipimg=converter.convertToIplImage(frame);
                    if(ipimg!=null)
                    {
                        //cvFlip(ipimg, ipimg, 1);
                        bImg=uobj.ipltoBuffered(ipimg);
                        Graphics g=jPanel1.getGraphics();
                        if (g.drawImage(bImg, 0, 0, getWidth(), getHeight() -150 , 0, 0, bImg.getWidth(), bImg.getHeight(), null))
                        if(runn == false)
                        {
                            System.out.println("Going to wait()");
			    this.wait();
                        }
                    }
                }
            }
            catch(FrameGrabber.Exception | InterruptedException e)
            {
                logger.log(Level.WARNING,e.getMessage(),e);
            }
        }    
    }
    /**
     * Creates new form videoFrame
     */
    public RegisterUser() {//constructor    
        getContentPane().setBackground(Color.CYAN);
        initComponents();
        openWebcam();
        btnAdd.setEnabled(false);
        i=0;//for adding to custImage
        noi=0;
        custImage=new BufferedImage[3];//the image array
       // txtAccountNumber.setEditable(false);
        uid=newUserid();
        output=newUserid1();
        //generateAccount();
         try
        {
            fh = new FileHandler(".\\Logger.log", true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();  
            fh.setFormatter(formatter); 
        }
        catch(IOException e)
        {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
         txtHidden.setText("15");
        txtMomentum.setText("0.7");
        txtlrate.setText("0.2");

    }
    private void openWebcam()//method to show the webcam from the runnable class
    {
        captureImage capt=new captureImage();
        Thread t=new Thread(capt);
        t.setDaemon(true);
        capt.runn=true;
        t.start();
    }   
    private int newUserid()
    {
        ResultSet rs=null;
        int tempid=0,usid=0;
        try
        {
            UserDB udobj=new UserDB();
            rs=udobj.getAlldetails();
            if(rs.next() == false)
            {
                System.out.println("Empty");
                usid=1;
            }
            else
            {
                do
                {
                    tempid=rs.getInt(1);
                }
                while(rs.next());
            }
            usid=tempid+1;
        }
        catch(SQLException e)
        {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
        return usid;
    }
        private int newUserid1()//gets user id for the new user
    {
        ResultSet rs=null;
        int usid=0;
        try
        {
            UserDB udobj=new UserDB();
            rs=udobj.getAlldetails();
            if(rs.next() == false)
            {
                usid=0;
            }
            else
            {
                do
                {
                    usid=rs.getInt(1);
                }
                while(rs.next());
            }
        }
        catch(SQLException e)
        {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
        return usid;
    }
    private void writeId(int id)//method to write the id
    {
        try
        {
            String text=String.valueOf(id)+",";
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(".\\trainingset\\traininglabels.txt", true)));
            pw.print(text);
            pw.close();
        }
        catch(IOException e)
        {
           logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCapture = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtMobile = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        txtemp = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtpass = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        labelCaptured = new javax.swing.JLabel();
        labelNoimages = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        labelCapturedHist = new javax.swing.JLabel();
        btnexit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnLoad = new javax.swing.JButton();
        btnTrain = new javax.swing.JButton();
        btnTest = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtHidden = new javax.swing.JTextField();
        txtlrate = new javax.swing.JTextField();
        txtMomentum = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnrestart = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnCapture.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        btnCapture.setText("CLICK");
        btnCapture.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCapture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaptureActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Book Antiqua", 1, 36)); // NOI18N
        jLabel2.setText("CREATE NEW USER");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Accountant Details"));
        jPanel2.setName(""); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Name");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Address");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Mobile");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Employee ID");

        txtMobile.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMobileKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setText("Password");

        txtpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtpass, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtemp, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(21, 21, 21)
                            .addComponent(txtMobile, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(txtpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnSave.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        btnSave.setText("Save");
        btnSave.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        btnAdd.setText("ADD");
        btnAdd.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        labelCaptured.setText("image 1");

        labelNoimages.setText("No.of Images - ");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(320, 240));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 508, Short.MAX_VALUE)
        );

        labelCapturedHist.setText("image 2");

        btnexit.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 18)); // NOI18N
        btnexit.setText("Exit");
        btnexit.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexitActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UIL/FaceRec.png"))); // NOI18N

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Hidden neurons");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Learning rate");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Momentum");

        btnLoad.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLoad.setText("SET LOADING.....");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        btnTrain.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnTrain.setText("NEURAL NETWORK TRAINING");
        btnTrain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrainActionPerformed(evt);
            }
        });

        btnTest.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnTest.setText("NEURAL NETWORK TESTING");
        btnTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Book Antiqua", 1, 36)); // NOI18N
        jLabel11.setText("TRAINING");

        txtHidden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHiddenActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel12.setText("Neural network");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(178, 178, 178)
                                .addComponent(jLabel8)
                                .addGap(53, 53, 53)
                                .addComponent(txtHidden, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel9)
                                .addGap(31, 31, 31)
                                .addComponent(txtlrate, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(169, 169, 169)
                                .addComponent(btnLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                                .addComponent(btnTrain)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addGap(47, 47, 47)
                                .addComponent(txtMomentum, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addComponent(btnTest))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 891, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(148, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtHidden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtlrate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMomentum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTrain, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTest, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jLabel12)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        btnrestart.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnrestart.setText("Please Check Your Inserted Info");
        btnrestart.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnrestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrestartActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Book Antiqua", 1, 30)); // NOI18N
        jLabel13.setText("Please Restart The System Before Training The System");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(147, 147, 147)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelNoimages, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelCaptured, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(7, 7, 7)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                                    .addComponent(btnCapture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelCapturedHist, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnexit, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(btnrestart))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 841, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(174, 174, 174)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelCaptured, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelCapturedHist, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(60, 60, 60)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnCapture, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(53, 53, 53)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnexit, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addComponent(btnrestart, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)))
                        .addComponent(labelNoimages)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        jPanel2.getAccessibleContext().setAccessibleName("New Customer");
        jPanel2.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCaptureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaptureActionPerformed

        FaceDetector fobj=new FaceDetector();
        PreProcess pobj=new PreProcess();
        try 
        {
            captured=bImg;
            result=fobj.detectFace(captured);
            if(result!=null)
            {
                histImg=pobj.histogramEqualization(result);
                labelCaptured.setIcon(new ImageIcon(result));
                labelCapturedHist.setIcon(new ImageIcon(histImg));
                btnAdd.setEnabled(true);
            }
            else
            {
                btnAdd.setEnabled(false);
                JOptionPane.showMessageDialog(rootPane,"Try again please","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (HeadlessException e) 
        {
            logger.log(Level.SEVERE, e.getMessage(), e);
            JOptionPane.showMessageDialog(rootPane,"Error capturing "+e,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCaptureActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        
        if(txtName.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane,"Enter Name of the customer","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else if(txtAddress.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane,"Enter Customer Address","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else if(txtMobile.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane,"Enter valid mobile no","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else if(txtMobile.getText().length()!=10)
        {
            JOptionPane.showMessageDialog(rootPane,"Enter valid mobile no","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else if(txtemp.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane,"Enter the employee ID","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else if(txtpass.getText().length()!=6)
        {
            JOptionPane.showMessageDialog(rootPane,"Enter valid password with 6 integers","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else if(noi!=8)
        {
            JOptionPane.showMessageDialog(rootPane,"Capture images and add","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            Users uobj=new Users();
            try
            {
                UserDB duobj=new UserDB();
                uobj.setName(txtName.getText());
                uobj.setAddress(txtAddress.getText());
                uobj.setMobile(Integer.parseInt(txtMobile.getText()));       
                uobj.setEmployeeID(txtemp.getText());
                uobj.setPassword(Integer.parseInt(txtpass.getText())); 
                int row=duobj.addAccountants(uobj);
                
                if(row>0&&row>0)
                {
                    JOptionPane.showMessageDialog(rootPane,"Created successfully","Created user",JOptionPane.INFORMATION_MESSAGE);
                    //UserTraining aobj=new UserTraining();
                    //aobj.show();     
                    //this.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(rootPane,"Could not create user","ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
            catch(NumberFormatException | HeadlessException e)
            {
                logger.log(Level.SEVERE, e.getMessage(), e);
                JOptionPane.showMessageDialog(rootPane,"Could not create user"+e,"ERROR",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        
        noi=noi+1;//count of images
        try
        {
            if(noi<9)
            {
                if(noi<=6)
                {
                File output=new File(".\\trainingset\\"+uid+"_"+noi+".jpg");
                ImageIO.write(histImg,"jpg",output);
                writeId(uid);//method to write the labels
                labelNoimages.setText("No.of Images - "+noi);
                if(noi==8)
                    JOptionPane.showMessageDialog(rootPane,"Training images Added","Added",JOptionPane.INFORMATION_MESSAGE);
                }   //i++;
                else
                {
                    File output=new File(".\\testSet\\"+uid+"_"+noi+".jpg");
                    ImageIO.write(histImg,"jpg",output);
                    writeId(uid);//method to write the labels
                    labelNoimages.setText("No.of Images - "+noi);
                }
            
           if(noi==8)
                JOptionPane.showMessageDialog(rootPane,"You have added the images","Added",JOptionPane.ERROR_MESSAGE);
        }
         else
                JOptionPane.showMessageDialog(rootPane,"You have added the images","Added",JOptionPane.ERROR_MESSAGE);
    }
        catch (IOException | HeadlessException e)
        {
            logger.log(Level.SEVERE, e.getMessage(), e);
            JOptionPane.showMessageDialog(rootPane,"Could not add "+e,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexitActionPerformed
          
             System.exit(0);
    }//GEN-LAST:event_btnexitActionPerformed

    private void txtMobileKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMobileKeyTyped
        
        char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter) || enter==KeyEvent.VK_BACK_SPACE || enter==KeyEvent.VK_DELETE || enter==KeyEvent.VK_PERIOD))
        {
            JOptionPane.showMessageDialog(null,"enter numbers only");
            evt.consume();
        }
    }//GEN-LAST:event_txtMobileKeyTyped

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        try
        {
            jLabel11.setText("");
            System.out.println("Creating");
            NeuralNet nnet=new NeuralNet();
            nnet.createTrainingset(output);
            JOptionPane.showMessageDialog(null,"Training set created","SUCCESS",JOptionPane.INFORMATION_MESSAGE);
            jLabel12.setText("");
        }
        catch(HeadlessException e)
        {
            logger.log(Level.SEVERE, e.getMessage(), e);
            JOptionPane.showMessageDialog(null,"Could not create Training set","ERROR",JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnLoadActionPerformed
 private void openFile()//alternative to real time detection
    {
        final JFrame frame = new JFrame("Select image to be recognized");
        JFileChooser fc=new JFileChooser(".\\testSet");
        int returnVal = fc.showOpenDialog(frame);
        if(returnVal==JFileChooser.APPROVE_OPTION)
        {
            file=fc.getSelectedFile();
            try
            {
                String filename=file.toString();
            }
            catch(Exception e)
            {
                logger.log(Level.WARNING, "Error in reading file {0}", e);
            }
        }
    }
    private void btnTrainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrainActionPerformed

        if(txtHidden.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter hidden layers","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else if(txtlrate.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter learning rate","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else if(txtMomentum.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter momentum","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            try
            {
                String error[]=new String[2];
                int hidden=Integer.parseInt(txtHidden.getText());
                double lrate=Double.parseDouble(txtlrate.getText());
                double momentum=Double.parseDouble(txtMomentum.getText());
                jLabel12.setText("Training.....");
                NeuralNet nnet=new NeuralNet();
                error=NeuralNet.trainNetwork(output,hidden ,lrate,momentum);//look into this and make non static
                jLabel12.setText("Error rate: "+error[0]+"   Iterations: "+error[1]);
            }
            catch(NumberFormatException e)
            {
                logger.log(Level.SEVERE, e.getMessage(), e);
                JOptionPane.showMessageDialog(null,"Error in training","ERROR",JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_btnTrainActionPerformed

    private void btnTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestActionPerformed
        NeuralNet obj=new NeuralNet();
        GaborFeature gf=new GaborFeature();
        double feature[]=new double[80];
        int id=0;
        try
        {
            openFile();
            BufferedImage test=ImageIO.read(file);
            imgname=file.getName();
            imgid=imgname.substring(0, 1);
            feature=gf.getFeature(test);
            id=obj.testingFaces(feature, output);
            imgnid=String.valueOf(id);
            jLabel12.setText("Actual results :"+imgnid+"     Expected results :"+imgid);
        }
        catch(IOException e)
        {
            logger.log(Level.WARNING, "Error in reading file {0}", e);
        }

    }//GEN-LAST:event_btnTestActionPerformed

    private void txtHiddenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHiddenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHiddenActionPerformed

    private void txtpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpassActionPerformed

    private void btnrestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrestartActionPerformed
  
        AdminForm obj=new AdminForm();
        obj.show();
        this.dispose();        
    }//GEN-LAST:event_btnrestartActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegisterUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCapture;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnTest;
    private javax.swing.JButton btnTrain;
    private javax.swing.JButton btnexit;
    private javax.swing.JButton btnrestart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel labelCaptured;
    private javax.swing.JLabel labelCapturedHist;
    private javax.swing.JLabel labelNoimages;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtHidden;
    private javax.swing.JTextField txtMobile;
    private javax.swing.JTextField txtMomentum;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtemp;
    private javax.swing.JTextField txtlrate;
    private javax.swing.JTextField txtpass;
    // End of variables declaration//GEN-END:variables
}
