import java.util.Scanner;
import java.util.*;

class Student {
    public String name;
    public int age;

    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class Main
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        int age = sc.nextInt();
        Student s = new Student(name, age);
        System.out.println("Name: " + s.name);
        System.out.println("Age: " + s.age);
    }
}