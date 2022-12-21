import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClassLoader classLoader = Scanner.class.getClassLoader();
        System.out.println(classLoader);
    }
}