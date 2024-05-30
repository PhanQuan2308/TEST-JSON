package view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Student;
import entity.StudentList;
import entity.Subject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StudentManagementSystem {
    private static final String FILE_PATH = "Student.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void displayAllStudents() {
        try (FileReader fileReader = new FileReader(FILE_PATH)) {
            StudentList studentList = gson.fromJson(fileReader, StudentList.class);
            String json = gson.toJson(studentList);
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void addStudent(Scanner scanner) {
        try (FileReader fileReader = new FileReader(FILE_PATH)) {
            StudentList studentList = gson.fromJson(fileReader, StudentList.class);

            Student student = new Student();
            System.out.print("Enter student ID: ");
            student.setId(scanner.nextInt());
            scanner.nextLine(); // Consume newline

            System.out.print("Enter student name: ");
            student.setName(scanner.nextLine());

            System.out.print("Enter number of subjects: ");
            int numSubjects = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            for (int i = 0; i < numSubjects; i++) {
                System.out.print("Enter subject name: ");
                String subjectName = scanner.nextLine();

                System.out.print("Enter subject score: ");
                double score = scanner.nextDouble();
                scanner.nextLine(); // Consume newline

                student.addSubject(subjectName, score);
            }

            studentList.addStudent(student);

            try (FileWriter fileWriter = new FileWriter(FILE_PATH)) {
                gson.toJson(studentList, fileWriter);
            }

            System.out.println("Student added successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void editStudent(Scanner scanner) {
        try (FileReader fileReader = new FileReader(FILE_PATH)) {
            StudentList studentList = gson.fromJson(fileReader, StudentList.class);

            System.out.print("Enter student ID to edit: ");
            int idToEdit = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            boolean found = false;
            for (Student student : studentList.getStudents()) {
                if (student.getId() == idToEdit) {
                    System.out.println("Editing information for student with ID " + idToEdit + ":");
                    System.out.print("Enter new name for student: ");
                    String newName = scanner.nextLine();
                    student.setName(newName);

                    System.out.print("Do you want to add (1), delete (2), or edit (3) subjects and scores? Choose: ");
                    int option = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (option) {
                        case 1:
                            System.out.print("Enter new subject name: ");
                            String newSubjectName = scanner.nextLine();
                            System.out.print("Enter score: ");
                            double newScore = scanner.nextDouble();
                            scanner.nextLine(); // Consume newline
                            student.addSubject(newSubjectName, newScore);
                            break;
                        case 2:
                            System.out.print("Enter subject to delete: ");
                            String subjectToDelete = scanner.nextLine();
                            student.removeSubject(subjectToDelete);
                            break;
                        case 3:
                            System.out.print("Enter subject to edit: ");
                            String subjectToEdit = scanner.nextLine();
                            System.out.print("Enter new score for subject: ");
                            double newScoreForEdit = scanner.nextDouble();
                            scanner.nextLine(); // Consume newline
                            student.editSubject(subjectToEdit, newScoreForEdit);
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }

                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Student with ID " + idToEdit + " not found.");
            } else {
                try (FileWriter fileWriter = new FileWriter(FILE_PATH)) {
                    gson.toJson(studentList, fileWriter);
                }
                System.out.println("Student information updated successfully.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchStudentById(Scanner scanner) {
        try (FileReader fileReader = new FileReader(FILE_PATH)) {
            StudentList studentList = gson.fromJson(fileReader, StudentList.class);

            System.out.print("Enter ID of student to search: ");
            int searchId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            boolean found = false;
            for (Student student : studentList.getStudents()) {
                if (student.getId() == searchId) {
                    String json = gson.toJson(student); // Convert student to JSON
                    System.out.println(json);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Student with ID " + searchId + " not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchStudentByName(Scanner scanner) {
        try (FileReader fileReader = new FileReader(FILE_PATH)) {
            StudentList studentList = gson.fromJson(fileReader, StudentList.class);

            System.out.print("Enter name of student to search: ");
            String searchName = scanner.nextLine();

            boolean found = false;
            for (Student student : studentList.getStudents()) {
                if (student.getName().equalsIgnoreCase(searchName)) {
                    String json = gson.toJson(student); // Convert student to JSON
                    System.out.println(json);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Student with name \"" + searchName + "\" not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}