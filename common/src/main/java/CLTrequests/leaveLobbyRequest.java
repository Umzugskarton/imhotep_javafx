package CLTrequests;

import java.util.Date;

/**
 * Created by Slothan on 18.12.2017.
 */
public class leaveLobbyRequest implements Request {

    private String request = "leaveLobby";
    private int id;

    public leaveLobbyRequest() {

    }

    public leaveLobbyRequest(int id) {
        this.id = id;

    }

    public String getType() {
        return this.request;
    }


    public Integer getId() {
        return this.id;
    }
}
