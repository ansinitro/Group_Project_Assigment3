package notmatter.Apartments;

import notmatter.Apartments.Rooms.Bathroom;
import notmatter.Apartments.Rooms.Kitchen;
import notmatter.Apartments.Rooms.LivingRoom;
import notmatter.Apartments.Rooms.Toilet;

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
