import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        String str = scanner.nextLine();
        if (null == str || str.length() % 2 != 0) {
            System.out.println("false");
            return;
        } else {
            char[] ch = str.toCharArray();
            for (char c : ch) {
                if (!(c == '{' || c == '[' || c == '(' || 
                      c == '}' || c == ']' || c == ')')) {
                    System.out.println("false");
                    return;
                }
            }
        }

        while (str.contains("()") || str.contains("[]") || str.contains("{}")) {
            str = str.replaceAll("\\(\\)", "")
                    .replaceAll("\\[\\]", "")
                    .replaceAll("\\{\\}", "");
        }
        if (str.length() == 0) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
