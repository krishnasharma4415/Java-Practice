// Base class
class Shape {
    // Protected members accessible to subclasses
    protected String color;
    protected boolean filled;
    
    // Static variable shared by all shapes
    public static int shapeCount = 0;
    
    // Constructor
    public Shape() {
        this("Red", true);
    }
    
    public Shape(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
        shapeCount++;
    }
    
    // Methods
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public boolean isFilled() {
        return filled;
    }
    
    public void setFilled(boolean filled) {
        this.filled = filled;
    }
    
    // Method to be overridden by subclasses
    public double getArea() {
        return 0.0;  // Default implementation
    }
    
    // Final method that cannot be overridden
    public final String getShapeType() {
        return "Generic Shape";
    }
    
    @Override
    public String toString() {
        return "Shape[color=" + color + ", filled=" + filled + "]";
    }
}

// Circle subclass
class Circle extends Shape {
    private double radius;
    
    public Circle() {
        this(1.0);
    }
    
    public Circle(double radius) {
        this(radius, "Red", true);
    }
    
    public Circle(double radius, String color, boolean filled) {
        super(color, filled);  // Call superclass constructor
        this.radius = radius;
    }
    
    public double getRadius() {
        return radius;
    }
    
    public void setRadius(double radius) {
        this.radius = radius;
    }
    
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
    
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
    
    @Override
    public String toString() {
        return "Circle[" + super.toString() + ", radius=" + radius + "]";
    }
}

// Rectangle subclass
class Rectangle extends Shape {
    private double width;
    private double height;
    
    public Rectangle() {
        this(1.0, 1.0);
    }
    
    public Rectangle(double width, double height) {
        this(width, height, "Red", true);
    }
    
    public Rectangle(double width, double height, String color, boolean filled) {
        super(color, filled);
        this.width = width;
        this.height = height;
    }
    
    public double getWidth() {
        return width;
    }
    
    public void setWidth(double width) {
        this.width = width;
    }
    
    public double getHeight() {
        return height;
    }
    
    public void setHeight(double height) {
        this.height = height;
    }
    
    @Override
    public double getArea() {
        return width * height;
    }
    
    public double getPerimeter() {
        return 2 * (width + height);
    }
    
    @Override
    public String toString() {
        return "Rectangle[" + super.toString() + ", width=" + width + ", height=" + height + "]";
    }
}

// Demo class to test inheritance
public class Inheritance {
    public static void main(String[] args) {
        // Create objects
        Shape shape = new Shape();
        Circle circle = new Circle(5.0, "Blue", false);
        Rectangle rectangle = new Rectangle(4.0, 6.0, "Green", true);
        
        // Demonstrate polymorphism
        Shape[] shapes = {shape, circle, rectangle};
        
        for(Shape s : shapes) {
            System.out.println(s.toString());
            System.out.println("Area: " + s.getArea());
            System.out.println("Shape Type: " + s.getShapeType());
            System.out.println();
        }
        
        // Accessing static variable
        System.out.println("Total shapes created: " + Shape.shapeCount);
    }
}