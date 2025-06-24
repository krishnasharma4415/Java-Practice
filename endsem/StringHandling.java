public class StringHandling {
    public static void main(String[] args) {
        // String creation
        String s1 = "Hello";  // Using literal (String pool)
        String s2 = new String("Hello");  // Using new (heap)
        
        // String methods
        System.out.println("String length: " + s1.length());
        System.out.println("Character at index 1: " + s1.charAt(1));
        System.out.println("s1 equals s2? " + s1.equals(s2));
        System.out.println("s1 == s2? " + (s1 == s2));  // Comparing references
        System.out.println("Substring: " + s1.substring(1, 4));
        
        // String concatenation
        String s3 = s1 + " World";
        System.out.println("Concatenated string: " + s3);
        
        // String methods
        String text = "  Java Programming Language  ";
        System.out.println("Original: '" + text + "'");
        System.out.println("Trimmed: '" + text.trim() + "'");
        System.out.println("Uppercase: " + text.toUpperCase());
        System.out.println("Lowercase: " + text.toLowerCase());
        System.out.println("Contains 'gram': " + text.contains("gram"));
        System.out.println("Replace: " + text.replace('a', 'A'));
        
        // String splitting
        String csvData = "John,Doe,28,Developer";
        String[] parts = csvData.split(",");
        System.out.println("\nSplit CSV data:");
        for(String part : parts) {
            System.out.println(part);
        }
        
        // StringBuilder for mutable strings
        StringBuilder sb = new StringBuilder("Java");
        System.out.println("\nStringBuilder operations:");
        System.out.println("Initial: " + sb);
        
        sb.append(" is powerful");
        System.out.println("After append: " + sb);
        
        sb.insert(5, " Programming");
        System.out.println("After insert: " + sb);
        
        sb.replace(0, 4, "The Java");
        System.out.println("After replace: " + sb);
        
        sb.delete(4, 9);
        System.out.println("After delete: " + sb);
        
        sb.reverse();
        System.out.println("After reverse: " + sb);
        
        // Converting StringBuilder to String
        String result = sb.toString();
        System.out.println("Final string: " + result);
        
        // StringBuffer (thread-safe alternative to StringBuilder)
        StringBuffer sbuf = new StringBuffer("Thread-safe");
        sbuf.append(" mutable string");
        System.out.println("\nStringBuffer: " + sbuf);
    }
}