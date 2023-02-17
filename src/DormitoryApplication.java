
import controllers.AdminController;
import controllers.DormitoryController;
import enteties.Apartment;
import enteties.Member;
import enteties.Room;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DormitoryApplication {
    private final AdminController adminController;
    private final DormitoryController dormitoryController;
    private final Scanner scanner;

    public DormitoryApplication(AdminController adminController, DormitoryController dormitoryController) {
        this.adminController = adminController;
        this.dormitoryController = dormitoryController;
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
                    while (true) {
                        System.out.println("Enter a password (for exit enter 0)");
                        String password = scanner.next();
                        if (password.equals("0"))
                            break;
                        if (password.equals("7777")) {
                            admin();
                            break;
                        } else
                            System.out.println("Try again\n");
                    }
                } else if (option == 2) {
                    member();
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




    public void admin() {
        while (true) {
            System.out.println();
            System.out.println("Welcome Admin");
            System.out.println("Select option:");
            System.out.println("1. Get all members");
            System.out.println("2. Get member by iin");
            System.out.println("3. Settlement Person");
            System.out.println("4. Eviction Person by iin");
            System.out.println("0. Exit");
            System.out.println();
            try {
                System.out.print("Enter option (1-4): ");
                int option = scanner.nextInt();
                if (option == 1) {
                    getAllMembersMenu();
                } else if (option == 2) {
                    getUserByIinMenu();
                } else if (option == 3) {
                    settlementMenu();
                } else if (option == 4){
                    evictionMenu();
                }else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be integer");
                scanner.nextLine(); // to ignore incorrect input
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("*************************");

        }
    }

    public void member(){
        while (true) {
            System.out.println();
            System.out.println("Welcome to Dormitory");
            System.out.println("Select option:");
            System.out.println("1. Admin name and surname");
            System.out.println("2. Admin phone number");
            System.out.println("3. Graphic of Dormitory");
            System.out.println("0. Exit");
            System.out.println();
            try {
                System.out.print("Enter option (1-3): ");
                int option = scanner.nextInt();
                if (option == 1) {
                    System.out.println("Shaumen Angsar");
                } else if (option == 2) {
                    System.out.println("+7 (707) 883 9600");
                } else if (option == 3) {
                    System.out.println(dormitoryController.getInformationAboutDormitory());
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be integer");
                scanner.nextLine(); // to ignore incorrect input
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("*************************");

        }
    }

    public void getAllMembersMenu() {
        List<Member> response = adminController.getAllMembers();
        for(Member m : response){
            System.out.println(m);
            System.out.println();
        }
    }

    public void getUserByIinMenu() {
        System.out.println("Please enter iin");

        String iin = scanner.next();
        String response = adminController.getMemberInfo(iin);
        System.out.println(response);
    }

    public void settlementMenu() {
        String name, surname, phone_number, iin;
        System.out.println("Please enter name");
        name = scanner.next().trim();
        System.out.println("Please enter surname");
        surname = scanner.next().trim();
        System.out.println("Please enter phone number like \"+7**********\"");
        while (true) {
            phone_number = scanner.next().trim();
            if(phone_number.length() != 12)
                System.out.println("Phone number must consist 12 character");
            else
                break;
        }
        System.out.println("Please enter iin");
        while(true){
            iin = scanner.next().trim();
            if(iin.length() != 12)
                System.out.println("IIN must consist 12 character");
            else
                break;
        }

        System.out.println("Choose free apartment:");
        for(Apartment a : dormitoryController.getFreeApartments())
            System.out.println(a);
        System.out.println("Please enter apartment number");
        int apartment = Integer.parseInt(scanner.next().trim());
        System.out.println("Choose free room:");
        for (Room r : dormitoryController.getFreeRooms(apartment))
            System.out.println(r);
        System.out.println("Please enter room number");
        int room = Integer.parseInt(scanner.next().trim());

        boolean response = adminController.settlement(name, surname, phone_number, iin, apartment, room);
        if(response)
            System.out.println("Person was added");
        else
            System.err.println("Something went wrong!!!");
    }

    public void evictionMenu(){

    }

}

