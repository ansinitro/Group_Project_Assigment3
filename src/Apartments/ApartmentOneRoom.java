package Apartments;

import Rooms.Bathroom;
import Rooms.Kitchen;
import Rooms.LivingRoom;
import Rooms.Toilet;

public class ApartmentOneRoom {
    ApartmentOneRoom(){
        LivingRoom l = new LivingRoom(25, 3);
        Kitchen kitchen = new Kitchen(15, 3);
        Toilet toilet = new Toilet(5, 3);
        Bathroom bathroom = new Bathroom(10, 3);
    }

}
