package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Reservation {
    private final Customer customer;
    private final IRoom room;
    private final Date checkInDate;
    private final Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public IRoom getRoom(){
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    @Override
    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy");
        return  customer + "\n" + room
                + "\nCheck in Date: " + sdf.format(checkInDate)
                + "\nCheck out Date: " + sdf.format(checkOutDate);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Reservation reservation = (Reservation) obj;
        return Objects.equals(this.getRoom(), reservation.getRoom());
    }
    @Override
    public int hashCode() {
        return Objects.hash(customer, room, checkInDate, checkOutDate);
    }

}
