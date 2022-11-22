import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        String ipAddress = scanner.nextLine();
//        String regex = "\\s*[(2[(5[0-5]|[0-4]\\d)]|[01]\\d\\d)\\.]{3}(2[(5[0-5]|[0-4]\\d)]|[01]\\d\\d)\\s*";
        String r = "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
        System.out.println(ipAddress.matches(r) ? "YES" : "NO");

    }
}