import java.util.*;

public class Main {

    public static void processIterator(String[] array) {
        // write your code here
        ListIterator<String> iterator = Arrays.stream(array).toList().listIterator();
        List<String> list = new ArrayList<>(List.of());

        while (iterator.hasNext()) {
            if (array[iterator.nextIndex()].startsWith("J")) {
                list.add(array[iterator.nextIndex()].substring(1));
            }
            iterator.next();
        }

        Collections.reverse(list);
        for (String s: list) {
            System.out.println(s);
        }
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        processIterator(scanner.nextLine().split(" "));
    }
}