package contacts;

public interface Menu {
    void addContact();
    void contactsList();
    void searchContact();
    void countContacts();
    void exit();

    default void printMenu() {
        System.out.print("[menu] Enter action (add, list, search, count, exit): ");
    }
}
