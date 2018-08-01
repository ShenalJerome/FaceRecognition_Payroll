/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DBL;

import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author User
 */
public class UserDB {
    
    public int addAccountants(Users obj)
    {
        int row=0;
        try
        {
            String SQL="INSERT INTO accountants VALUES(null,'"+obj.getName()+"','"+obj.getAddress()+"',"
                    + "'"+obj.getMobile()+"','"+obj.getEmployeeID()+"','"+obj.getPassword()+"','"+obj.getImg1()+"','"+obj.getImg2()+"','"+obj.getImg3()+"')";
            DBConnection mycon=new DBConnection();
            row=mycon.addValues(SQL);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return row;
    }
    
   
    public ResultSet getDetails(Users obj)
    {
        ResultSet rs=null;
        try
        {
            String SQL="select*from accountants where ID="+obj.getID()+"";
            DBConnection mycon=new DBConnection();
            rs=mycon.getValues(SQL);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return rs;
    }
    
  
     public ResultSet getAccountdetails(Users obj)
    {
        ResultSet rs=null;
        try
        {
            String SQL="select*from accountants where ID="+obj.getID()+" and Password="+obj.getPassword()+"";
            DBConnection mycon=new DBConnection();
            rs=mycon.getValues(SQL);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return rs;
    }

   
    public ResultSet getAlldetails()
    {
        ResultSet rs=null;
        try
        {
            String SQL="select*from accountants";
            DBConnection mycon=new DBConnection();
            rs=mycon.getValues(SQL);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return rs;
    }
    
    public DefaultTableModel accountantsTable()
    {
        DefaultTableModel objtable=new DefaultTableModel();
        try
        {
            String SQL="select*from accountants";
            DBConnection mycon=new DBConnection();
            objtable=mycon.getTables(SQL);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error connecting database  "+e);
        }
        return objtable;
    }
    
    
    
    public int updateAccountants(Users obj)
    {
        int row=0;
        try
        {
            String SQL="update accountants set AName='"+obj.getName()+"',AAddress='"+obj.getAddress()+"',Mobile='"+obj.getMobile()+"',EmpID='"+obj.getEmployeeID()+"'"
                    + "Password="+obj.getPassword()+" where ID="+obj.getID()+"";
            DBConnection mycon=new DBConnection();
            row=mycon.addValues(SQL);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return row;
    }
    
    
}
