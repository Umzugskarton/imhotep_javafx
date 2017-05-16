package login.model;

/**
 * Created by mircoskrzipczyk, annkristinklopp on 09.05.17.
 */
public interface LoginModel {
    void Model();
    void setUsername(String user);
    void setPassword(String pass);
    String getUsername();
    String getPassword();

}
