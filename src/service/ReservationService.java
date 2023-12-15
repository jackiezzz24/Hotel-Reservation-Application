package service;

import model.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class ReservationService {
    private final Map<String, IRoom> roomDatabase = new HashMap<>();
    private final Map<String, Collection<Reservation>> allCustomerReservation = new HashMap<>();
    private final Set<Reservation> allReservation = new HashSet<>();

    private static ReservationService instance;

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }
        return instance;
    }

    public void addRoom(IRoom room){
        if (!roomDatabase.containsKey(room.getRoomNumber())) {
            roomDatabase.put(room.getRoomNumber(), room);
        } else {
            System.out.println("This room number already exist in the database.");
        }
    }

    public IRoom getARoom(String roomId){
        return roomDatabase.get(roomId);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        Collection<Reservation> cusReservation = getCustomerReservation(customer);

        if (cusReservation == null){
            cusReservation = new HashSet<>();
        }
        cusReservation.add(reservation);
        allCustomerReservation.put(customer.getEmail(), cusReservation);
        allReservation.add(reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Collection<String> bookedRooms = new HashSet<>();
        Collection<IRoom> availableRooms = new HashSet<>();
        for (Reservation reservation : allReservation) {
            if (checkInDate.before(reservation.getCheckOutDate()) || checkOutDate.after(reservation.getCheckInDate())) {
                bookedRooms.add(reservation.getRoom().getRoomNumber());
            }
        }
        for (String roomNumber: roomDatabase.keySet()) {
            if (!bookedRooms.contains(roomNumber)) {
                availableRooms.add(roomDatabase.get(roomNumber));
            }
        }
        return availableRooms;
    }

    public Collection<Reservation> getCustomerReservation(Customer customer){
        return allCustomerReservation.get(customer.getEmail());
    }

    public void printAllReservation(){
        if (allReservation.isEmpty()) {
            System.out.println("There is no reservation in our database.");
        }
        else {
            for (Reservation reservation : allReservation) {
                System.out.println(reservation + "\n");
            }
        }
    }

    public Collection<IRoom> getAllRooms() {
        return new HashSet<>(roomDatabase.values());
    }

//    public Collection<IRoom> recommendRooms(Date checkInDate, Date checkOutDate){
//        Date newCheckInDate = datePlusDuration(checkInDate);
//        Date newCheckOutDate = datePlusDuration(checkOutDate);
//        return findRooms(newCheckInDate, newCheckOutDate);
//    }
//
//    public Date datePlusDuration(Date date){
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.add(Calendar.DATE, 7);
//        return calendar.getTime();
//    }

    public void addSampleData() {
        IRoom room1 = new Room("101", 135.0, RoomType.SINGLE);
        IRoom room2 = new Room("102", 225.0, RoomType.DOUBLE);
        IRoom room3 = new FreeRoom("103", RoomType.SINGLE);
        roomDatabase.put(room1.getRoomNumber(), room1);
        roomDatabase.put(room2.getRoomNumber(), room2);
        roomDatabase.put(room3.getRoomNumber(), room3);
    }
}
