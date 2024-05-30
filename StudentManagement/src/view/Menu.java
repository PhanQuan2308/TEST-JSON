package view;

import java.util.Scanner;

public class Menu {
    static Scanner sc = new Scanner(System.in);
    public  static  final  StudentManagementSystem studentManagementSystem = new StudentManagementSystem();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("======= Menu ============");
            System.out.println("1. Add new student");
            System.out.println("2. Show all students");
            System.out.println("3. Edit information student");
            System.out.println("4. Search student by id");
            System.out.println("5. Search student by name");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    studentManagementSystem.addStudent(sc);
                    break;
                case 2:
                    studentManagementSystem.displayAllStudents();
                    break;
                case 3:
                    studentManagementSystem.editStudent(sc);
                    break;
                case 4:
                    studentManagementSystem.searchStudentById(sc);
                    break;
                case 5:
                    studentManagementSystem.searchStudentByName(sc);
                    break;
                case 6:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                    break;
            }
        } while (choice != 6);
    }
}
