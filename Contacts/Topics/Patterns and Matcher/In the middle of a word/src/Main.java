import java.util.*;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String part = scanner.nextLine();
        String line = scanner.nextLine();

        // write your code here
        Pattern pattern = Pattern.compile(String.format(".*\\w+%s\\w+.*", part), Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);
        boolean bool = matcher.find();
        System.out.println(bool ? "YES" : "NO");
    }
}