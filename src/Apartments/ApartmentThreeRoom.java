package Apartments;

import Rooms.Bathroom;
import Rooms.Kitchen;
import Rooms.LivingRoom;
import Rooms.Toilet;

import javax.swing.text.LabelView;

public class ApartmentThreeRoom {
    ApartmentThreeRoom(){
        LivingRoom l1 = new LivingRoom(100, 3);
        LivingRoom l2 = new LivingRoom(50, 3);
        LivingRoom l3 = new LivingRoom(50,3);
        Kitchen kitchen = new Kitchen(25, 3);
        Toilet toilet = new Toilet(5, 3);
        Bathroom bathroom = new Bathroom(10, 3);
    }
}
