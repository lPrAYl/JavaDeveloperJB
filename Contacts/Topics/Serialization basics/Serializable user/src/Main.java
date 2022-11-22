import java.io.Serializable;

class User implements Serializable {
    private static final long serialVersionUID = 7L;

    String name;
    private transient String password;
}