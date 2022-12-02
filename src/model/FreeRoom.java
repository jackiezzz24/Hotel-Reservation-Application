package model;

public class FreeRoom extends Room{

    public FreeRoom(String roomNumber, RoomType roomType) {
        super(roomNumber, 0.0, roomType);
    }

    @Override
    public String toString(){
        return super.toString() + "This is a free room.";
    }

    @Override
    public boolean isFree() {
        return true;
    }
}
