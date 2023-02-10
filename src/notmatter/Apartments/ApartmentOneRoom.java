package notmatter.Apartments;

import notmatter.Apartments.Rooms.Bathroom;
import notmatter.Apartments.Rooms.Kitchen;
import notmatter.Apartments.Rooms.LivingRoom;
import notmatter.Apartments.Rooms.Toilet;

public class ApartmentOneRoom {
    ApartmentOneRoom(){
        LivingRoom l = new LivingRoom(25, 3);
        Kitchen kitchen = new Kitchen(15, 3);
        Toilet toilet = new Toilet(5, 3);
        Bathroom bathroom = new Bathroom(10, 3);
    }

}
