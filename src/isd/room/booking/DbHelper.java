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
    
    private Statement checkUserExistence;
    private Statement getBookingList;
    private Statement getRoomList;
    private Statement deleteRooms;
    private Statement deleteBookings;
    private Statement addRoom;
    private Statement addBooking;
    
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
    
    public User userExists(String uid, String pass) throws SQLException{
        ResultSet rs = null;
        User user = null;
        checkUserExistence = conn.createStatement();
        String query = "SELECT * FROM user WHERE uid ='" + uid + "' AND password ='" + pass + "'";
        
        try {
            rs = checkUserExistence.executeQuery(query);
            
            if (rs.next()) {
                user = new User(
                    uid,
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getInt("user_level"));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(DbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            rs.close();
        }
        
        return user;
    }
    
    public ResultSet getBookings(String uid) throws SQLException {
        ResultSet rs = null;
        getBookingList = conn.createStatement();
        String query = "select req_id, name, date, from_time, to_time from room, request "
                + "WHERE room.room_id = request.room_id and uid ='" + uid + "'";
        
        try {
            rs = getBookingList.executeQuery(query);
        }
        catch (SQLException ex) {
            Logger.getLogger(DbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }
    
    public ResultSet getRooms() throws SQLException {
        ResultSet rs = null;
        getRoomList = conn.createStatement();
        String query = "select * FROM room";
        
        try {
            rs = getRoomList.executeQuery(query);
        }
        catch (SQLException ex) {
            Logger.getLogger(DbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }

    public void deleteBooking(int req_id) throws SQLException {
        deleteBookings = conn.createStatement();
        String query = "Delete from request where req_id = " + req_id;
        
        try {
            deleteBookings.executeUpdate(query);
        }
        catch (SQLException ex) {
            Logger.getLogger(DbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 

    public void deleteRoom(String room_id) throws SQLException {
        deleteRooms = conn.createStatement();
        String query = "Delete from room where room_id = " + room_id;
        
        try {
            deleteRooms.executeUpdate(query);
        }
        catch (SQLException ex) {
            Logger.getLogger(DbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addRoom(Room room) throws SQLException {
        addRoom = conn.createStatement();
        String query = "Insert into room values('"
                + room.roomid + "','" + room.name + "," + room.capacity + "','" + room.type + "')";
        
        try {
            addRoom.executeUpdate(query);
        }
        catch (SQLException ex) {
            Logger.getLogger(DbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void addBooking(Booking booking) throws SQLException {
        addBooking = conn.createStatement();
        String query = "Insert into request values('" + 
                booking.req_id + "','" + booking.room.roomid + "','" + booking.user.uid + "','" + 
                booking.date + "','" + booking.from_time + "','" + booking.toString()+"')";
        
        try {
            addBooking.executeQuery(query);
        }
        catch (SQLException ex) {
            Logger.getLogger(DbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
    
    
    
}
