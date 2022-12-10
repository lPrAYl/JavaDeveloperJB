import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String number = scanner.nextLine();
        int one = 0;
        int two = 0;
        for (int i = 0; i < 3; ++i) {
            one += Character.getNumericValue(number.charAt(i));
        }
        for (int i = 3; i < 6; ++i) {
            two += Character.getNumericValue(number.charAt(i));
        }

        if (one == two) {
            System.out.println("Lucky");
        } else {
            System.out.println("Regular");
        }
    }
}