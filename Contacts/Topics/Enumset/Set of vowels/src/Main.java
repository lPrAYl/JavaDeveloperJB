import java.util.EnumSet;
import java.util.Scanner;

public class Main {

    enum Alphabets {
        A, B, E, O, T, U, L, I
    }

    EnumSet<Alphabets> enumSet;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine().trim();

        // Change Code below this line

        EnumSet<Alphabets> set = EnumSet.noneOf(Alphabets.class);
        switch (string) {
            case "setOfVowels": set = EnumSet.of(Alphabets.A, Alphabets.E, Alphabets.O, Alphabets.U, Alphabets.I);
                break;
            case "setOfConsonants": set = EnumSet.of(Alphabets.B, Alphabets.T, Alphabets.L);
                break;
            case "containsAOnly": set = EnumSet.of(Alphabets.A);
                break;
            case "empty": set = EnumSet.noneOf(Alphabets.class);
                break;
            case "exceptT-A-E": set = EnumSet.of(Alphabets.B, Alphabets.O, Alphabets.U, Alphabets.L, Alphabets.I);
                break;
            default : System.out.println("ERROR");
                break;
        }
        System.out.println(set);
        // Change the code above this line
    }
}
