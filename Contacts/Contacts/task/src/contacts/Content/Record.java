package contacts.Content;

import contacts.Validator;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Record {
    protected String phoneNumber;
    protected LocalDateTime timeOfCreated;
    protected LocalDateTime timeOfLastEdit;
    protected Scanner scanner;

    Record(String phoneNumber) {
        this.phoneNumber = Validator.validatePhoneNumber(phoneNumber);
        this.timeOfCreated = LocalDateTime.now().withNano(0);
        this.timeOfLastEdit = LocalDateTime.now().withNano(0);
        this.scanner = new Scanner(System.in);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = Validator.validatePhoneNumber(phoneNumber);
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getTimeOfCreated() {
        return this.timeOfCreated.toString();
    }

    public void setTimeOfLastEdit() {
        this.timeOfLastEdit = LocalDateTime.now().withNano(0);
    }

    public String getTimeOfEdited() {
        return this.timeOfLastEdit.toString();
    }

    public abstract String getTitle();
    public abstract void getRecordInfo();
    public abstract void editRecord();
}
