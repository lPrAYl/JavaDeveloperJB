package carsharing;

import java.util.List;
import java.util.Scanner;

public class Session {
    private final Scanner scanner;
    private CompanyDAO dao;

    public Session(CompanyDAO dao) {
        this.scanner = new Scanner(System.in);
        this.dao = dao;
    }

    private void carList(int companyId) {
        System.out.println();
        List<Car> cars = dao.getCars(companyId);
        if (cars.isEmpty()) {
            System.out.println("The car list is empty!");
        } else {
            int id = 1;
            for (Car car : cars) {
                System.out.println(id + ". " + car.getName());
                id++;
            }
        }
        companyMenu(companyId);
    }

    private void createCar(int companyId) {
        System.out.println();
        System.out.println("Enter the car name:");
        String name = scanner.nextLine();
        if (name.isEmpty()) {
            name = scanner.nextLine();
        }
        dao.insertCar(name, companyId);
        System.out.println("The car was added!");
        companyMenu(companyId);
    }

    private void companyMenu(int choice) {
        System.out.println();
        List<Company> companies = dao.getAllCompanies();
        if (choice > companies.size()) {
            System.out.println("No such company in the list");
        } else {
            Company company = companies.get(choice - 1);
            System.out.printf("'%s' company\n", company.getName());
            System.out.println("1. Car list");
            System.out.println("2. Create a car");
            System.out.println("0. Back");
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> carList(company.getId());
                case 2 -> createCar(company.getId());
                case 0 -> managerMenu();
            }
        }
    }

    private void companyList() {
        System.out.println();
        List<Company> companies = dao.getAllCompanies();
        if (companies.isEmpty()) {
            System.out.println("The company list is empty!");
            System.out.println();
            managerMenu();
        } else {
            System.out.println("Choose the company:");
            for (Company company : companies) {
                System.out.println(company.getId() + ". " + company.getName());
            }
            System.out.println("0. Back");
            int choice = scanner.nextInt();
            switch (choice) {
                case 0 -> managerMenu();
                default -> companyMenu(choice);
            }
        }
    }

    private void createCompany() {
        System.out.println();
        System.out.println("Enter the company name:");
        String name = scanner.nextLine();
        if (name.isEmpty()) {
            name = scanner.nextLine();
        }
        dao.insertCompany(name);
        System.out.println("The company was created!");
        managerMenu();
    }

    private void managerMenu() {
        System.out.println();
        System.out.println("1. Company list");
        System.out.println("2. Create a company");
        System.out.println("0. Back");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> companyList();
            case 2 -> createCompany();
            case 0 -> mainMenu();
            default -> managerMenu();
        }
    }

    private void carMenu(int companyId, int customerId) {
        System.out.println();
        List<Car> cars = dao.getCars(companyId);
        if (cars.isEmpty()) {
            System.out.println("No available cars in the 'Company name' company");  //  TODO
        } else {
            int id = 1;
            System.out.println("Choose a car:");
            for (Car car : cars) {
                if (dao.carIsFree(car.getId() + 1)) {
                    System.out.println(id + ". " + car.getName());
                    id++;
                }
            }
            System.out.println("0. Back");
            int choice = scanner.nextInt();
            id = 1;
            for (Car car : cars) {
                if (id == choice && dao.carIsFree(car.getId() + 1)) {
                    dao.updateCustomer(customerId, car.getId() + 1);
                    System.out.println();
                    System.out.printf("You rented '%s'", car.getName());
                    System.out.println();
//                    dao.showTable();
                    break;
                }
                id++;
            }
        }
        customerMenu(customerId);
    }

    private void rentACar(int customerId) {
        System.out.println();
        List<Car> rentedCars = dao.getRentedCar(customerId);
        if (!rentedCars.isEmpty()) {
            System.out.println();
            System.out.println("You've already rented a car!");
            customerMenu(customerId);
        } else {
            List<Company> companies = dao.getAllCompanies();
            System.out.println("Choose the company:");
            for (Company company : companies) {
                System.out.println(company.getId() + ". " + company.getName());
            }
            System.out.println("0. Back");
            int choice = scanner.nextInt();
            switch (choice) {
                case 0 -> customerMenu(customerId);
                default -> carMenu(choice, customerId);
            }
        }
    }

    private void returnRentedCar(int customerId) {
        List<Car> rentedCars = dao.getRentedCar(customerId);
        if (rentedCars.isEmpty()) {
            System.out.println();
            System.out.println("You didn't rent a car!");
        } else {
            dao.updateCustomer(customerId, 0);
            System.out.println();
            System.out.println("You've returned a rented car!");
        }
        customerMenu(customerId);
    }

    private void getRentedCar(int customerId) {
        System.out.println();
        List<Car> rentedCars = dao.getRentedCar(customerId);
        if (rentedCars.isEmpty()) {
            System.out.println("You didn't rent a car!");
        } else {
            for (Car car : rentedCars) {
                System.out.println("You rented car:");
                System.out.println(car.getName());
                System.out.println("Company:");
                System.out.println(car.getCompanyName());
            }
        }
        customerMenu(customerId);
    }

    private void customerMenu(int customerId) {
        System.out.println();
        List<Customer> customers = dao.getCustomers();
        if (customerId > customers.size()) {
            System.out.println("No such company in the list");
        } else {
            Customer customer = customers.get(customerId - 1);
            System.out.println("1. Rent a car");
            System.out.println("2. Return a rented car");
            System.out.println("3. My rented car");
            System.out.println("0. Back");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> rentACar(customerId);
                case 2 -> returnRentedCar(customerId);
                case 3 -> getRentedCar(customerId);
                case 0 -> mainMenu();
            }
        }
    }

    private void customerList() {
        System.out.println();
        List<Customer> customers = dao.getCustomers();
        if (customers.isEmpty()) {
            System.out.println("The customer list is empty!");
            System.out.println();
            mainMenu();
        } else {
            System.out.println("Customer list:");
            for (Customer customer : customers) {
                System.out.println(customer.getId() + ". " + customer.getName());
            }
            System.out.println("0. Back");
            int choice = scanner.nextInt();
            switch (choice) {
                case 0 -> mainMenu();
                default -> customerMenu(choice);
            }
        }
    }

    private void createCustomer() {
        System.out.println();
        System.out.println("Enter the customer name:");
        String name = scanner.nextLine();
        if (name.isEmpty()) {
            name = scanner.nextLine();
        }
        dao.insertCustomer(name);
        System.out.println("The customer was added!");
        System.out.println();
        mainMenu();
    }

    private void mainMenu() {
        System.out.println("1. Log in as a manager");
        System.out.println("2. Log in as a customer");
        System.out.println("3. Create a customer");
        System.out.println("0. Exit");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> managerMenu();
            case 2 -> customerList();
            case 3 -> createCustomer();
            case 0 -> dao.exit();
            default -> mainMenu();
        }
    }

    public void run() {
        mainMenu();
    }
}
