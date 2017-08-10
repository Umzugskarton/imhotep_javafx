package lobby.presenter;


import lobby.model.Lobby;
import lobby.view.LobbyView;
import main.SceneController;

public class LobbyPresenter {
    private SceneController sc;
    private LobbyView lobbyView;
    private Lobby lobby;

    public LobbyPresenter (LobbyView lobbyView, SceneController sc){
        this.sc= sc;
        this.lobbyView = lobbyView;
        lobbyView.setLobbyPresenter(this);

    }

    public Lobby getLobby(){
        return this.lobby;
    }

    public LobbyView getLobbyView(){
        return this.lobbyView;
    }

    public SceneController getSceneController(){
        return this.sc;
    }
}
