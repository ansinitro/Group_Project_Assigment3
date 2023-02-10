package controllers;

import repository.Person;
import repository.interfaces.IAdminRepository;

import java.util.List;

public class AdminController {
    private final IAdminRepository repository;

    public AdminController(IAdminRepository repository) {
        this.repository = repository;
    }

    public String createMemeber(String name, String surname, String phone_number, String iin){
        Person person = new Person(name, surname, phone_number, iin);
        boolean created = repository.createPerson(person);

        return (created ? "Member was created" : "Member creation wa failed");
    }

    public String getMemberInfo(String iin){
        Person member = repository.getMemberInfo(iin);

        return(member == null ? "Member not found" : member.toString());
    }

    public List<Person> getAllMembers() {
        List<Person> members = repository.getAllList();
        for (Person p : members) {
            p.toString();
        }
    }
}
