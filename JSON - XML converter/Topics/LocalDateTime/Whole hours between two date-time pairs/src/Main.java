import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        LocalDateTime dateTime1 = LocalDateTime.parse(scanner.nextLine());
        LocalDateTime dateTime2 = LocalDateTime.parse(scanner.nextLine());

        System.out.println(ChronoUnit.HOURS.between(dateTime1, dateTime2));
    }
}