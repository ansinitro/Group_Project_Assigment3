import controllers.AdminController;
import enteties.Member;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DormitoryApplication {
    private final AdminController controller;
    private final Scanner scanner;

    public DormitoryApplication(AdminController controller) {
        this.controller = controller;
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println();
            System.out.println("Choose who are you?");
            System.out.println("Select option:");
            System.out.println("1. Admin");
            System.out.println("2. Member");
            try {
                System.out.print("Enter option (1-2): ");
                int option = scanner.nextInt();
                if (option == 1) {
                    System.out.println("Enter a password");
                    String password = scanner.nextLine();
                    while (true) {
                        if (password.equals("7777")) {
                            admin();
                            break;
                        } else System.out.println("Try again");
                    }
                } else if (option == 2) {
                    //member();
                } else {
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Input must be integer");
                scanner.nextLine(); // to ignore incorrect input
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("*************************");

        }
    public void admin() {
        while (true) {
            System.out.println();
            System.out.println("Welcome Admin");
            System.out.println("Select option:");
            System.out.println("1. Get all users");
            System.out.println("2. Get user by iin");
            System.out.println("3. Create user");
            System.out.println("0. Exit");
            System.out.println();
            try {
                System.out.print("Enter option (1-3): ");
                int option = scanner.nextInt();
                if (option == 1) {
                    getAllUsersMenu();
                } else if (option == 2) {
                    getUserByIinMenu();
                } else if (option == 3) {
                    createUserMenu();
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be integer");
                scanner.nextLine(); // to ignore incorrect input
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("*************************");
        }
    }

        public void getAllUsersMenu () {
            List<Member> response = controller.getAllMembers();
            for (Member p : response) {
                System.out.println(p);
                System.out.println();
            }
        }

        public void getUserByIinMenu () {
            System.out.println("Please enter iin");

            String iin = scanner.next();
            String response = controller.getMemberInfo(iin);
            System.out.println(response);
        }

        public void createUserMenu () {
            System.out.println("Please enter name");
            String name = scanner.next().trim();
            System.out.println("Please enter surname");
            String surname = scanner.next().trim();
            System.out.println("Please enter phone number \"+7...\" (otherwise enter 0)");
            String phone_number = scanner.next().trim();
            if (phone_number == "0") phone_number = null;
            System.out.println("Please enter iin");
            String iin = scanner.next().trim();

            String response = controller.createMember(name, surname, phone_number, iin);
            System.out.println(response);
        }
    }
}