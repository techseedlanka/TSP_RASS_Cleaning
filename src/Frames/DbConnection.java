package Frames;

import java.sql.Connection;
import java.sql.DriverManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sapu
 */
//192.168.1.200-Target Server  
//localhost
public class DbConnection {

    static Connection getconnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");

        //Connection c = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/techseed_payroll", "root", "123");
        //("jdbc:mysql://localhost:3306/techseed_payroll", "root", "123")
        //JOptionPane.showMessageDialog(null,"DB Connectted");
        String url = "jdbc:mysql://localhost:3306/techseed_payroll_rass_target?useUnicode=true&characterEncoding=UTF-8";
        Connection c = DriverManager.getConnection(url, "root", "123");
        return c;
 
        //*******************************************************************************************
        // #To connect to DB within Server (another PC) Use following code line
        //("jdbc:mysql://192.168.1.10:3306/pos", "user", "123")
        //("DriverType://ServerIP:SQLPortNumber/Database","UserName","Password")
        // # If there are multiple computers create Users in SQL server for each computers IP
        // eg: if computer's IP is 192.168.100.100 then Create a User in SQl :-
        // User Name: "user"(as for the following code)
        // Password: "123"
        // Host:"192.168.100.100"
        //********************************************************************************************
        //********************************************************
        // TYPE & SAVE DATA IN SINHALA
        // Use following code as connection
        // String url = "jdbc:mysql://localhost:3306/techseed_payroll?useUnicode=true&characterEncoding=UTF-8";//getting url
        // Connection c = DriverManager.getConnection(url, "root", "123");//create connection
        //*******************************************************
    }

}
