
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
                if(option == 0)
                    break;
                switch (option) {
                    case 1:
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
                        break;
                    case 2:
                        member();
                        break;
                    default:
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
                if(option == 0)
                    break;
                switch (option) {
                    case 1:
                        getAllMembersMenu();
                        break;
                    case 2:
                        getUserByIinMenu();
                        break;
                    case 3:
                        settlementMenu();
                        break;
                    case 4:
                        evictionMenu();
                        break;
                    default:
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
            System.out.println("1. Information about Admin ");
            System.out.println("2. Information about Dormitory");
            System.out.println("0. Exit");
            System.out.println();
            try {
                System.out.print("Enter option (1-3): ");
                int option = scanner.nextInt();
                if(option == 0)
                    break;
                switch (option) {
                    case 1:
                        System.out.println("Name: Angsar\nSurname: Shaumen\nPhone number: +77078839600");
                        break;
                    case 2:
                        System.out.println(dormitoryController.getInformationAboutDormitory());
                    default:
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
        if(response.equals("Member not found"))
            System.err.println(response);
        else
            System.out.println(response);
    }

    public void settlementMenu() {
        String name, surname, phone_number, iin;
        System.out.println("Please enter name:");
        name = scanner.next().trim();
        System.out.println("Please enter surname:");
        surname = scanner.next().trim();
        System.out.println("Please enter phone number like \"+7**********\":");
        while (true) {
            phone_number = scanner.next().trim();
            if(phone_number.length() != 12)
                System.err.println("Phone number must consist 12 character");
            else
                break;
        }
        System.out.println("Please enter iin:");
        while(true){
            iin = scanner.next().trim();
            if(iin.length() != 12)
                System.err.println("IIN must consist 12 character");
            else
                break;
        }

        System.out.println("Choose free apartment");
        for(Apartment a : dormitoryController.getFreeApartments())
            System.out.println(a);
        System.out.println("Please enter apartment number:");
        int apartment = Integer.parseInt(scanner.next().trim());
        System.out.println("Choose free room");
        for (Room r : dormitoryController.getFreeRooms(apartment))
            System.out.println(r);
        System.out.println("Please enter room number:");
        int room = Integer.parseInt(scanner.next().trim());

        boolean response = adminController.settlement(name, surname, phone_number, iin, apartment, room);
        if(response)
            System.out.println("Person was added");
        else
            System.err.println("Something went wrong!!!");
    }

    public void evictionMenu(){
        String iin;
        System.out.println("Input IIN of member: ");
        while(true){
            iin = scanner.next().trim();
            if(iin.length() != 12)
                System.err.println("IIN must consist 12 character");
            else
                break;
        }
        if(adminController.eviction_by_iin(iin))
            System.out.println("Person evicted");
        else
            System.err.println("Something went wrong!!!");
    }
}

