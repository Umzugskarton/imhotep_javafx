package login.model;

/**
 * Created by mircoskrzipczyk, annkristinklopp on 09.05.17.
 */
public class LoginModelImpl implements LoginModel {

    private String password;
    private String username;

    public void Model() {
        password = "password";
        username = "username";

    }


    public void setPassword (String pass) {

        password = pass;
    }

    public void setUsername (String user) {

        username = user;
    }

    public String getPassword() {

        return password;
    }

    public String getUsername() {

        return username;
    }

}
