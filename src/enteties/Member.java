package enteties;

public class Member {
    private String name;
    private String surname;
    private String phone_number;
    private String iin;

    public Member(String name, String surname, String iin){
        this.name = name;
        this.surname = surname;
        this.iin = iin;
    }
    public Member(String name, String surname, String phone_number, String iin){
        this(name, surname, iin);
        this.phone_number = phone_number;
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

    @Override
    public String toString() {
        String s = "Surname: " + surname + "\nName: " + name + "\nPhone number: " + phone_number + "\nIIN:" + iin;
        return s;
    }
}
