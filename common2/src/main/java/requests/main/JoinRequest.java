package requests.main;

/**
 * Created on 13.10.2017.
 */
public class JoinRequest extends MainRequest {
    private String request = "join";
    private int id;
    private String pw;

    public JoinRequest() {

    }

    public JoinRequest(int id, String password) {
        this.id = id;
        this.pw = password;
    }

    public int getId() {
        return this.id;
    }

    public String getPassword() {
        return this.pw;
    }
}
