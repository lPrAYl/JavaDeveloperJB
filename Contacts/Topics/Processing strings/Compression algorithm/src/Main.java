import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scannner = new Scanner(System.in);

        int count = 0;
        String line = scannner.nextLine();
        char ch = line.charAt(0);
        for (int i = 0; i < line.length(); i ++) {
            if (line.charAt(i) == ch) {
                count++;
            } else {
                System.out.print(ch);
                System.out.print(count);
                ch = line.charAt(i);
                count = 1;
            }
            if (i == line.length() - 1) {
                System.out.print(ch);
                System.out.print(count);
            }
        }
        System.out.println();
    }
}