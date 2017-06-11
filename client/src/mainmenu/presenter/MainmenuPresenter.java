package mainmenu.presenter;

import json.ClientCommands;
import main.SceneController;
import mainmenu.model.PlayerList;
import mainmenu.model.PlayerListImpl;
import mainmenu.view.MainmenuView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import mainmenu.view.MainmenuViewImpl;

/**
 * Created by mirco, kristin on 28.05.2017.
 */
public class MainmenuPresenter {

  private MainmenuView view;
  private SceneController sceneController;
  private PlayerList playerList;

    public MainmenuPresenter(MainmenuView view, SceneController sc) {
      this.view = view;
      this.sceneController = sc;
      view.setMainmenuPresenter(this);
      this.playerList = new PlayerListImpl();   //Reihenfolge wichtig, sonst NullPointerException!
      view.initPlayerList();
      this.sceneController.getClientSocket().send(ClientCommands.userlistCommand());
    }

  public MainmenuView getMainmenuView() {
    return this.view;
  }

  public void updateUserlist(JSONArray userArray) {
      playerList.getPlayers().clear();

      for(Object user : userArray) {
        playerList.getPlayers().add(user.toString());
      }
  }

  public void toLoginScene() {
    sceneController.toLoginScene();
  }

  public PlayerList getPlayerList(){
    return this.playerList;
  }

}
