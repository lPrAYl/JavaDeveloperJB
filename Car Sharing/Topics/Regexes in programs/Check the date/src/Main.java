import java.util.*;

class Date {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String date = scn.nextLine();
        String dateRegex = "\\s*(19|20)\\d{2}(\\/|.)(1[0-2]|0[1-9])(\\/|.)(3[01]|[0-2][1-9])\\s*";
        String regex = "\\s*(3[01]|[0-2][1-9])(\\/|.)(1[0-2]|0[1-9])(\\/|.)(19|20)\\d{2}\\s*";
        System.out.println(date.matches(dateRegex) || date.matches(regex) ? "Yes" : "No");
    }
}
