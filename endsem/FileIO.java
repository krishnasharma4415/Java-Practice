import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileIO {
    public static void main(String[] args) {
        // File handling basics
        System.out.println("--- File Handling Basics ---");
        fileOperations();
        
        // Byte stream operations
        System.out.println("\n--- Byte Stream Operations ---");
        byteStreamOperations();
        
        // Character stream operations
        System.out.println("\n--- Character Stream Operations ---");
        characterStreamOperations();
        
        // Buffered I/O operations (more efficient)
        System.out.println("\n--- Buffered I/O Operations ---");
        bufferedIOOperations();
        
        // Data streams for primitive data types
        System.out.println("\n--- Data Stream Operations ---");
        dataStreamOperations();
        
        // Object serialization
        System.out.println("\n--- Object Serialization ---");
        serializationDemo();
    }
    
    // Basic file operations
    private static void fileOperations() {
        try {
            // Create File objects
            File file = new File("example.txt");
            File directory = new File("exampleDir");
            
            // Creating a new file
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
            
            // Creating a directory
            if (directory.mkdir()) {
                System.out.println("Directory created: " + directory.getName());
            } else {
                System.out.println("Directory already exists or couldn't be created.");
            }
            
            // Writing to the file (using FileWriter for simplicity here)
            FileWriter writer = new FileWriter(file);
            writer.write("Hello, File I/O!");
            writer.close();
            
            // File information
            System.out.println("File path: " + file.getAbsolutePath());
            System.out.println("File size: " + file.length() + " bytes");
            System.out.println("Can read: " + file.canRead());
            System.out.println("Can write: " + file.canWrite());
            System.out.println("Is directory: " + file.isDirectory());
            
            // Listing directory contents
            if (directory.exists()) {
                File[] files = directory.listFiles();
                System.out.println("Directory contents:");
                if (files != null && files.length > 0) {
                    for (File f : files) {
                        System.out.println("  " + f.getName());
                    }
                } else {
                    System.out.println("  (empty)");
                }
            }
            
            // Rename file
            File renamedFile = new File("renamed_example.txt");
            if (file.renameTo(renamedFile)) {
                System.out.println("File renamed successfully.");
            } else {
                System.out.println("Failed to rename the file.");
            }
            
            // Delete file and directory
            if (renamedFile.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Failed to delete the file.");
            }
            
            if (directory.delete()) {
                System.out.println("Directory deleted successfully.");
            } else {
                System.out.println("Failed to delete the directory.");
            }
            
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Byte stream operations
    private static void byteStreamOperations() {
        try {
            // Create a file for byte stream operations
            File file = new File("byte_stream_example.dat");
            
            // Write binary data using FileOutputStream
            try (FileOutputStream fos = new FileOutputStream(file)) {
                byte[] data = {65, 66, 67, 68, 69}; // ASCII for ABCDE
                fos.write(data);
                System.out.println("Data written to file using byte stream.");
            }
            
            // Read binary data using FileInputStream
            try (FileInputStream fis = new FileInputStream(file)) {
                System.out.print("Data read from file: ");
                int byteData;
                while ((byteData = fis.read()) != -1) {
                    System.out.print((char) byteData);
                }
                System.out.println();
            }
            
            // Clean up
            file.delete();
            
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Character stream operations
    private static void characterStreamOperations() {
        try {
            // Create a file for character stream operations
            File file = new File("character_stream_example.txt");
            
            // Write text using FileWriter
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("Hello, Character Stream!\n");
                writer.write("This is a text file created using FileWriter.");
                System.out.println("Data written to file using character stream.");
            }
            
            // Read text using FileReader
            try (FileReader reader = new FileReader(file)) {
                System.out.println("Data read from file:");
                int charData;
                while ((charData = reader.read()) != -1) {
                    System.out.print((char) charData);
                }
            }
            
            // Append text to existing file
            try (FileWriter writer = new FileWriter(file, true)) {
                writer.write("\nThis text is appended to the file.");
                System.out.println("\nText appended to file.");
            }
            
            // Read again to show appended text
            try (FileReader reader = new FileReader(file)) {
                System.out.println("Updated file contents:");
                int charData;
                while ((charData = reader.read()) != -1) {
                    System.out.print((char) charData);
                }
            }
            
            // Clean up
            file.delete();
            
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Buffered I/O operations
    private static void bufferedIOOperations() {
        try {
            // Create a file for buffered I/O operations
            File file = new File("buffered_io_example.txt");
            
            // Write text using BufferedWriter (more efficient for large data)
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("Using BufferedWriter for efficient writing.");
                writer.newLine(); // Platform-independent newline
                writer.write("It buffers the I/O operations for better performance.");
                System.out.println("Data written to file using buffered writer.");
            }
            
            // Read text using BufferedReader (more efficient, line by line)
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                System.out.println("Data read from file using buffered reader:");
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
            
            // Clean up
            file.delete();
            
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Data streams for primitive data types
    private static void dataStreamOperations() {
        try {
            // Create a file for data stream operations
            File file = new File("data_stream_example.dat");
            
            // Write primitive data types using DataOutputStream
            try (DataOutputStream dos = new DataOutputStream(
                    new BufferedOutputStream(new FileOutputStream(file)))) {
                
                dos.writeInt(1234);
                dos.writeDouble(3.14159);
                dos.writeBoolean(true);
                dos.writeUTF("DataOutputStream can write various data types");
                System.out.println("Various data types written to file.");
            }
            
            // Read primitive data types using DataInputStream
            try (DataInputStream dis = new DataInputStream(
                    new BufferedInputStream(new FileInputStream(file)))) {
                
                System.out.println("Reading data types from file:");
                System.out.println("Integer: " + dis.readInt());
                System.out.println("Double: " + dis.readDouble());
                System.out.println("Boolean: " + dis.readBoolean());
                System.out.println("String: " + dis.readUTF());
            }
            
            // Clean up
            file.delete();
            
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Object serialization
    private static void serializationDemo() {
        try {
            // Create a file for serialization
            File file = new File("serialization_example.ser");
            
            // Create a serializable object
            Student student = new Student(1, "Alice Johnson", 20);
            
            // Serialize and write the object to a file
            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(file))) {
                
                oos.writeObject(student);
                System.out.println("Object serialized and saved to file.");
            }
            
            // Deserialize and read the object from file
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(file))) {
                
                Student deserializedStudent = (Student) ois.readObject();
                System.out.println("Object deserialized from file:");
                System.out.println(deserializedStudent);
            }
            
            // Clean up
            file.delete();
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Serializable class for object serialization demo
    static class Student implements Serializable {
        private static final long serialVersionUID = 1L;
        private int id;
        private String name;
        private int age;
        
        public Student(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }
        
        @Override
        public String toString() {
            return "Student [id=" + id + ", name=" + name + ", age=" + age + "]";
        }
    }
}