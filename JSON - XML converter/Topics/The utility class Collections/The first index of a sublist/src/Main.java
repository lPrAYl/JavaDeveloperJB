import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        List<String> firstLine = new ArrayList<>(List.of(scanner.nextLine().split(" ")));
        List<String> secondLine = new ArrayList<>(List.of(scanner.nextLine().split(" ")));


        System.out.println(Collections.indexOfSubList(firstLine, secondLine) + " " +
                Collections.lastIndexOfSubList(firstLine, secondLine));

    }
}