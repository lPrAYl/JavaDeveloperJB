import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        List<String> numbers = new ArrayList<>(List.of(scanner.nextLine().split(" ")));
        int countOfSwap = scanner.nextInt();
        for (int i = 0; i < countOfSwap; ++i) {
            int firstIndex = scanner.nextInt();
            int secondIndex = scanner.nextInt();
            Collections.swap(numbers, firstIndex, secondIndex);
        }

        System.out.println(String.join(" ", numbers));
    }
}