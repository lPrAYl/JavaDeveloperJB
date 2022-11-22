import java.time.LocalTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here

        String[] timeLine1 = scanner.nextLine().split(":");
        String[] timeLine2 = scanner.nextLine().split(":");

        LocalTime time1 = LocalTime.of(Integer.parseInt(timeLine1[0]), Integer.parseInt(timeLine1[1]), Integer.parseInt(timeLine1[2]) );
        LocalTime time2 = LocalTime.of(Integer.parseInt(timeLine2[0]), Integer.parseInt(timeLine2[1]), Integer.parseInt(timeLine2[2]) );

        System.out.println(Math.abs(time1.toSecondOfDay() - time2.toSecondOfDay()));
    }
}