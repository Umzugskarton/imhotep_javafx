package CLTrequests;

import java.util.Date;

/**
 * Created by Slothan/Dennis Lindt on 23.10.2017.
 */
public class changeCredentialRequest implements Request {
    private String request = "changeCredential";
    private Date date;
    private String email;
    private String username;


    public changeCredentialRequest() {
    }

    public changeCredentialRequest(String email) {
        this.email = email;
    }
    public String getRequest() { return this.request; }
    public Date getDate() { return this.date; }
    public String getEmail(){
        return this.email;
    }
    public String getUsername() { return this.username; }

}


