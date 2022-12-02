import api.*;
import model.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainMenu {
    public static HotelResource hr = new HotelResource();
    private static final Scanner scanner = new Scanner(System.in);
    private static Collection<IRoom> availableRoom = new HashSet<>();

    public static void main() {
        Scanner scanner = new Scanner(System.in);
        mainMenu();
        while (true) {
            try {
                String userInput = scanner.nextLine();
                    switch (userInput) {
                        case "1" -> {
                            findAndReserve();
                        }
                        case "2" -> {
                            Scanner scannerEmail = new Scanner(System.in);
                            System.out.println("Enter your email address: ");
                            String email = scannerEmail.nextLine();
                            printCusReservation(hr.getCustomersReservations(email));
                            mainMenu();
                        }
                        case "3" -> {
                            Scanner scannerAcct = new Scanner(System.in);
                            System.out.println("Enter your email address: ");
                            String email = scannerAcct.nextLine();
                            System.out.println("Enter your first name: ");
                            String firstName = scannerAcct.nextLine();
                            System.out.println("Enter your last name: ");
                            String lastName = scannerAcct.nextLine();
                            hr.createACustomer(email, firstName, lastName);
                            mainMenu();
                        }
                        case "4" -> {
                            AdminMenu.main();
                        }
                        case "5" -> {
                            System.out.println("Exiting...");
                            System.exit(0);
                        }
                        default -> System.out.println("Invalid input. Valid selection is 1 to 5. Please try again.");
                    }
                } catch(Exception ex) {
                    System.out.println("Invalid input. Please try again.");
                }
        }
    }

    public static void mainMenu(){
        System.out.println("");
        System.out.println("Welcome to the Hotel Reservation Application\n");
        System.out.println("--------------------------------------");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("--------------------------------------");
        System.out.println("Please select a number for the menu option");
    }
    private static void findAndReserve(){
        Scanner scannerDate = new Scanner(System.in);
        System.out.println("Enter check in date: mm-dd-yyyy");
        String checkInDate = scannerDate.nextLine();
        Date checkIn = null;
        try {
            checkIn = new SimpleDateFormat("MM-dd-yyyy").parse(checkInDate);
        } catch(Exception ex) {
           System.out.println("Invalid Input");
            main();
        }
        System.out.println("Enter check out date: mm-dd-yyyy");
        String checkOutDate = scannerDate.nextLine();
        Date checkOut = null;
        try {
            checkOut = new SimpleDateFormat("MM-dd-yyyy").parse(checkOutDate);
        } catch(Exception ex) {
            System.out.println("Invalid Input");
            main();
        }

        if (checkIn != null && checkOut != null) {
            availableRoom = hr.findARoom(checkIn, checkOut);
        }

        if (availableRoom.isEmpty()){
            Collection<IRoom> recommendRoom = hr.recommendRoom(checkIn, checkOut);
            if (recommendRoom.isEmpty()){
                System.out.println("No room is available now.");
                main();
            }
            else{
                Date newCheckInDate = hr.alternativeDate(checkIn);
                Date newCheckOutDate = hr.alternativeDate(checkOut);

                System.out.println("Sorry, there is no room available during the selected period.");
                System.out.println("You may choose alternative date range from" +
                        newCheckInDate + " to " + newCheckOutDate);

                printRooms(recommendRoom);
                reserveRoom(scanner, newCheckInDate, newCheckOutDate,recommendRoom);
            }
        }
        else{
            printRooms(availableRoom);
            reserveRoom(scanner, checkIn, checkOut, availableRoom);
        }
    }

    private static void printRooms(Collection<IRoom> rooms){
        for (IRoom room: rooms){
            System.out.println(room);
        }
    }

    private static void printCusReservation(Collection<Reservation> reservations){
        if (reservations == null || reservations.isEmpty()) {
            System.out.println("There is no reservation in our database.");
        }
        else {
            for (Reservation cusReservation : reservations) {
                System.out.println(cusReservation + "\n");
            }
        }
    }

    private static void reserveRoom(Scanner scanner, Date checkIn, Date checkOut, Collection<IRoom> rooms){
        System.out.println("Would you like to book a room? Y/N");
        String bookInput = scanner.nextLine();
        if (bookInput.equalsIgnoreCase("y")){
            System.out.println("Do you have an account with us? Y/N");
            String acctInput = scanner.nextLine();
            if (acctInput.equalsIgnoreCase("y")){
                System.out.println("Enter Email format: name@doman.com");
                String emailInput = scanner.nextLine();
                if (hr.getCustomer(emailInput) == null){
                    System.out.println("You don't have an account with us.");
                    main();
                }
                else{
                    System.out.println("What room number would you like to reserve?");
                    String roomInput = scanner.nextLine();
                    IRoom room = hr.getRoom(roomInput);
                    Reservation reservation = hr.bookARoom(emailInput, room, checkIn, checkOut);
                    System.out.println(reservation);
                }
                main();
            }
            else {
                System.out.println("Please create an account with us first.");
                main();
            }
        }
        else if (bookInput.equalsIgnoreCase("n")){
            main();
        }
        else{
            reserveRoom(scanner, checkIn, checkOut, rooms);
        }
    }
}
