package enteties;

public class Member extends Person {
    private int apartment;
    private int room;

    public Member(String name, String surname, String phone_number, String iin, int apartment, int room) {
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
        return "\nName: " + getName() + "\nSurname: " + getSurname() +
                "\nPhone number: " + getPhone_number() + "\nIIN: " + getIin()
            +"\nApartment: " + apartment + "\nroom: " + room;
    }
}