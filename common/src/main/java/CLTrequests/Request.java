package CLTrequests;

/**
 * Created by Nils on 24.01.18.
 */
public abstract class Request implements IRequest{
    protected int lobbyId;

    public Request(int lobbyId){
        this.lobbyId = lobbyId;
    }

    public int getLobbyId(){
        return lobbyId;
    }
}
