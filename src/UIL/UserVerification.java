/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UIL;

import BLL.FaceDetector;
import BLL.GaborFeature;
import BLL.NeuralNet;
import BLL.Util;
import BLL.PreProcess;
import DBL.Users;
import DBL.UserDB;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.bytedeco.javacpp.opencv_core.IplImage;
import static org.bytedeco.javacpp.opencv_core.cvFlip;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.javacv.VideoInputFrameGrabber;

/**
 *
 * @author User
 */
public class UserVerification extends javax.swing.JFrame {

    private static final Logger logger = Logger.getLogger(UserVerification.class.getName());
    FileHandler fh;
    FrameGrabber grabber;
    IplImage ipimg;
    OpenCVFrameConverter.ToIplImage converter=new OpenCVFrameConverter.ToIplImage();
    BufferedImage bImg,captured,detected,hist;
    File file;
    String name,address;int mobile,acc,outputs;
    
    class captureImage implements Runnable{
        protected volatile boolean runn = false;
        protected volatile FrameGrabber gr;
        Util uobj=new Util();
        @Override
        public void run() {
            try
            {
                grabber=new VideoInputFrameGrabber(0);
                grabber=new  OpenCVFrameGrabber(0);
                grabber.start();
                while(runn)
                {
                    Frame frame=grabber.grab();
                    ipimg=converter.convertToIplImage(frame);
                    if(ipimg!=null)
                    {
                        cvFlip(ipimg, ipimg, 1);
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
    public UserVerification(){
        getContentPane().setBackground(Color.CYAN);
        initComponents();
        capture();
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
        outputs=newUserid();
    }
    
    private void capture()
    {
        captureImage capt=new captureImage();
        Thread t=new Thread(capt);
        t.setDaemon(true);
        capt.runn=true;
        t.start();
    }

    private void openFile()//alternative to real time detection
    {
        final JFrame frame = new JFrame("Select image to be recognized");
        JFileChooser fc=new JFileChooser("D:\\Final Project\\FaceRecognition_Payroll\\trainingset");
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
    
    private int newUserid()//gets user id for the new user
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
        int ids=usid;
        System.out.println(ids);
        return ids;
    }
    /**
     * this is the method to recognize the faces
     */
    private void recognizeFaces(BufferedImage newImage)
             {
        NeuralNet nnet=new NeuralNet();
        GaborFeature gf=new GaborFeature();
        ResultSet rs;
        Users cobj=new Users();
        double feature[]=new double[80];
        int id,Password;
        feature=gf.getFeature(newImage);
        try
        {
            id=nnet.recognizeFaces(feature,outputs);//get the recognized id
            Password=Integer.parseInt(txtPIN.getText());
            cobj.setID(id);
            cobj.setPassword(Password);
            UserDB cdobj=new UserDB();
            rs=cdobj.getAccountdetails(cobj);
            if(rs.next()==false)
            {
                JOptionPane.showMessageDialog(null,"Unauthorized User - Try again","ERROR",JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                PayRoll_System atmo=new PayRoll_System(id, Password);
                atmo.show(); 
                grabber.stop();
                grabber.close();
                this.hide();
            }
            /*rs2=cdobj.getDetails(cobj);
            while(rs.next())
            {
                acc=rs.getInt(2);
                bal=rs.getFloat(5);
                with=rs.getFloat(6);
            }
            while(rs2.next())
            {
                name=rs2.getString(2);
                address=rs2.getString(3);
                mobile=rs2.getInt(4);
            }*/
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,"cannot retrive values","ERROR",JOptionPane.ERROR_MESSAGE);
            logger.log(Level.WARNING, "Cannot retrive values {0}", e);
        } catch (FrameGrabber.Exception ex) {
            Logger.getLogger(UserVerification.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        btnVerify = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        btnFileimage = new javax.swing.JButton();
        btnGotologin = new javax.swing.JButton();
        txtPIN = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(320, 240));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 468, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 439, Short.MAX_VALUE)
        );

        btnVerify.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnVerify.setIcon(new javax.swing.ImageIcon("C:\\Users\\Shenal_Jerome\\Downloads\\LOGINBUTTON.png")); // NOI18N
        btnVerify.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnVerify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerifyActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UIL/Payroll-Final-Logo-768x242.png"))); // NOI18N

        jToolBar1.setRollover(true);

        btnFileimage.setText("File");
        btnFileimage.setFocusable(false);
        btnFileimage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFileimage.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFileimage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFileimageActionPerformed(evt);
            }
        });
        jToolBar1.add(btnFileimage);

        btnGotologin.setText("Admin");
        btnGotologin.setFocusable(false);
        btnGotologin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGotologin.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGotologin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGotologinActionPerformed(evt);
            }
        });
        jToolBar1.add(btnGotologin);

        txtPIN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPINActionPerformed(evt);
            }
        });
        txtPIN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPINKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Palatino Linotype", 1, 36)); // NOI18N
        jLabel3.setText("LOGIN");

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Shenal_Jerome\\Downloads\\login-gov-600x314.png")); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("ENTER PASSWORD");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 794, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(67, 67, 67)
                                        .addComponent(txtPIN, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(199, 199, 199))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnVerify, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(94, 94, 94)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPIN, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVerify, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerifyActionPerformed

       
      PreProcess pobj=new PreProcess();
        
        if(txtPIN.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Please enter your Password and try again","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            try 
            {
                FaceDetector obj=new FaceDetector();
                captured=bImg;
                detected=obj.detectFace(captured);
                if(detected==null)
                {
                    JOptionPane.showMessageDialog(null,"Please capture again","ERROR",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    hist=pobj.histogramEqualization(detected);
                    try {
                        ImageIO.write(hist,"jpg",(new File("D:\\Final Project\\FaceRecognition_Payroll\\recognized.jpg")));
                    } catch (IOException ex) {
                        Logger.getLogger(UserVerification.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    recognizeFaces(hist);
                }
                
            } 
            catch (HeadlessException e)
            {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }//GEN-LAST:event_btnVerifyActionPerformed

    private void btnFileimageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFileimageActionPerformed

        if(txtPIN.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter The password and try again","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            try 
            {
                openFile();
                hist=ImageIO.read(file);
                recognizeFaces(hist);
            } 
            catch (IOException e) 
            {
                logger.log(Level.WARNING, "Error in reading image{0}", e);
            }
        }
    }//GEN-LAST:event_btnFileimageActionPerformed

    private void btnGotologinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGotologinActionPerformed
        try {
                Login atmo=new Login();
                atmo.show(); 
                grabber.stop();
                grabber.close();
                this.hide();
        } catch (FrameGrabber.Exception ex) {
            Logger.getLogger(UserVerification.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGotologinActionPerformed

    private void txtPINKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPINKeyTyped
        
         char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter) || enter==KeyEvent.VK_BACK_SPACE || enter==KeyEvent.VK_DELETE || enter==KeyEvent.VK_PERIOD))
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtPINKeyTyped

    private void txtPINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPINActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPINActionPerformed

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
            java.util.logging.Logger.getLogger(UserVerification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserVerification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserVerification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserVerification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserVerification().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFileimage;
    private javax.swing.JButton btnGotologin;
    private javax.swing.JButton btnVerify;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPasswordField txtPIN;
    // End of variables declaration//GEN-END:variables
}
