package service;

import model.*;
import java.util.*;
import java.util.stream.Collectors;

public class ReservationService {
    private final Map<String, IRoom> mapOfRooms = new HashMap<>();
    private final Map<String, Collection<Reservation>> mapOfReservation = new HashMap<>();
    private final Collection<Reservation> reservationSet = new HashSet<>();

    public void addRoom(IRoom room){
        mapOfRooms.put(room.getRoomNumber(), room);
    }

    public IRoom getRoom(String roomNumber){
        return mapOfRooms.get(roomNumber);
    }

    public Collection<IRoom> getAllRoom(){
        return mapOfRooms.values();
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        Collection<Reservation> cusReservation = getCustomerReservation(customer);

        if (cusReservation == null){
            cusReservation = new HashSet<>();
        }
        cusReservation.add(reservation);
        mapOfReservation.put(customer.getEmail(), cusReservation);
        reservationSet.add(reservation);
        return reservation;
    }

    public Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate){
        return findRooms(checkInDate, checkOutDate);
    }

    Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Collection<Reservation> allReservation = getAllReservation();
        Collection<IRoom> bookedRooms = new HashSet<>();
        for (Reservation reservation : allReservation) {
            if (checkInDate.before(reservation.getCheckOutDate())
                    && checkOutDate.after(reservation.getCheckInDate())) {
                bookedRooms.add(reservation.getRoom());
            }
        }
        return mapOfRooms.values().stream().filter(room -> bookedRooms.stream()
                        .noneMatch(bookedRoom -> bookedRoom.equals(room)))
                .collect(Collectors.toList());
            }

    public Collection<IRoom> recommendRoom(Date checkInDate, Date checkOutDate){
        Date newCheckInDate = alternativeDate(checkInDate);
        Date newCheckOutDate = alternativeDate(checkOutDate);
        return findRooms(newCheckInDate, newCheckOutDate);
    }

    public Date alternativeDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 7);
        return calendar.getTime();
    }

    public Collection<Reservation> getCustomerReservation(Customer customer){
        return mapOfReservation.get(customer.getEmail());
    }

    public void printAllReservation(){
        if (reservationSet.isEmpty()) {
            System.out.println("There is no reservation in our database.");
        }
        else {
            for (Reservation reservation : reservationSet) {
                System.out.println(reservation + "\n");
            }
        }
    }

    public Collection<Reservation> getAllReservation(){
        Collection<Reservation> reservationsSet = new LinkedList<>();
        for (Collection<Reservation> reservation: mapOfReservation.values()) {
            reservationsSet.addAll(reservation);
        }
        return reservationsSet;
    }


}
