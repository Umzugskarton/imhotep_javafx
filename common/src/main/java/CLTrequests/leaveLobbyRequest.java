package CLTrequests;

import java.util.Date;

/**
 * Created by Slothan on 18.12.2017.
 */
public class leaveLobbyRequest extends Request {

    private String request = "leaveLobby";
    private Date date;

    public leaveLobbyRequest(){}

    public leaveLobbyRequest(int lobbyId) {
       super(lobbyId);

    }

    public String getType() {
        return this.request;
    }

    public Date getDate() {
        return this.date;
    }

}
