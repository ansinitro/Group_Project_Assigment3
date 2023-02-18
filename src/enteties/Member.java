package enteties;

public class Member extends Person {
    private int apartment;
    private int room;

    public Member(String name, String surname, String phone_number, String iin, int apartment, int room){
        super(name, surname, phone_number, iin);
        this.apartment = apartment;
        this.room = room;
    }

    public int getApartment() {
        return apartment;
    }

    public int getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "Apartment: " + apartment + "\nroom: " + room +
                "\nSurname: " + getSurname() + "\nName: " + getName() +
                "\nPhone number: " + getPhone_number() + "\nIIN: " + getIin();
    }
}
