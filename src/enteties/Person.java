package enteties;

public abstract class Person {
    private String name;
    private String surname;
    private String phone_number;
    private String iin;

    public Person(String name, String surname, String phone_number, String iin){
        this.name = name;
        this.surname = surname;
        this.phone_number = phone_number;
        this.iin = iin;
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
}
