package model;

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

    public Customer getCustomer(){
        return customer;
    }
    public IRoom getRoom(){
        return room;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    @Override
    public String toString(){
        return "Customer: " + customer
                + "\nRoom: " + room
                + "\nCheck in: " + checkInDate
                + "\nCheck out: " + checkOutDate;
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
