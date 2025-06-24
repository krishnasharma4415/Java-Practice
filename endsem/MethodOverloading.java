class MethodOverloading {
    // Method overloading by changing number of parameters
    static int calculate(int x, int y) {
        System.out.println("Method with 2 parameters");
        return x + y;
    }
    
    static int calculate(int x, int y, int z) {
        System.out.println("Method with 3 parameters");
        return x + y + z;
    }
    
    // Method overloading by changing data type of parameters
    static double calculate(double x, double y) {
        System.out.println("Method with double parameters");
        return x * y;
    }
    
    public static void main(String[] args) {
        System.out.println("Result 1: " + calculate(10, 20));           // calls first method
        System.out.println("Result 2: " + calculate(10, 20, 30));       // calls second method
        System.out.println("Result 3: " + calculate(10.5, 20.5));       // calls third method
    }
}