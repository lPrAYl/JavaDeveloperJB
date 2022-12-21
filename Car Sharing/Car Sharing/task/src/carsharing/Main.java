package carsharing;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        String databaseFileName = "carsharing";
        if (args.length == 2 && Objects.equals(args[0], "-databaseFileName")) {
            databaseFileName = args[1];
        }

        CompanyDAO dao = new CompanyDAOImpl(databaseFileName);
        new Session(dao).run();
    }
}
