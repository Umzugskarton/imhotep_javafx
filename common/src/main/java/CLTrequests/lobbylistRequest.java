package CLTrequests;

import java.util.Date;

/**
 * Created on 13.10.2017.
 */
public class lobbylistRequest {
    private String request = "lobbylist";
    private Date date;
    private int id;
    private String pw;

    public lobbylistRequest(){

    }

    public lobbylistRequest(int id, String password){
        this.id = id;
        this.pw = password;
        this.date = new Date();
    }

    public String getRequest(){
        return this.request;
    }

    public int getId(){
        return this.id;
    }

    public String getPassword(){
        return this.pw;
    }

    public Date getDate(){
        return this.date;
    }

}
