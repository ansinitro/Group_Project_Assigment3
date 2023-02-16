package enteties;

public class Member {
    private String name;
    private String surname;
    private String phone_number;
    private String iin;
    private int apartment;
    private int room;

    public Member(String name, String surname, String phone_number, String iin){
        this.name = name;
        this.surname = surname;
        this.phone_number = phone_number;
        this.iin = iin;
    }

    public Member(String name, String surname, String phone_number, String iin, int apartment, int room){
        this(name, surname, phone_number, iin);
        this.apartment = apartment;
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public String getIin() {
        return iin;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getSurname() {
        return surname;
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
                "\nSurname: " + surname + "\nName: " + name +
                "\nPhone number: " + phone_number + "\nIIN: " + iin;
    }
}
