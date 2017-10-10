package CLTrequests;

import java.util.Date;

/**
 * Created on 10.10.2017.
 */
public class registerRequest implements Request {
    private String command = "register";
    private Date date;
    private String username;
    private String pw;
    private String email;

    public registerRequest(){

    }

    public registerRequest(String username, String password, String email){
        this.username = username;
        this.pw = password;
        this.email = email;
        this.date = new Date();
    }

    public String getCommand(){
        return this.command;
    }

    public String getUsername(){
        return this.username;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.pw;
    }

    public Date getDate(){
        return this.date;
    }
}
