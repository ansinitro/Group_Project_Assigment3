package controllers;

import enteties.Apartment;
import enteties.Room;
import repository.interfaces.IDormitoryRepository;

import java.util.List;

public class DormitoryController {
    private final IDormitoryRepository repository;

    public DormitoryController(IDormitoryRepository repository) {
        this.repository = repository;
    }

    public List<Apartment> getFreeApartments(){
        return repository.getFreeApartments();
    }

    public List<Room> getFreeRooms(int apartment){
        return repository.getFreeRooms(apartment);
    }

    public String getInformationAboutDormitory(){
        return repository.getInformationAboutDormitory();
    }
}
