/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isd.room.booking;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public final class DbHelper {
    private Connection conn;
    private static final String URL = "jdbc:mysql://localhost:3306/room_booking";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "qwerty";
    
    public DbHelper() {
        connect();
    }
    
    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected to MySQL server.");
        }
        catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error in connecting to MySQL server!");
            Logger.getLogger(DbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
