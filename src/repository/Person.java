package repository;

public class Person {
    private String name;
    private String surname;
    private String phone_number = "no number";
    private String iin;

    public Person(String name, String surname, String iin){
        this.name = name;
        this.surname = surname;
        this.iin = iin;
    }
    public Person(String name, String surname, String phone_number, String iin){
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
        return (surname + ' ' + name + ' ' + phone_number + ' ' + iin);
    }
}
