import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        String string = scanner.nextLine();
        int i = 0;
        int len = string.length();
        while (string.charAt(i) == string.charAt(len - 1 - i) && i < len - 1 - i) {
            i++;
        }

        if (i == len / 2) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}