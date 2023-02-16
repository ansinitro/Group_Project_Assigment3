package repository.interfaces;

import enteties.Apartment;
import enteties.Room;

import java.util.List;

public interface IDormitoryRepository {
    List<Apartment> getFreeApartments();
    List<Room> getFreeRooms(int apartment);
    String getInformationAboutDormitory();
}
