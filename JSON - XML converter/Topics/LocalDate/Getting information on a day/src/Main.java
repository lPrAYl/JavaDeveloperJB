import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();
        LocalDate date = LocalDate.parse(line);
        int dayOfYear = date.getDayOfYear();
        int dayOfMonth = date.getDayOfMonth();

        System.out.println(dayOfYear + " " + dayOfMonth);
    }
}