package CLTrequests;

import java.util.Date;

/**
 * Created by Slothan on 16.11.2017.
 */
public class setReadyRequest extends Request {

    private String request = "setReady";

    public setReadyRequest(){}

    public setReadyRequest(int lobbyId){
        super(lobbyId);
    }

    public String getType() {
        return this.request;
    }

}
