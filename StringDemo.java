public class StringDemo {
    public static void main(String[] args) {
        String name = "Java Programming";

        System.out.println(name.length());              // 16
        System.out.println(name.toUpperCase());         // JAVA PROGRAMMING
        System.out.println(name.toLowerCase());         // java programming
        System.out.println(name.charAt(0));             // J
        System.out.println(name.contains("Java"));      // true
        System.out.println(name.substring(0, 4));       // Java
        System.out.println(name.replace("Java", "Python"));
    }
}