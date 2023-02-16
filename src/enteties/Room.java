package enteties;

public class Room {
    private int room_number;
    private int capacity;

    public Room(int room_number, int capacity){
        this.room_number = room_number;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRoom_number() {
        return room_number;
    }

    @Override
    public String toString() {
        return "Room: " + room_number + "\nCapacity : " + capacity;
    }
}
