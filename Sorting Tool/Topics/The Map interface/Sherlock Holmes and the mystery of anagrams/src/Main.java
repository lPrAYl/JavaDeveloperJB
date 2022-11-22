import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

class Main {
    static Map<Character, Integer> stringToMap(String s) {
        Map<Character, Integer> map = new HashMap<>();

        String str = s.toLowerCase();
        for (int i = 0; i < s.length(); i++) {

            map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);
        }

        return map;
    }

    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();

        if (Objects.equals(stringToMap(s1), stringToMap(s2))) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }

    }
}