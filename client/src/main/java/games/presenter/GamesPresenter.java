package games.presenter;


import CLTrequests.joinRequest;
import games.model.GameList;
import games.model.GameListImpl;
import commonLobby.CLTLobby;
import games.view.GamesView;
import games.view.GamesViewImpl;
import main.SceneController;
import java.util.ArrayList;


public class GamesPresenter {
    SceneController sc;
    GamesView view;
    GameList gamesList;

    public GamesPresenter(SceneController sc){
        this.sc=sc;

        this.gamesList = new GameListImpl();

        this.view= new GamesViewImpl(this);
        view.initGameList();
    }

    public GamesView getGamesView(){
        return this.view;
    }

    public void updateLobbylist(ArrayList<CLTLobby> gamesArray){
        gamesList.getGames().clear();

        for (CLTLobby lobby: gamesArray){
            gamesList.getGames().add(lobby);
        }

    }

    public void joinLobby(int id, String pw){
            joinRequest join = new joinRequest(id, pw);
            sc.getClientSocket().send(join);
    }

    public GameList getGameList() {
        return this.gamesList;
    }

}