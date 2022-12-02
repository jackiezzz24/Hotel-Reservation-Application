import java.util.*;
import java.util.regex.Pattern;

import api.*;
import model.*;

public class AdminMenu {
    public static AdminResource ar = new AdminResource();
    private static final List<IRoom> roomList = new ArrayList<>();

    public static void main() {
        adminMenu();
        Scanner menuScanner = new Scanner(System.in);
        while (true) {
            try {
                String menuInput = menuScanner.nextLine();
                switch (menuInput) {
                    case "1" -> {
                        Collection<Customer> customerCollection = ar.getAllCustomers();
                        if (customerCollection.isEmpty()) {
                            System.out.println("No customer in the database.");
                        } else {
                            for (Customer cus : customerCollection) {
                                System.out.println(cus.toString() + "\n");
                            }
                        }
                        main();
                    }
                    case "2" -> {
                        Collection<IRoom> roomCollection = ar.getAllRooms();
                        if (roomCollection.isEmpty()) {
                            System.out.println("There is no room in the system.");
                        } else {
                            for (IRoom room : roomCollection) {
                                System.out.println(room.toSting() + "\n");
                            }
                        }
                        main();
                    }
                    case "3" -> {
                        ar.displayAllReservations();
                        main();
                    }
                    case "4" -> {
                        addRoom();
                    }
                    case "5" -> {
                        MainMenu.main();
                    }
                    default -> {
                        System.out.println("Invalid input. Valid selection is 1 to 5. Please try again.");
                        AdminMenu.adminMenu();
                    }
                }
            } catch (Exception ex) {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    public static void adminMenu(){
        System.out.println("");
        System.out.println("Admin Menu");
        System.out.println("--------------------------------------");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
        System.out.println("--------------------------------------");
        System.out.println("Please select a number for the menu option");
    }
    public static void addRoom(){
        while (true) {
            IRoom room;
            String roomNumber = getRoomNumber();
            Double roomPrice = getRoomPrice();
            RoomType roomType = getRoomType();
            room = new Room(roomNumber, roomPrice, roomType);
            roomList.add(room);
            ar.addRoom(roomList);

            try {
                Scanner scannerAddRoom = new Scanner(System.in);
                System.out.println("Would you like to add another room? Y/N");
                String addRoom = scannerAddRoom.nextLine();
                if (addRoom.equalsIgnoreCase("y")) {
                    continue;
                }
                if (addRoom.equalsIgnoreCase("n")) {
                    AdminMenu.adminMenu();
                    break;
                }
            } catch (Exception ex) {
                System.out.println("Please enter Y(Yes) or N(No)");
            }
        }
    }

    private static String getRoomNumber(){
        boolean flag = true;
        String roomNumber = null;
        String roomNumRegex = "([0-9]+)";
        Pattern pattern = Pattern.compile(roomNumRegex);

        while (flag){
            try {
                Scanner roomNumScanner = new Scanner(System.in);
                System.out.println("Enter room number: ");
                roomNumber = roomNumScanner.nextLine();
                if (!pattern.matcher(roomNumber).matches()) {
                    throw new IllegalArgumentException("Invalid entry. Please try again.");
                }
            }
            catch (Exception ex){
                System.out.println("Invalid entry. Please try again.");
                continue;
            }
            flag = false;
        }
        return roomNumber;
    }

    private static Double getRoomPrice(){
        boolean flag = true;
        double roomPrice = 0.0;
        String roomPriceRegex = "([0-9]+)\\.?([0-9]+)?";
        Pattern pattern = Pattern.compile(roomPriceRegex);

        while (flag){
            try {
                Scanner roomPriceScanner = new Scanner(System.in);
                System.out.println("Enter price per night: ");
                roomPrice = roomPriceScanner.nextDouble();
                if (!pattern.matcher(Double.toString(roomPrice)).matches()) {
                    throw new IllegalArgumentException("Invalid entry. Please try again.");
                }
            }
            catch (Exception ex){
                System.out.println("Invalid entry. Please try again.");
                continue;
            }
            flag = false;
        }
        return roomPrice;
    }

    private static RoomType getRoomType(){
        RoomType roomType = RoomType.SINGLE;
        boolean flag = true;
        String userInput = "";
        String roomTypeRegex = "([1-2])";
        Pattern pattern = Pattern.compile(roomTypeRegex);

        while (flag){
            try {
                Scanner roomTypeScanner = new Scanner(System.in);
                System.out.println("Enter room type: 1 for single bed, 2 for double bed");
                userInput = roomTypeScanner.nextLine();
                if (!pattern.matcher(userInput).matches()) {
                    throw new IllegalArgumentException("Please entry 1 for single bed, 2 for double bed.");
                }
            }
            catch (Exception ex){
                System.out.println("Invalid entry. Please try again.");
                continue;
            }
            flag = false;
        }
        if (userInput.equals("2")){
            roomType = RoomType.DOUBLE;
        }
        return roomType;
    }
}
