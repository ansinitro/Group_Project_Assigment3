import Rooms.LivingRoom;
import Rooms.Room;

public class Main {
    public static void main(String[] args) {
        Room living = new LivingRoom(50, 3, 10);
        System.out.println(living.getSquare());
        living.switchOffLight();

    }
}