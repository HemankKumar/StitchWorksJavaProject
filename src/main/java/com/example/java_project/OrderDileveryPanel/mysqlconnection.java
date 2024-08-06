package com.example.java_project.OrderDileveryPanel;

import java.sql.Connection;
import java.sql.DriverManager;

public class mysqlconnection {
    public static Connection doConnect(){
        Connection con=null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost/java_project_db","root","123456");

        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        return con;
    }

    public static void main(String args[]){
        if(doConnect()==null){
            System.out.println("Sorry");
        }
        else{
            System.out.println("connection established!!");
        }
    }
}
