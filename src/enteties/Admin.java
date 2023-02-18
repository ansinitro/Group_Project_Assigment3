package enteties;

public class Admin extends Person{
    private String  password;
    public Admin(String name, String surname, String phone_number, String iin, String password){
        super(name, surname, phone_number, iin);
        this.password = password;
    }

    public void changePassword(String password){
        this.password = password;
    }

}
