package entity;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private int id;
    private String name;
    private List<Subject> subjects;

    public Student() {
        subjects = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void addSubject(String name, double score) {
        Subject subject = new Subject();
        subject.setName(name);
        subject.setScore(score);
        subjects.add(subject);
    }

    public void removeSubject(String name) {
        subjects.removeIf(subject -> subject.getName().equalsIgnoreCase(name));
    }

    public void editSubject(String name, double newScore) {
        for (Subject subject : subjects) {
            if (subject.getName().equalsIgnoreCase(name)) {
                subject.setScore(newScore);
                break;
            }
        }
    }
}
