package contacts;

import contacts.Content.Record;
import contacts.Factory.OrganizationFactory;
import contacts.Factory.PersonFactory;
import contacts.Factory.RecordFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook implements Menu {
    private Actions action;
    private List<Record> contacts;
    private Scanner scanner;

    public PhoneBook() {
        this.action = Actions.MAIN_MENU;
        this.contacts = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        do {
            printMenu();
            String command = scanner.nextLine();
            switch (command) {
                case "add" -> addContact();
                case "list" -> contactsList();
                case "search" -> searchContact();
                case "count" -> countContacts();
                case "exit" -> action = Actions.EXIT;
            }
        } while (!action.equals(Actions.EXIT));
    }

    @Override
    public void addContact() {
        System.out.print("Enter the type (person, organization): ");
        String type = scanner.nextLine();
        RecordFactory personFactory = new PersonFactory();
        RecordFactory organizationFactory = new OrganizationFactory();
        switch (type) {
            case "person" -> this.contacts.add(personFactory.createRecord());
            case "organization" -> this.contacts.add(organizationFactory.createRecord());
        }
        System.out.println("The record added.\n");
    }

    @Override
    public void contactsList() {
        int index = 1;
        action = Actions.LIST_MENU;
        for (Record record: contacts) {
            System.out.println(index + ". " + record.getTitle());
            index++;
        }
        System.out.println();

        System.out.print("[list] Enter action ([number], back): ");
        String choice = scanner.nextLine();
        try {
            index = Integer.parseInt(choice);
            contacts.get(index - 1).getRecordInfo();
            recordActions(index - 1);
        } catch (Exception e) {
            action = Actions.MAIN_MENU;
        }
    }

    private void recordActions(int index) {
        do {
            System.out.print("[record] Enter action (edit, delete, menu): ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "edit" -> contacts.get(index).editRecord();
                case "delete" -> {
                    contacts.remove(index);
                    action = Actions.MAIN_MENU;
                }
                case "menu" -> action = Actions.MAIN_MENU;
            }
        } while (!action.equals(Actions.MAIN_MENU));
    }

    @Override
    public void searchContact() {
        action = Actions.SEARCH_MENU;

        do {
            System.out.print("Enter search query: ");
            String searchQuery = scanner.nextLine();
            Pattern pattern = Pattern.compile(searchQuery, Pattern.CASE_INSENSITIVE);

            int count = 0;
            for (Record record: contacts) {
                Matcher matcher = pattern.matcher(record.toString());
                if (matcher.find()) {
                    count++;
                }
            }

            System.out.printf("Found %d results:\n", count);
            int index = 1;
            for (Record record: contacts) {
                Matcher matcher = pattern.matcher(record.toString());
                if (matcher.find()) {
                    System.out.printf("%d. %s\n", index, record.getTitle());
                    index++;
                }
            }

            System.out.print("\n[search] Enter action ([number], back, again): ");
            String choice = scanner.nextLine();
            try {
                index = Integer.parseInt(choice);
                int i = 0;
                int j = 0;

                for (Record record: contacts) {
                    Matcher matcher = pattern.matcher(record.toString());
                    if (matcher.find()) {
                        i++;
                    }
                    if (i == index) {
                        index = j;
                        break;
                    }
                    j++;
                }
                contacts.get(index).getRecordInfo();
                recordActions(index);
            } catch (Exception e) {
                switch (choice) {
                    case "back" -> action = Actions.MAIN_MENU;
                    case "again" -> action = Actions.SEARCH_MENU;
                }
            }
        } while (!action.equals(Actions.MAIN_MENU));
        System.out.println();
    }

    @Override
    public void countContacts() {
        System.out.printf("The Phone Book has %d records.\n\n", contacts.size());
    }


    @Override
    public void exit() { }
}
