package model;

public interface IRoom {
    String toSting();

    public String getRoomNumber();

    public Double getRoomPrice();

    public RoomType getRoomType();

    public boolean isFree();
}

