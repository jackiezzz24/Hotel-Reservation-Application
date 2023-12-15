package api;

import model.*;
import service.*;
import java.util.*;

public class HotelResource {
    private static final CustomerService customerService = CustomerService.getInstance();
    private static final ReservationService reservationService = ReservationService.getInstance();

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName){
        customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber){
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer customer = null;
        try {
            customer = getCustomer(customerEmail);
        } catch (IllegalArgumentException ex) {
            System.out.println("This email address is not in our database.");
        }
        return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail){
        Customer customer = getCustomer(customerEmail);
        if (customer == null) {
            return Collections.emptyList();
        }
        return reservationService.getCustomerReservation(customer);
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut){
        return reservationService.findRooms(checkIn, checkOut);
    }

//    public Collection<IRoom> recommendRooms(Date checkIn, Date checkOut){
//        return reservationService.recommendRooms(checkIn, checkOut);
//    }
//
//    public Date datePlusDuration(Date date){
//        return reservationService.datePlusDuration(date);
//    }
}
