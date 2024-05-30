package entity;

public class StudentList {
    private Student[] students;
    public Student[] getStudents(){
        return students;
    }
    public void addStudent(Student student) {
        if (students == null) {
            students = new Student[]{student};
        } else {
            Student[] newStudents = new Student[students.length + 1];
            System.arraycopy(students, 0, newStudents, 0, students.length);
            newStudents[students.length] = student;
            students = newStudents;
        }
    }
}
