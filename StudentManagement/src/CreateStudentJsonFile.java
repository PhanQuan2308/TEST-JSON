import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Student;
import entity.StudentList;
import entity.Subject;

import java.io.FileWriter;
import java.io.IOException;

public class CreateStudentJsonFile {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String FILE_PATH = "Student.json";

    public static void main(String[] args) {
        // Create a StudentList object
        StudentList studentList = new StudentList();

        // Create sample student data
        Student student1 = new Student();
        student1.setId(1);
        student1.setName("John Doe");
        student1.addSubject("Math", 90.0);
        student1.addSubject("Physics", 85.5);
        studentList.addStudent(student1);

        Student student2 = new Student();
        student2.setId(2);
        student2.setName("Jane Smith");
        student2.addSubject("Biology", 88.0);
        student2.addSubject("Chemistry", 92.3);
        studentList.addStudent(student2);

        // Write the StudentList object to the JSON file
        try (FileWriter fileWriter = new FileWriter(FILE_PATH)) {
            gson.toJson(studentList, fileWriter);
            System.out.println("Student JSON file created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
