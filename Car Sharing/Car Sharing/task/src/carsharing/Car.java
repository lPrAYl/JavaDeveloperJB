package carsharing;

public class Car {
    private int id;
    private String name;
    private int company_id;
    private String companyName;

    public Car(String name) {
        this.name = name;
    }

    public Car(String name, String companyName) {
        this.name = name;
        this.companyName = companyName;
    }

    public Car(int id, String name, int company_id, String companyName) {
        this.id = id;
        this.name = name;
        this.company_id = company_id;
        this.companyName = companyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }
}
