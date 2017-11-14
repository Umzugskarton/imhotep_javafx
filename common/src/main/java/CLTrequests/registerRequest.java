package CLTrequests;

import java.util.Date;

public class registerRequest implements Request {
    private String request = "register";
    private Date date;
    private String username;
    private String pw;
    private String email;

    public registerRequest() {

    }

    public registerRequest(String username, String password, String email) {
        this.username = username;
        this.pw = password;
        this.email = email;
        this.date = new Date();
    }

    public String getType() {
        return this.request;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.pw;
    }

    public Date getDate() {
        return this.date;
    }
}
