class Box {
    // Instance variables
    private double width;
    private double height;
    private double depth;

    // Static variable (shared across all instances)
    public static int boxCount = 0;

    // Final constant
    public static final String SHAPE_NAME = "Box";

    // Default constructor
    public Box() {
        this(10, 10, 10); // Calls parameterized constructor
    }

    // Parameterized constructor
    public Box(double width, double height, double depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        boxCount++; // Increment the static counter
    }

    // Method to calculate volume
    public double volume() {
        return width * height * depth;
    }

    // Method with parameters
    public void setDimensions(double w, double h, double d) {
        width = w;
        height = h;
        depth = d;
    }

    // Static method
    public static void displayCount() {
        System.out.println("Total boxes created: " + boxCount);
    }

    // Method that returns a string representation
    @Override
    public String toString() {
        return "Box dimensions: " + width + " x " + height + " x " + depth;
    }
}

public class ClassBasics {
    public static void main(String[] args) {
        // Creating objects
        Box myBox1 = new Box();
        Box myBox2 = new Box(5, 6, 7);

        // Using methods
        System.out.println(myBox1.toString());
        System.out.println("Volume of myBox1: " + myBox1.volume());

        System.out.println(myBox2.toString());
        System.out.println("Volume of myBox2: " + myBox2.volume());

        // Using static method and accessing static variable
        Box.displayCount();
        System.out.println("Box shape name: " + Box.SHAPE_NAME);
    }
}