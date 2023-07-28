/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class DBUtility implements Serializable{
    public static Connection makeConnection (){
        try{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=SWP391";
        Connection con = DriverManager.getConnection(url,"sa","12345");
        return con;
        }
        catch(ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
        return null;
        }
    }

