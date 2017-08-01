package games.presenter;


import games.model.GameList;
import games.model.GameListImpl;
import games.model.Lobby;
import games.view.GamesView;
import games.view.GamesViewImpl;
import json.ClientCommands;
import main.SceneController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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

    public void updateLobbylist(JSONArray gamesArray){
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
            gamesList.getGames().add(new Lobby(id, size, name, haspw, usercount));
        }


    }

    public void joinLobby(int id, String pw){
            JSONObject join = ClientCommands.joinCommand(id, pw);
            sc.getClientSocket().send(join);
    }

    public GameList getGameList() {
        return this.gamesList;
    }


}
