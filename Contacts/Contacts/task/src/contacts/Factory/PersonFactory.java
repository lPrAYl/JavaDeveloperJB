package contacts.Factory;

import contacts.Content.Person;
import contacts.Content.Record;
import contacts.Validator;

import java.time.LocalDate;

public class PersonFactory implements RecordFactory {
    @Override
    public Record createRecord() {
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter the birthday date: ");
        String birthDate = Validator.validateBirthDate(scanner.nextLine());
        System.out.print("Enter gender (M, F): ");
        String gender = Validator.validateGender(scanner.nextLine());
        System.out.print("Enter the number: ");
        String phoneNumber = Validator.validatePhoneNumber(scanner.nextLine());

        return new Person(name, surname, phoneNumber, birthDate, gender);
    }
}
