package repository.interfaces;

import repository.Person;

import java.util.List;

public interface IAdminRepository {
    boolean createPerson(Person person);
    Person getMemberInfo(String iin);
    List<Person> getAllList();
}
