public class ArraysAndControlFlow {
    public static void main(String[] args) {
        // One-dimensional array
        int[] numbers = new int[5];
        
        // Initialize with a loop
        for(int i = 0; i < numbers.length; i++) {
            numbers[i] = i * 10;
        }
        
        // Array with initializer
        int[] primes = {2, 3, 5, 7, 11, 13};
        
        // Two-dimensional array
        int[][] matrix = new int[3][4];
        
        // Initialize the 2D array
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = i * j;
            }
        }
        
        // Enhanced for loop (for-each)
        System.out.println("Prime numbers:");
        for(int prime : primes) {
            System.out.print(prime + " ");
        }
        System.out.println();
        
        // Break demonstration
        System.out.println("\nBreak example:");
        for(int i = 0; i < 10; i++) {
            if(i == 5) break;
            System.out.print(i + " ");
        }
        System.out.println();
        
        // Continue demonstration
        System.out.println("\nContinue example (skip even numbers):");
        for(int i = 0; i < 10; i++) {
            if(i % 2 == 0) continue;
            System.out.print(i + " ");
        }
        System.out.println();
        
        // Labeled break and continue
        System.out.println("\nLabeled break example:");
        outerLoop: 
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(i == 3 && j == 2) break outerLoop;
                System.out.print("(" + i + "," + j + ") ");
            }
            System.out.println();
        }
        
        // Display the matrix
        System.out.println("\nMatrix contents:");
        for(int[] row : matrix) {
            for(int value : row) {
                System.out.printf("%-3d", value);
            }
            System.out.println();
        }
    }
}