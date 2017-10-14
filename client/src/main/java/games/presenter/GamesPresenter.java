package games.presenter;


import CLTrequests.joinRequest;
import games.model.GameList;
import games.model.GameListImpl;
import commonLobby.CLTLobby;
import games.view.GamesView;
import games.view.GamesViewImpl;
import main.SceneController;
import org.json.simple.JSONObject;

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

    public void updateLobbylist(ArrayList gamesArray){
        gamesList.getGames().clear();
        int size;
        int id;
        int usercount;
        String name;
        boolean haspw;
        for (Object game : gamesArray){
            JSONObject gameLobby = (JSONObject) game;
            size = (int)(long)gameLobby.get("size");
            id = (int)(long)gameLobby.get("lobbyid");
            usercount = (int)(long) gameLobby.get("usercount");
            name = (String) gameLobby.get("name");
            haspw = (boolean) gameLobby.get("haspw");
            gamesList.getGames().add();
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
