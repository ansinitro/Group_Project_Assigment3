import controllers.AdminController;
import data.PostgresDB;
import data.interfaces.IDB;
import repository.AdminRepository;
import repository.interfaces.IAdminRepository;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB();
        IAdminRepository repo = new AdminRepository(db);
        AdminController controller = new AdminController(repo);
        DormitoryApplication app = new DormitoryApplication(controller);
        app.start();
    }
}