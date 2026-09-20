public class ArraysDemo {
    public static void main(String[] args) {
        int[] numbers = {10, 20, 30, 40, 50};

        // Access by index
        System.out.println("First: " + numbers[0]);
        System.out.println("Length: " + numbers.length);

        // Loop through
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();

        // For-each (easier)
        for (int num : numbers) {
            System.out.print(num + " ");
        }
    }
}