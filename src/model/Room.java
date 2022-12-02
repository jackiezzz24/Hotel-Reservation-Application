package model;

public class Room implements IRoom{
    private String roomNumber;
    private Double roomPrice;
    private RoomType roomType;

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
        return roomPrice;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
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
