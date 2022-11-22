package contacts.Factory;

import contacts.Content.Organization;
import contacts.Content.Record;
import contacts.Validator;

public class OrganizationFactory implements RecordFactory {
    @Override
    public Record createRecord() {
        System.out.print("Enter the organization name: ");
        String organizationName = scanner.nextLine();
        System.out.print("Enter the address: ");
        String address = scanner.nextLine();
        System.out.print("Enter the number: ");
        String phoneNumber = Validator.validatePhoneNumber(scanner.nextLine());

        return new Organization(organizationName, address, phoneNumber);
    }
}
