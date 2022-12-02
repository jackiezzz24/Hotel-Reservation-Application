package api;

import model.*;
import service.*;
import java.util.*;

public class AdminResource {
    private static final CustomerService customerService = new CustomerService();
    private static final ReservationService reservationService = new ReservationService();

    public void addRoom(List<IRoom> rooms){
        for (IRoom room: rooms) {
            reservationService.addRoom(room);
        }
    }

    public static Collection<IRoom> getAllRooms(){
        return reservationService.getAllRoom();
    }

    public Collection<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    public void displayAllReservations(){
        reservationService.printAllReservation();
    }

}
