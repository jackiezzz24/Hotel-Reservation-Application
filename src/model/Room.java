package model;

public class Room implements IRoom{
    private final String roomNumber;
    private final Double roomPrice;
    private final RoomType roomType;

    public Room(String roomNumber, Double roomPrice, RoomType roomType){
        super();
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.roomType = roomType;
    }

    @Override
    public String toSting(){
        return "Room Number: " + roomNumber
                + "\nPrice: " + roomPrice
                + "\nRoom Type: " + roomType;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return null;
    }

    @Override
    public RoomType getRoomType() {
        return null;
    }


    @Override
    public boolean isFree(){
        return (roomPrice==0);
    }

    @Override
    public String toString(){
        return "Room number: " + roomNumber
                + " Price per night: " + roomPrice
                + " Room Type: " + roomType;
    }
}
