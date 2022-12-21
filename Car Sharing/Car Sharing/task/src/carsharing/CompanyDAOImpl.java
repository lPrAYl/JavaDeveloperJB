package carsharing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.NULL;

public class CompanyDAOImpl implements CompanyDAO {
    private ConnectionFactory factory;

    public CompanyDAOImpl(String databaseFileName) {
        try {
            Files.createDirectories(Paths.get(System.getProperty("user.dir"),
                    "src", "carsharing", "db"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.factory = new ConnectionFactory(databaseFileName);
        createTableCompany();
        createTableCar();
        createTableCustomer();
    }

    @Override
    public List<Company> getAllCompanies() {
        List<Company> companies = new ArrayList<>();
        String sql = "SELECT id, name FROM company ORDER BY id";

        try (
                Connection connection = this.factory.getConnection();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(sql);
        ) {
            while (result.next()) {
                Company company = new Company(result.getInt(1),
                        result.getString(2));
                companies.add(company);
            }
        } catch (SQLException se) {
            se.printStackTrace();
            throw new RuntimeException(se);
        }

        return companies;
    }

    @Override
    public void insertCompany(String companyName) {
        String sql = "INSERT INTO Company (name) VALUES(?)";
        try (
                Connection connection = this.factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, companyName);
            statement.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
            throw new RuntimeException(se);
        }
    }

    @Override
    public void createTableCompany() {
        try (
                Connection connection = this.factory.getConnection();
                Statement statement = connection.createStatement()) {
            String sql =  "CREATE TABLE IF NOT EXISTS Company " +
                    "(id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    " name VARCHAR(255) UNIQUE NOT NULL)";
            statement.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
            throw new RuntimeException(se);
        }
    }

    public boolean carIsFree(int carId) {
        String sql = String.format("SELECT Car.id, Car.name FROM Car " +
                " INNER JOIN Customer ON (Car.id = Customer.rented_car_id)" +
                " WHERE Car.id = %s ", carId);
        try (
                Connection connection = this.factory.getConnection();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(sql);
        ) {
            if (result.next()) {
                return false;
            }
        } catch (SQLException se) {
            se.printStackTrace();
            throw new RuntimeException(se);
        }

        return true;
    }

    @Override
    public List<Car> getCars(int companyId) {
        List<Car> cars = new ArrayList<>();
        String sql = String.format("SELECT id, name FROM Car " +
                " WHERE company_id = %s " +
                " ORDER BY id", companyId);

        try (
                Connection connection = this.factory.getConnection();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(sql);
        ) {
            while (result.next()) {
                Car car = new Car(result.getString(2));
                cars.add(car);
            }
        } catch (SQLException se) {
            se.printStackTrace();
            throw new RuntimeException(se);
        }

        return cars;
    }

    @Override
    public void insertCar(String carName, int companyId) {
        String sql = "INSERT INTO Car (name, company_id) VALUES(?, ?)";
        try (
                Connection connection = this.factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, carName);
            statement.setInt(2, companyId);
            statement.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
            throw new RuntimeException(se);
        }
    }

    @Override
    public void createTableCar() {
        try (
                Connection connection = this.factory.getConnection();
                Statement statement = connection.createStatement()) {
            String sql =  "CREATE TABLE IF NOT EXISTS Car " +
                    "(id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    " name VARCHAR(255) UNIQUE NOT NULL, " +
                    " company_id INT NOT NULL, " +
                    " FOREIGN KEY (company_id) REFERENCES Company(id) )";

//            CONSTRAINT fk_company FOREIGN KEY (company_id)
//                    REFERENCES Company(company_id)

            statement.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
            throw new RuntimeException(se);
        }
    }

    public void showTable() {
        try (
                Connection connection = this.factory.getConnection();
                Statement statement = connection.createStatement();
        ) {
//            statement.executeUpdate("UPDATE CUSTOMER " +
//                    " SET rented_car_id = 1; ");
            ResultSet rs = statement.executeQuery(" SELECT * FROM Customer ");
            while(rs.next()) {
                System.out.print(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
                System.out.println();
            }
        } catch (SQLException se) {
            se.printStackTrace();
            throw new RuntimeException(se);
        }
    }

    public void updateCustomer(int customerId, int carId) {
        String sql = "UPDATE Customer" +
                " SET rented_car_id = ? " +
                " WHERE id = ? ";
        try (
                Connection connection = this.factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            if (carId == 0) {
                statement.setNull(1, NULL);
            } else {
                statement.setInt(1, carId);
            }
            statement.setInt(2, customerId);
            statement.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
            throw new RuntimeException(se);
        }
    }

    public List<Car> getRentedCar(int customerId) {
        List<Car> cars = new ArrayList<>();
        String sql = String.format("SELECT Car.name, Company.name FROM Customer " +
                " INNER JOIN Car ON (Customer.rented_car_id = Car.id) " +
                " INNER JOIN Company ON (Car.company_id = Company.id)" +
                " WHERE Customer.id = %s ", customerId);

        try (
                Connection connection = this.factory.getConnection();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(sql);
        ) {
            while (result.next()) {
                Car car = new Car(result.getString(1), result.getString(2));
                cars.add(car);
            }
        } catch (SQLException se) {
            se.printStackTrace();
            throw new RuntimeException(se);
        }

        return cars;
    }

    @Override
    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customer";

        try (
                Connection connection = this.factory.getConnection();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(sql);
        ) {
            while (result.next()) {
                Customer customer = new Customer(result.getInt(1),
                        result.getString(2), result.getInt(3));
                customers.add(customer);
            }
        } catch (SQLException se) {
            se.printStackTrace();
            throw new RuntimeException(se);
        }

        return customers;
    }

    @Override
    public void insertCustomer(String customerName) {
        String sql = "INSERT INTO Customer (name) VALUES(?)";
        try (
                Connection connection = this.factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, customerName);
            statement.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
            throw new RuntimeException(se);
        }
    }
    @Override
    public void createTableCustomer() {
        try (
                Connection connection = this.factory.getConnection();
                Statement statement = connection.createStatement()) {
            String sql =  "CREATE TABLE IF NOT EXISTS Customer " +
                    "(id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    " name VARCHAR(255) UNIQUE NOT NULL, " +
                    " rented_car_id INT, " +
                    " FOREIGN KEY (rented_car_id) REFERENCES Car(id) )";

//            CONSTRAINT fk_company FOREIGN KEY (company_id)
//                    REFERENCES Company(company_id)

            statement.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
            throw new RuntimeException(se);
        }
    }

    @Override
    public void dropTable() {
        try (
                Connection connection = this.factory.getConnection();
                Statement statement = connection.createStatement()) {
            //STEP 3: Execute a query
            String sql = "DROP TABLE IF EXISTS company";
            statement.execute(sql);
        } catch (SQLException se) {
            se.printStackTrace();
            throw new RuntimeException(se);
        }
    }

    @Override
    public void exit() {
        try {
            if (factory.getConnection() != null) factory.getConnection().close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
