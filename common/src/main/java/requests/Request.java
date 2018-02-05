package requests;

public abstract class Request implements IRequest {
    protected int lobbyId;

    public Request() {
    }

    public Request(int lobbyId) {
        this.lobbyId = lobbyId;
    }

    public int getLobbyId() {
        return lobbyId;
    }
}
