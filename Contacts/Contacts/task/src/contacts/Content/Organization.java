package contacts.Content;

public class Organization extends Record {
    private String organizationName;
    private String address;

    public Organization(String organizationName, String address, String phoneNumber) {
        super(phoneNumber);
        this.organizationName = organizationName;
        this.address = address;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public String getAddress() {
        return address;
    }

    public String getTitle() {
        return this.organizationName;
    }
    @Override
    public void getRecordInfo() {
        System.out.println("Organization name: " + this.organizationName);
        System.out.println("Address: " + this.address);
        System.out.println("Number: " + this.phoneNumber);
        System.out.println("Time created: " + this.timeOfCreated);
        System.out.println("Time last edit: " + this.timeOfLastEdit);
        System.out.println();
    }

    @Override
    public void editRecord() {
        System.out.print("Select a field (address, number): ");
        String field = scanner.nextLine();

        switch (field) {
            case "name" -> {
                System.out.print("Enter name: ");
                String organizationName = scanner.nextLine();
                this.setOrganizationName(organizationName);
            }
            case "address" -> {
                System.out.print("Enter address: ");
                String address = scanner.nextLine();
                this.setAddress(address);
            }
            case "number" -> {
                System.out.print("Enter number: ");
                String phoneNumber = scanner.nextLine();
                this.setPhoneNumber(phoneNumber);
            }
        }
        this.setTimeOfLastEdit();
        System.out.println("Saved!");
        getRecordInfo();
    }

    @Override
    public String toString() {
        return getOrganizationName() + " " + getAddress() + " " + getPhoneNumber();
    }
}
