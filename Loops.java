public class Loops {
    public static void main(String[] args) {
        // for loop — when you know count
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // while loop — when condition-based
        int j = 1;
        while (j <= 5) {
            System.out.print(j + " ");
            j++;
        }
        System.out.println();

        // do-while — runs at least once
        int k = 10;
        do {
            System.out.println("Runs once even though k=10");
        } while (k < 5);
    }
}