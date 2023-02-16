import controllers.AdminController;
import controllers.DormitoryController;
import data.PostgresDB;
import data.interfaces.IDB;
import repository.AdminRepository;
import repository.DormitoryRepository;
import repository.interfaces.IAdminRepository;
import repository.interfaces.IDormitoryRepository;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB();
        IAdminRepository adminRepository = new AdminRepository(db);
        IDormitoryRepository dormitoryRepository = new DormitoryRepository(db);
        AdminController adminController = new AdminController(adminRepository);
        DormitoryController dormitoryController = new DormitoryController(dormitoryRepository);
        DormitoryApplication app = new DormitoryApplication(adminController, dormitoryController);
        app.start();
    }
}