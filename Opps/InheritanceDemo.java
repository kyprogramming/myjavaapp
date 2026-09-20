package Opps;

class Animal {
    void eat() {
        System.out.println("Eating...");
    }
}

class Dog extends Animal {   // Dog inherits Animal
    void bark() {
        System.out.println("Barking...");
    }
}

public class InheritanceDemo {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.eat();    // from parent
        d.bark();   // from child
    }
}