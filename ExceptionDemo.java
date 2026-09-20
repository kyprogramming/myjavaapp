public class ExceptionDemo {
    public static void main(String[] args) {
        try {
            int[] arr = {1, 2, 3};
            System.out.println(arr[5]);      // out of bounds
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array index wrong!");
        } finally {
            System.out.println("Always runs");
        }

        try {
            int result = 10 / 0;             // arithmetic error
        } catch (ArithmeticException e) {
            System.out.println(e.toString());
        }
    }
}