/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isd.room.booking;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author user
 */
public class Booking {
    int req_id;
    Room room;
    User user;
    Date date;
    Time from_time;
    Time to_time;

    public Booking(int req_id, Room room, User user, Date date, Time from_time, Time to_time) {
        this.req_id = req_id;
        this.room = room;
        this.user = user;
        this.date = date;
        this.from_time = from_time;
        this.to_time = to_time;
    }

    public int getReq_id() {
        return req_id;
    }

    public Room getRoom() {
        return room;
    }

    public User getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }

    public Time getFrom_time() {
        return from_time;
    }

    public Time getTo_time() {
        return to_time;
    }

    
    
    
}
