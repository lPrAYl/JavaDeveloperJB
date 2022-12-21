import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        LocalDateTime dateTime = LocalDateTime.parse(scanner.nextLine());

        LocalDate dateOf = dateTime.toLocalDate();
        LocalTime timeOf = dateTime.toLocalTime();

        System.out.println((dateOf.getDayOfYear() - 1) * 24 + timeOf.getHour());
    }
}