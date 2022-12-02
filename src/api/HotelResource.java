package api;

import model.*;
import service.*;
import java.util.*;

public class HotelResource {
    private static final HotelResource hr = new HotelResource();
    private static final CustomerService customerService = CustomerService.getInstance();
    private static final ReservationService reservationService = ReservationService.getInstance();

    public static HotelResource getInstance(){
        return hr;
    }

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName){
        customerService.addCustomer(email, firstName,lastName);
    }

    public IRoom getRoom(String roomNumber){
        return reservationService.getRoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        try {
            Customer customer = getCustomer(customerEmail);
        }
        catch (IllegalArgumentException ex){
            System.out.println("This email address is not in our database.");
        }
        return reservationService.reserveARoom(getCustomer(customerEmail), room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail){
        Customer customer = getCustomer(customerEmail);
        if (customer == null) {
            return Collections.emptyList();
        }
        return reservationService.getCustomerReservation(customer);
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut){
        return reservationService.findARoom(checkIn, checkOut);
    }

    public Collection<IRoom> recommendRoom(Date checkIn, Date checkOut){
        return reservationService.recommendRoom(checkIn, checkOut);
    }

    public Date alternativeDate(Date date){
        return reservationService.alternativeDate(date);
    }
}
