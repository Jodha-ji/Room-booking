/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isd.room.booking;

/**
 *
 * @author user
 */
public class ISDRoomBooking {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       DbHelper db = new DbHelper();
       LogIn login = new LogIn();
       login.setVisible(true);
    }
    
}
