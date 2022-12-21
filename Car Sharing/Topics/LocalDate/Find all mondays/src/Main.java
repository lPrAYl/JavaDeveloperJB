import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        int month = scanner.nextInt();

        LocalDate date = LocalDate.of(year, month, 1);
        while (date.getMonthValue() == month) {
            if (date.getDayOfWeek() == DayOfWeek.MONDAY) {
                System.out.println(date);
                date = date.plusWeeks(1);
            } else {
                date = date.plusDays(1);
            }
        }
    }
}