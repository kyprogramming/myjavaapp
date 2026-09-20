import java.util.HashMap;

public class MapDemo {
    public static void main(String[] args) {
        HashMap<String, Integer> ages = new HashMap<>();

        ages.put("Rahul", 25);
        ages.put("Priya", 22);
        ages.put("Amit", 30);

        System.out.println(ages.get("Rahul"));   // 25
        System.out.println(ages.containsKey("Priya")); // true
        System.out.println(ages.size());         // 3

        // Loop through
        for (String key : ages.keySet()) {
            System.out.println(key + " = " + ages.get(key));
        }
    }
}