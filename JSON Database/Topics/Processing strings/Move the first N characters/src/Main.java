import java.util.*;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        String s = scanner.next();
        int n = scanner.nextInt();

        if (s.length() <= n) {
            System.out.println(s);
        } else {
            System.out.println(s.substring(n).concat(s.substring(0, n)));
        }
    }
}