import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class BankCard {
    public static void main(String[] args) {
        String text = "Java supports regular expressions. LET'S USE JAVA!!!";

        Pattern javaPattern = Pattern.compile("java", Pattern.CASE_INSENSITIVE);
        Matcher javaMatcher = javaPattern.matcher(text);

        Pattern regexPattern = Pattern.compile(".*regular expression.*");
        Matcher regexMatcher = regexPattern.matcher(text);

        System.out.println(regexMatcher.find());
        System.out.println(regexMatcher.matches());
        System.out.println(javaMatcher.find());
        System.out.println(Pattern.matches(".*java.*", text));
        System.out.println(javaMatcher.matches());

    }
}