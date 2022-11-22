import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;

class UserProfile implements Serializable {
    private static final long serialVersionUID = 26292552485L;

    private String login;
    private String email;
    private transient String password;

    public UserProfile(String login, String email, String password) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    private String decrypt(String password) {
        StringBuilder decryptPassword = new StringBuilder();

        for (int i = 0; i < password.length(); i++) {
            decryptPassword.append((char) (password.charAt(i) - 1));
        }

        return decryptPassword.toString();
    }

    private String encrypt(String password) {
        StringBuilder encryptPassword = new StringBuilder();

        for (int i = 0; i < password.length(); i++) {
            encryptPassword.append((char) (password.charAt(i) + 1));
        }

        return encryptPassword.toString();
    }

    // implement readObject and writeObject properly
    @Serial
    private void writeObject(ObjectOutputStream oos) throws Exception {
        oos.defaultWriteObject();
        String encryptPassword = encrypt(password);
        oos.writeObject(encryptPassword);
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws Exception {
        ois.defaultReadObject();
        password = decrypt((String) ois.readObject());
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}