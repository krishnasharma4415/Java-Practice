// Abstract class
abstract class Animal {
    protected String name;
    protected int age;
    
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Regular method
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    // Abstract methods (to be implemented by subclasses)
    public abstract void makeSound();
    public abstract String getType();
    
    // Final method
    public final String getInfo() {
        return "Name: " + name + ", Age: " + age + ", Type: " + getType();
    }
}

// Interface
interface Swimmable {
    // Constants (implicitly public, static, final)
    int MAX_DEPTH = 100;
    
    // Abstract methods (implicitly public)
    void swim();
    String getSwimSpeed();
}

// Another interface
interface Huntable {
    void hunt();
}

// Dog class extending abstract class
class Dog extends Animal {
    private String breed;
    
    public Dog(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
    }
    
    public String getBreed() {
        return breed;
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " barks: Woof! Woof!");
    }
    
    @Override
    public String getType() {
        return "Dog (" + breed + ")";
    }
}

// Fish class extending abstract class and implementing interface
class Fish extends Animal implements Swimmable {
    private String species;
    private boolean freshwater;
    
    public Fish(String name, int age, String species, boolean freshwater) {
        super(name, age);
        this.species = species;
        this.freshwater = freshwater;
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " makes bubbles: blub blub!");
    }
    
    @Override
    public String getType() {
        return "Fish (" + species + ")";
    }
    
    @Override
    public void swim() {
        System.out.println(name + " is swimming in " + 
                          (freshwater ? "freshwater" : "saltwater"));
    }
    
    @Override
    public String getSwimSpeed() {
        return "moderate";
    }
    
    public boolean isFreshwater() {
        return freshwater;
    }
}

// Dolphin class implementing multiple interfaces
class Dolphin extends Animal implements Swimmable, Huntable {
    public Dolphin(String name, int age) {
        super(name, age);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " clicks and whistles!");
    }
    
    @Override
    public String getType() {
        return "Dolphin";
    }
    
    @Override
    public void swim() {
        System.out.println(name + " is swimming gracefully in the ocean");
    }
    
    @Override
    public String getSwimSpeed() {
        return "fast";
    }
    
    @Override
    public void hunt() {
        System.out.println(name + " is hunting fish in a coordinated group");
    }
}

// Demo class
public class AbstractAndInterface {
    public static void main(String[] args) {
        // Create objects
        Dog dog = new Dog("Rex", 3, "German Shepherd");
        Fish fish = new Fish("Nemo", 1, "Clownfish", false);
        Dolphin dolphin = new Dolphin("Flipper", 8);
        
        // Using abstract class methods
        System.out.println("--- Animals Demo ---");
        Animal[] animals = {dog, fish, dolphin};
        
        for(Animal animal : animals) {
            System.out.println(animal.getInfo());
            animal.makeSound();
            System.out.println();
        }
        
        // Using interface methods
        System.out.println("--- Swimming Demo ---");
        Swimmable[] swimmers = {fish, dolphin};
        
        for(Swimmable swimmer : swimmers) {
            swimmer.swim();
            System.out.println("Swimming speed: " + swimmer.getSwimSpeed());
            
            // Using instanceof to check types
            if(swimmer instanceof Fish) {
                Fish f = (Fish)swimmer;
                System.out.println("Is freshwater: " + f.isFreshwater());
            }
            
            System.out.println();
        }
        
        // Demonstrate multiple interface implementation
        System.out.println("--- Hunting Demo ---");
        dolphin.hunt();
        
        // Accessing interface constant
        System.out.println("\nMaximum swimming depth: " + Swimmable.MAX_DEPTH + " meters");
    }
}