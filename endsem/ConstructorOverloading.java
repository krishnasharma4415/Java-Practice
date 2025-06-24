class Student {
    String name;
    int age;
    String course;
    
    // Default constructor
    Student() {
        System.out.println("Default constructor called");
        name = "Unknown";
        age = 0;
        course = "Not enrolled";
    }
    
    // Constructor with one parameter
    Student(String name) {
        this(); // Call default constructor
        System.out.println("One parameter constructor called");
        this.name = name;
    }
    
    // Constructor with two parameters
    Student(String name, int age) {
        this(name); // Call one parameter constructor
        System.out.println("Two parameter constructor called");
        this.age = age;
    }
    
    // Constructor with all parameters
    Student(String name, int age, String course) {
        this(name, age); // Call two parameter constructor
        System.out.println("Three parameter constructor called");
        this.course = course;
    }
    
    void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Course: " + course);
        System.out.println();
    }
}