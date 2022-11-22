class User {
    private String firstName;
    private String lastName;

    public User() {
        this.firstName = "";
        this.lastName = "";
    }

    public void setFirstName(String firstName) {
        if (!(firstName == null || firstName.equals(""))) {
            this.firstName = firstName;
        }
    }

    public void setLastName(String lastName) {
        if (!(lastName == null || lastName.equals(""))) {
            this.lastName = lastName;
        }    }

    public String getFullName() {
        if (firstName.equals("") && lastName.equals("")) {
            return "Unknown";
        } else if (firstName.equals("")) {
            return lastName;
        } else if (lastName.equals("")) {
            return firstName;
        }

        return firstName + " " + lastName;
    }
}