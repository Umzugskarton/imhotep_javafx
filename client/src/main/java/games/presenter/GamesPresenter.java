package games.presenter;


import requests.JoinRequest;
import games.model.GameList;
import games.model.GameListImpl;
import data.lobby.CommonLobby;
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

    public void updateLobbylist(ArrayList<CommonLobby> gamesArray){
        gamesList.getGames().clear();

        for (CommonLobby lobby: gamesArray){
            gamesList.getGames().add(lobby);
        }

    }

    public void joinLobby(int id, String pw){
            JoinRequest join = new JoinRequest(id, pw);
            sc.getClientSocket().send(join);
    }

    public GameList getGameList() {
        return this.gamesList;
    }

}
