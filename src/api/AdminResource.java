package api;

import model.*;
import service.*;
import java.util.*;

public class AdminResource {
    private static final CustomerService customerService = CustomerService.getInstance();
    private static final ReservationService reservationService = ReservationService.getInstance();

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms){
        for (IRoom room: rooms) {
            reservationService.addRoom(room);
        }
    }

    public static Collection<IRoom> getAllRooms(){
        return reservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    public void displayAllReservations(){
        reservationService.printAllReservation();
    }

    public void addSampleData() {
        customerService.addSampleData();
        reservationService.addSampleData();
    }
}
