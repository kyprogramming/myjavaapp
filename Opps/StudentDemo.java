package Opps;

class Student {
    String name;
    int age;

    // Constructor — runs when object is created
    Student(String n, int a) {
        name = n;
        age = a;
    }

    void display() {
        System.out.println(name + " - " + age);
    }
}

public class StudentDemo {
    public static void main(String[] args) {
        Student s1 = new Student("Kaushal", 20);
        Student s2 = new Student("Priya", 22);

        s1.display();
        s2.display();
    }
}

