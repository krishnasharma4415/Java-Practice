class BasicExceptionDemo {
    static void divideNumbers() {
        try {
            int result = 10 / 0; // Causes ArithmeticException
            System.out.println("Result: " + result); // This won't execute
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero: " + e.getMessage());
        }
        System.out.println("Execution continues after exception");
    }
}

// Example 6: Multiple Catch Blocks
class MultipleCatchDemo {
    static void demonstrateMultipleCatch(int choice) {
        try {
            if (choice == 1) {
                // Array index out of bounds
                int[] arr = new int[5];
                arr[10] = 50;
            } else if (choice == 2) {
                // Arithmetic exception
                int result = 10 / 0;
            } else if (choice == 3) {
                // Null pointer exception
                String str = null;
                System.out.println(str.length());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array index problem: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic problem: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Null pointer problem: " + e.getMessage());
        } catch (Exception e) {
            // Generic catch block for any other exceptions
            System.out.println("Some other exception: " + e);
        }
    }
}

// Example 7: Try-Catch-Finally
class FinallyBlockDemo {
    static void openAndProcessFile() {
        System.out.println("Opening file");
        try {
            // Code that might throw exception
            System.out.println("Reading from file");
            int result = 10 / 0; // Will cause exception
            System.out.println("Processing file data"); // Won't execute
        } catch (ArithmeticException e) {
            System.out.println("Error while processing file: " + e.getMessage());
        } finally {
            // Always executes, regardless of exception
            System.out.println("Closing file");
        }
    }
}

// Example 8: Custom Exception Class
class InsufficientBalanceException extends Exception {
    private double amount;
    
    InsufficientBalanceException(String message, double amount) {
        super(message);
        this.amount = amount;
    }
    
    public double getAmount() {
        return amount;
    }
}
