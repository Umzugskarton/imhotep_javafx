package games.presenter;


import games.model.GameList;
import games.model.GameListImpl;
import games.model.Lobby;
import games.view.GamesView;
import games.view.GamesViewImpl;
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
        JSONParser parser = new JSONParser();

        for (Object game : gamesArray){
            JSONObject gameLobby = (JSONObject) game;
            int size = (int)(long)gameLobby.get("size");
            int id = (int)(long)gameLobby.get("lobbyid");
            int usercount = (int)(long) gameLobby.get("usercount");
            String name = (String) gameLobby.get("name");
            boolean haspw = (boolean) gameLobby.get("haspw");
            Lobby tempLobby= new Lobby(id, size, name, haspw, usercount);
            gamesList.getGames().add(tempLobby);
        }


    }

    public GameList getGameList() {
        return this.gamesList;
    }


}
