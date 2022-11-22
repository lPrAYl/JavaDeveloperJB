package contacts;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static boolean isCorrectPhoneNumber(String number) {
        Pattern pattern = Pattern.compile("^\\+?([\\da-zA-Z]+[\\s-]?)?" +
                "(\\([\\da-zA-Z]{2,}(\\)[\\s-]|\\)$))?([\\da-zA-Z]{2,}[\\s-]?)*([\\da-zA-Z]{2,})?$");
        Matcher matcher = pattern.matcher(number);

        return matcher.find();
    }

    public static String validatePhoneNumber(String phoneNumber) {
        if (isCorrectPhoneNumber(phoneNumber)) {
            return phoneNumber;
        } else {
            System.out.println("Wrong number format!");
            return "[no number]";
        }
    }

    public static String validateBirthDate(String birthDate) {
        try {
            LocalDate.parse(birthDate);
            return birthDate;
        } catch (Exception e) {
            System.out.println("Bad birth date!");
            return "[no data]";
        }
    }

    public static String validateGender(String gender) {
        if ("M".equals(gender) || "F".equals(gender)) {
            return gender;
        } else {
            System.out.println("Bad gender!");
            return  "[no data]";
        }
    }
}
