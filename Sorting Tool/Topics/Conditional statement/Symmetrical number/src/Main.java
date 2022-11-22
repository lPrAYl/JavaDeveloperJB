import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        int number = scanner.nextInt();
        int a = number / 1000;
        int b = number / 100 % 10;
        int c = number / 10 % 10;
        int d = number % 10;

        if (a == d && b == c) {
            System.out.println(1);
        } else {
            System.out.println(25);
        }
    }
}
