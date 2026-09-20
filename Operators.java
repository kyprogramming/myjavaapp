public class Operators {
    public static void main(String[] args) {
        int a = 10, b = 3;

        System.out.println("Add: " + (a + b));      // 13
        System.out.println("Sub: " + (a - b));      // 7
        System.out.println("Mul: " + (a * b));      // 30
        System.out.println("Div: " + (a / b));      // 3
        System.out.println("Mod: " + (a % b));      // 1

        System.out.println("a > b: " + (a > b));    // true
        System.out.println("a == b: " + (a == b));  // false

        boolean check = (a > 5) && (b < 5);
        System.out.println("Both true: " + check);  // true
    }
}