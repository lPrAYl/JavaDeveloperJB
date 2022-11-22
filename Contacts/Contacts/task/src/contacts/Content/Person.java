package contacts.Content;

import contacts.Validator;

import java.time.LocalDate;

public class Person extends Record {
    private String name;
    private String surname;
    private String birthDate;
    private String gender;

    public Person(String name, String surname, String phoneNumber, String birthDate, String gender) {
        super(phoneNumber);
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender= gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setGender(String gender) {
        this.gender = Validator.validateGender(gender);
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = Validator.validateBirthDate(birthDate);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String getTitle() {
        return this.name + " " + this.surname;
    }
    @Override
    public void getRecordInfo() {
        System.out.println("Name: " + this.name);
        System.out.println("Surname: " + this.surname);
        System.out.println("Birth date: " + this.birthDate);
        System.out.println("Gender: " + this.gender);
        System.out.println("Number: " + this.phoneNumber);
        System.out.println("Time created: " + this.timeOfCreated);
        System.out.println("Time last edit: " + this.timeOfLastEdit);
        System.out.println();
    }

    @Override
    public void editRecord() {
        System.out.print("Select a field (name, surname, birth, gender, number): ");
        String field = scanner.nextLine();

        switch (field) {
            case "name" -> {
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                this.setName(name);
            }
            case "surname" -> {
                System.out.print("Enter surname: ");
                String surname = scanner.nextLine();
                this.setSurname(surname);
            }
            case "birth" -> {
                System.out.print("Enter birthday: ");
                String birthdate = scanner.nextLine();
                this.setBirthDate(birthdate);
            }
            case "gender" -> {
                System.out.print("Enter gender: ");
                String gender = scanner.nextLine();
                this.setGender(gender);
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
        return getName() + " " + getSurname() + " " + getPhoneNumber();
    }
}
