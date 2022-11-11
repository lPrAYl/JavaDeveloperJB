package budget;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class BudgetManager implements Menu {
    double balance;
    Actions action;
    File file;
    Scanner scanner;
    TypeOfPurchase typeOfPurchase;
    Map<TypeOfPurchase, ArrayList<Purchase>> purchaseListByCategory;

    public void initPurchaseListByCategory() {
        purchaseListByCategory.put(TypeOfPurchase.FOOD, new ArrayList<>(List.of()));
        purchaseListByCategory.put(TypeOfPurchase.CLOTHES, new ArrayList<>(List.of()));
        purchaseListByCategory.put(TypeOfPurchase.ENTERTAINMENT, new ArrayList<>(List.of()));
        purchaseListByCategory.put(TypeOfPurchase.OTHER, new ArrayList<>(List.of()));
    }

    BudgetManager() {
        this.balance = 0d;
        this.action = Actions.WAIT;
        this.file = new File("./purchases.txt");
        this.scanner = new Scanner(System.in);
        this.typeOfPurchase = TypeOfPurchase.OTHER;
        this.purchaseListByCategory = new HashMap<>();
        initPurchaseListByCategory();
    }

    @Override
    public void addIncome() {
        System.out.println("\nEnter income:");
        double income = scanner.nextDouble();
        this.balance += income;
        System.out.println("Income was added!\n");
    }

    private void add(TypeOfPurchase typeOfPurchase) {
        System.out.println("\nEnter purchase name:");
        scanner.nextLine();
        String purchaseName = scanner.nextLine();
        System.out.println("Enter its price:");
        double price = scanner.nextDouble();
        this.balance -= price;
        typeOfPurchase.setSum(price);
        purchaseListByCategory.get(typeOfPurchase).add(new Purchase(purchaseName, price));
        System.out.println("Purchase was added!");
    }
    @Override
    public void addPurchase() {
        do {
            printProductTypeMenuForAdd();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> add(TypeOfPurchase.FOOD);
                case 2 -> add(TypeOfPurchase.CLOTHES);
                case 3 -> add(TypeOfPurchase.ENTERTAINMENT);
                case 4 -> add(TypeOfPurchase.OTHER);
                case 5 -> action = Actions.BACK_TO_MAIN_MENU;
            }
        } while (!action.equals(Actions.BACK_TO_MAIN_MENU));
        System.out.println();
    }

    private void showInfo(TypeOfPurchase typeOfPurchase) {
        System.out.println("\n" + typeOfPurchase.getName() + ":");
        if (typeOfPurchase == TypeOfPurchase.ALL) {
            ArrayList<Purchase> listOfAll = new ArrayList<>(List.of());
            for (TypeOfPurchase type: purchaseListByCategory.keySet()) {
                listOfAll.addAll(purchaseListByCategory.get(type));
            }
            if (listOfAll.isEmpty()) {
                System.out.println("The purchase list is empty!\n");
            } else {
                for (Purchase purchase: listOfAll) {
                    System.out.printf("%s $%.2f\n", purchase.getName(), purchase.getPrice());
                }
                double totalSum = TypeOfPurchase.FOOD.getSum() + TypeOfPurchase.CLOTHES.getSum() +
                        TypeOfPurchase.ENTERTAINMENT.getSum() + TypeOfPurchase.OTHER.getSum();
                System.out.printf("Total sum: $%.2f\n", totalSum);
            }
        } else {
            if (purchaseListByCategory.get(typeOfPurchase).isEmpty()) {
                System.out.println("The purchase list is empty!\n");
            } else {
                for (Purchase purchase: purchaseListByCategory.get(typeOfPurchase)) {
                    System.out.printf("%s $%.2f\n", purchase.getName(), purchase.getPrice());
                }
                System.out.printf("Total sum: $%.2f\n", typeOfPurchase.getSum());
            }
        }
    }

    @Override
    public void showListOfPurchases() {
        if (purchaseListByCategory.isEmpty()) {
            System.out.println("\nThe purchase list is empty!\n");
        } else {
            do {
                printProductTypeMenuForInfo();
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> showInfo(TypeOfPurchase.FOOD);
                    case 2 -> showInfo(TypeOfPurchase.CLOTHES);
                    case 3 -> showInfo(TypeOfPurchase.ENTERTAINMENT);
                    case 4 -> showInfo(TypeOfPurchase.OTHER);
                    case 5 -> showInfo(TypeOfPurchase.ALL);
                    case 6 -> action = Actions.BACK_TO_MAIN_MENU;
                }
            } while (!action.equals(Actions.BACK_TO_MAIN_MENU));
            System.out.println();
        }
    }

    @Override
    public void showBalance() {
        System.out.printf("\nBalance: $%.2f\n\n", this.balance);
    }

    @Override
    public void save() {
        try (FileWriter writer = new FileWriter(file)) {
            for (TypeOfPurchase type: purchaseListByCategory.keySet()) {
                writer.write("Type: " + type.name() + "\n");
                ArrayList<Purchase> listOfPurchase = new ArrayList<>(List.of());
                listOfPurchase.addAll(purchaseListByCategory.get(type));
                for (Purchase purchase: listOfPurchase) {
                    writer.write(String.format("Purchase: %s$%.2f\n", purchase.getName(), purchase.getPrice()));
                }
                writer.write("Total sum: $" + type.getSum() + "\n");
            }
            writer.write("Balance: $" + this.balance + "\n");
            System.out.println("\nPurchases were saved!\n");
        } catch (IOException e) {
            System.out.printf("An exception occurred %s\n", e.getMessage());
        }
    }

    @Override
    public void load() {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String current = scanner.nextLine();
                if (current.startsWith("Type: ")) {
                    String type = current.substring(current.lastIndexOf(' ') + 1);
                    typeOfPurchase = TypeOfPurchase.valueOf(type);
                } else if (current.startsWith("Purchase: ")) {
                    String purchase = current.split("Purchase: ")[1];
                    String purchaseName = purchase.substring(0, purchase.lastIndexOf("$"));
                    double price = Double.parseDouble(purchase.substring(purchase.lastIndexOf("$") + 1));
                    purchaseListByCategory.get(typeOfPurchase).add(new Purchase(purchaseName, price));
                } else if (current.startsWith("Total sum: $")) {
                    String totalSum = current.substring(current.lastIndexOf('$') + 1);
                    typeOfPurchase.setSum(Double.parseDouble(totalSum));
                } else if (current.startsWith("Balance: $")) {
                    String balance = current.substring(current.lastIndexOf('$') + 1);
                    this.balance = Double.parseDouble(balance);
                }
            }
            System.out.println("\nPurchases were loaded!\n");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.getPath());
        }
    }

    private void sortAll() {
        List<Purchase> listOfAll = new ArrayList<>();
        for (TypeOfPurchase type: purchaseListByCategory.keySet()) {
            listOfAll.addAll(purchaseListByCategory.get(type));
        }
        if (listOfAll.isEmpty()) {
            System.out.println("\nThe purchase list is empty!");
        } else {
            listOfAll.sort(Comparator.comparing(Purchase::getPrice).reversed());
            System.out.println();
            //System.out.println("\nAll:");
            double totalSum = 0d;
            for (Purchase purchase: listOfAll) {
                System.out.printf("%s $%.2f\n", purchase.getName(), purchase.getPrice());
                totalSum += purchase.getPrice();
            }
            //System.out.printf("Total: $%.2f\n", totalSum);
        }
    }

    private void sortByType() {
        List<TypeOfPurchase> listOfAllType =
                new ArrayList<>(List.of(TypeOfPurchase.FOOD, TypeOfPurchase.CLOTHES, TypeOfPurchase.ENTERTAINMENT, TypeOfPurchase.OTHER));
        listOfAllType.sort(Comparator.comparing(TypeOfPurchase::getSum).reversed());
        double totalSum = 0d;
        System.out.println("\nTypes:");
        for (TypeOfPurchase type: listOfAllType) {
            System.out.printf("%s - $%.2f\n", type.getName(), type.getSum());
            totalSum += type.getSum();
        }
        System.out.printf("Total: $%.2f\n", totalSum);
    }

    private void sortCertainType() {
        printMenuForAnalyze();

        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> typeOfPurchase = TypeOfPurchase.FOOD;
            case 2 -> typeOfPurchase = TypeOfPurchase.CLOTHES;
            case 3 -> typeOfPurchase = TypeOfPurchase.ENTERTAINMENT;
            case 4 -> typeOfPurchase = TypeOfPurchase.OTHER;
        }
        List<Purchase> listOfCertainType = purchaseListByCategory.get(typeOfPurchase);
        if (listOfCertainType.isEmpty()) {
            System.out.println("\nThe purchase list is empty!");
        } else {
            listOfCertainType.sort(Comparator.comparing(Purchase::getPrice).reversed());
            double totalSum = 0d;
            System.out.printf("\n%s:\n", typeOfPurchase.getName());
            for (Purchase purchase: listOfCertainType) {
                System.out.printf("%s $%.2f\n", purchase.getName(), purchase.getPrice());
                totalSum += purchase.getPrice();
            }
            System.out.printf("Total: $%.2f\n", totalSum);
        }
    }
    @Override
    public void analyze() {
        do {
            printMenuForAnalyze();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> sortAll();
                case 2 -> sortByType();
                case 3 -> sortCertainType();
                case 4 -> action = Actions.BACK_TO_MAIN_MENU;
            }
        } while (!action.equals(Actions.BACK_TO_MAIN_MENU));
        System.out.println();
    }

    public void run() {
        do {
            printMenu();
            this.action = Actions.WAIT;
            int choice = scanner.nextInt();
            switch (choice) {
                case 0 -> action = Actions.EXIT;
                case 1 -> addIncome();
                case 2 -> addPurchase();
                case 3 -> showListOfPurchases();
                case 4 -> showBalance();
                case 5 -> save();
                case 6 -> load();
                case 7 -> analyze();
            }
        } while (!action.equals(Actions.EXIT));

        System.out.println("\nBye!");
    }
}

public class Main {
    public static void main(String[] args) {
        BudgetManager manager = new BudgetManager();
        manager.run();
    }
}
