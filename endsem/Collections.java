import java.util.*;
import java.util.stream.Collectors;

public class Collections {
    public static void main(String[] args) {
        // ArrayList demo
        System.out.println("--- ArrayList Demo ---");
        arrayListDemo();
        
        // LinkedList demo
        System.out.println("\n--- LinkedList Demo ---");
        linkedListDemo();
    }
    
    private static void arrayListDemo() {
        // Creating an ArrayList
        ArrayList<String> fruits = new ArrayList<>();
        
        // Adding elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Date");
        fruits.add("Fig");
        
        // Displaying the ArrayList
        System.out.println("ArrayList: " + fruits);
        
        // Accessing elements
        System.out.println("Element at index 2: " + fruits.get(2));
        
        // Modifying elements
        fruits.set(1, "Blueberry");
        System.out.println("After modification: " + fruits);
        
        // Removing elements
        fruits.remove("Fig");
        fruits.remove(0);
        System.out.println("After removal: " + fruits);
        
        // Checking if element exists
        System.out.println("Contains Cherry? " + fruits.contains("Cherry"));
        
        // Finding index of element
        System.out.println("Index of Cherry: " + fruits.indexOf("Cherry"));
        
        // Size of ArrayList
        System.out.println("Size of ArrayList: " + fruits.size());
        
        // Iterating through ArrayList
        System.out.println("Iterating through ArrayList:");
        for (String fruit : fruits) {
            System.out.println("  " + fruit);
        }
        
        // Converting ArrayList to array
        String[] fruitArray = fruits.toArray(new String[0]);
        System.out.println("Converted to array: " + Arrays.toString(fruitArray));
        
        // Creating ArrayList from array
        String[] vegetables = {"Carrot", "Broccoli", "Spinach"};
        ArrayList<String> vegList = new ArrayList<>(Arrays.asList(vegetables));
        System.out.println("ArrayList from array: " + vegList);
        
        // Clearing the ArrayList
        fruits.clear();
        System.out.println("After clearing: " + fruits + ", Size: " + fruits.size());
    }
    
    private static void linkedListDemo() {
        // Creating a LinkedList
        LinkedList<String> animals = new LinkedList<>();
        
        // Adding elements
        animals.add("Dog");
        animals.add("Cat");
        animals.add("Horse");
        
        // Adding elements at specific positions
        animals.addFirst("Lion");  // Add at the beginning
        animals.addLast("Tiger");  // Add at the end
        animals.add(2, "Elephant");  // Add at index 2
        
        System.out.println("LinkedList: " + animals);
        
        // Accessing elements
        System.out.println("First element: " + animals.getFirst());
        System.out.println("Last element: " + animals.getLast());
        System.out.println("Element at index 2: " + animals.get(2));
        
        // Removing elements
        animals.removeFirst();  // Remove first element
        animals.removeLast();   // Remove last element
        animals.remove("Elephant");  // Remove specific element
        
        System.out.println("After removal: " + animals);
        
        // Using LinkedList as a Queue (FIFO)
        System.out.println("\nUsing LinkedList as a Queue:");
        LinkedList<String> queue = new LinkedList<>();
        
        // Adding elements (enqueue)
        queue.offer("Task 1");  // Add to tail
        queue.offer("Task 2");
        queue.offer("Task 3");
        
        System.out.println("Queue: " + queue);
        
        // Processing queue (dequeue)
        while (!queue.isEmpty()) {
            System.out.println("Processing: " + queue.poll());  // Remove from head
        }
        
        // Using LinkedList as a Stack (LIFO)
        System.out.println("\nUsing LinkedList as a Stack:");
        LinkedList<String> stack = new LinkedList<>();
        
        // Adding elements (push)
        stack.push("Page 1");  // Add to head
        stack.push("Page 2");
        stack.push("Page 3");
        
        System.out.println("Stack: " + stack);
        
        // Processing stack (pop)
        while (!stack.isEmpty()) {
            System.out.println("Processing: " + stack.pop());  // Remove from head
        }
    }   
}