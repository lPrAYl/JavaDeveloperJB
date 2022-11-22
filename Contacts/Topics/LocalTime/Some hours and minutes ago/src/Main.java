import java.time.LocalTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int hours = scanner.nextInt();
        int minutes = scanner.nextInt();

        LocalTime time = LocalTime.of(Integer.parseInt(line.split(":")[0]), Integer.parseInt(line.split(":")[1]));
        LocalTime time1 = time.minusHours(hours);
        LocalTime time2 = time1.minusMinutes(minutes);
        System.out.println(time2);
    }
}