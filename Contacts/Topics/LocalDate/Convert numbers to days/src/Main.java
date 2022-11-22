import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        int year = scanner.nextInt();
        int first = scanner.nextInt();
        int second = scanner.nextInt();
        int third = scanner.nextInt();

        System.out.println(LocalDate.ofYearDay(year, first));
        System.out.println(LocalDate.ofYearDay(year, second));
        System.out.println(LocalDate.ofYearDay(year, third));

    }
}