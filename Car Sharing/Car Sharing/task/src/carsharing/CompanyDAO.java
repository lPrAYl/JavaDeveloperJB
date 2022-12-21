package carsharing;

import java.util.List;

public interface CompanyDAO {
    void createTableCompany();
    void createTableCar();
    void createTableCustomer();
    void dropTable();
    void insertCompany(String companyName);
    void insertCar(String carName, int companyId);
    void insertCustomer(String customerName);
    List<Company> getAllCompanies();
    List<Car> getCars(int companyId);
    List<Customer> getCustomers();
    void exit();

    List<Car> getRentedCar(int customerId);

    void updateCustomer(int customerId, int id);

    void showTable();

    boolean carIsFree(int i);
}
