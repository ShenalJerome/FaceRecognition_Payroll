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
public class AdminDB {
    
    public ResultSet login(Admin obj)//login method
    {
        ResultSet rs=null;
        try
        {
            String SQL="select*from logindetails where userID='"+obj.getUsername()+"' and password='"+obj.getPassword()+"'";
            DBConnection mycon=new DBConnection();
            rs=mycon.getValues(SQL);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return rs;
    }
    
    public int addUser(Admin obj)
    {
        int row=0;
        try
        {
            String SQL="insert into logindetails values(null,'"+obj.getUsername()+"','"+obj.getPassword()+"')";
            DBConnection mycon=new DBConnection();
            row=mycon.addValues(SQL);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return row;
    }
    
    public DefaultTableModel adminTable()/*method to view the book table*/
    {
        DefaultTableModel objtable=new DefaultTableModel();
        try
        {
            String SQL="select*from logindetails";
            DBConnection mycon=new DBConnection();
            objtable=mycon.getTables(SQL);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return objtable;
    }
    
    
    public ResultSet getAlldetails()
    {
        ResultSet rs=null;
        try
        {
            String SQL="select*from logindetails";
            DBConnection mycon=new DBConnection();
            rs=mycon.getValues(SQL);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return rs;
    }
    
}
