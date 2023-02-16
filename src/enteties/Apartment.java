package enteties;

public class Apartment {
    private int apartment_number;
    private int number_of_rooms;

    public Apartment(int apartment_number, int number_of_rooms){
        this.apartment_number = apartment_number;
        this.number_of_rooms = number_of_rooms;
    }

    public int getApartment_number() {
        return apartment_number;
    }

    public int getNumber_of_rooms() {
        return number_of_rooms;
    }


    @Override
    public String toString() {
        return "Apartment: " + apartment_number + "\nNumber of rooms: " + number_of_rooms;
    }
}
