import java.util.ArrayList;

public class ListDemo {
    public static void main(String[] args) {
        ArrayList<String> fruits = new ArrayList<>();

        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Mango");

        System.out.println(fruits);          // [Apple, Banana, Mango]
        System.out.println(fruits.get(0));   // Apple
        System.out.println(fruits.size());   // 3

        fruits.remove("Banana");
        System.out.println(fruits);          // [Apple, Mango]

        // Loop
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
    }
}