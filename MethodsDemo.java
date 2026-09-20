public class MethodsDemo {

    // No return, no parameter
    static void greet() {
        System.out.println("Hello!");
    }

    // With parameters + return value
    static int add(int a, int b) {
        return a + b;
    }

    // Returns double
    static double average(int a, int b) {
        return (a + b) / 2.0;
    }

    public static void main(String[] args) {
        greet();
        System.out.println("Sum: " + add(10, 20));
        System.out.println("Avg: " + average(10, 20));
    }
}