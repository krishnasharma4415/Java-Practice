class Animal {
    void makeSound() {
        System.out.println("Animal makes a sound");
    }
    
    void eat() {
        System.out.println("Animal eats food");
    }
}

class Dog extends Animal {
    // Overriding method from parent class
    @Override
    void makeSound() {
        System.out.println("Dog barks");
    }
    
    // Adding specific method
    void wagTail() {
        System.out.println("Dog wags tail");
    }
}

class Cat extends Animal {
    // Overriding method from parent class
    @Override
    void makeSound() {
        System.out.println("Cat meows");
    }
    
    // Overriding another method
    @Override
    void eat() {
        System.out.println("Cat eats fish");
    }
}
