package contacts.Factory;

import contacts.Content.Record;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface RecordFactory {
    Scanner scanner = new Scanner(System.in);
    Record createRecord();

}
