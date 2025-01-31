import java.util.Scanner;

class Student 
{
    private String name;
    private int id;
    private int marks;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        this.marks = -1;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public void viewMarks() {
        if (marks == -1) {
            System.out.println(name + " (ID: " + id + ") - Marks not assigned yet.");
        } else {
            System.out.println(name + " (ID: " + id + ") - Marks: " + marks);
        }
    }

    public String getName() {
        return name;
    }
}

class Teacher
{
    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    public void assignMarks(Student student, int marks) {
        student.setMarks(marks);
        System.out.println("Marks assigned by " + name + " to " + student.getName() + "");
    }
}

public class school
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Student student1 = new Student("Krishna Sharma", 128);
        Teacher teacher = new Teacher("teacher");

        System.out.print("Enter marks for " + student1.getName() + ": ");
        int marks = scanner.nextInt();
        teacher.assignMarks(student1, marks);

        student1.viewMarks();

        scanner.close();
    }
}