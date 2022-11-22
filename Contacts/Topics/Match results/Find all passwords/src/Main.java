import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> listPasswords = new ArrayList<>();

        String text = scanner.nextLine();

        // write your code here
        Pattern pattern = Pattern.compile("password[\\s:]*[0-9A-Za-z]+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            Pattern pattern1 = Pattern.compile("[0-9A-Za-z]+$", Pattern.CASE_INSENSITIVE);
            Matcher matcher1 = pattern1.matcher(matcher.group());
            matcher1.find();
            listPasswords.add(matcher1.group());
        }

        if (listPasswords.isEmpty()) {
            System.out.println("No passwords found.");
        } else {
            for (String psw: listPasswords) {
                System.out.println(psw);
            }
        }
    }
}