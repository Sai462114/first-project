import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Simple console-based Student Management System.
 * Run with: javac src/Main.java && java -cp src Main
 */
public class Main {
    private static final List<Student> students = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static int nextId = 1;

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add student");
            System.out.println("2. View all students");
            System.out.println("3. Search student by ID");
            System.out.println("4. Update student");
            System.out.println("5. Delete student");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> addStudent();
                case "2" -> viewStudents();
                case "3" -> searchStudent();
                case "4" -> updateStudent();
                case "5" -> deleteStudent();
                case "0" -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
        System.out.println("Thank you for using the Student Management System!");
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }
        System.out.print("Enter course: ");
        String course = scanner.nextLine().trim();
        students.add(new Student(nextId++, name, course));
        System.out.println("Student added successfully.");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("\nID | Name | Course");
        System.out.println("-------------------");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void searchStudent() {
        Student student = findById(readId());
        System.out.println(student == null ? "Student not found." : student);
    }

    private static void updateStudent() {
        Student student = findById(readId());
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.print("Enter new name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter new course: ");
        String course = scanner.nextLine().trim();
        if (!name.isEmpty()) student.setName(name);
        if (!course.isEmpty()) student.setCourse(course);
        System.out.println("Student updated successfully.");
    }

    private static void deleteStudent() {
        int id = readId();
        Student student = findById(id);
        if (student != null) {
            students.remove(student);
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static int readId() {
        while (true) {
            System.out.print("Enter student ID: ");
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid numeric ID.");
            }
        }
    }

    private static Student findById(int id) {
        for (Student student : students) {
            if (student.getId() == id) return student;
        }
        return null;
    }

    private static class Student {
        private final int id;
        private String name;
        private String course;

        Student(int id, String name, String course) {
            this.id = id;
            this.name = name;
            this.course = course;
        }

        int getId() { return id; }
        void setName(String name) { this.name = name; }
        void setCourse(String course) { this.course = course; }

        @Override
        public String toString() {
            return id + " | " + name + " | " + course;
        }
    }
}
