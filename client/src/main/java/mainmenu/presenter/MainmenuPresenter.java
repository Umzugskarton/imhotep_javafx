package mainmenu.presenter;

import chat.presenter.ChatPresenter;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import json.ClientCommands;
import main.SceneController;
import mainmenu.model.PlayerList;
import mainmenu.model.PlayerListImpl;
import mainmenu.view.MainmenuView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MainmenuPresenter {

  private MainmenuView view;
  private SceneController sceneController;
  private PlayerList playerList;
  private ChatPresenter chatPresenter;

  public MainmenuPresenter(MainmenuView view, SceneController sc) {
    this.view = view;
    this.sceneController = sc;
    view.setMainmenuPresenter(this);

    //Reihenfolge wichtig, sonst NullPointerException!
    this.playerList = new PlayerListImpl();
    view.initPlayerList();

    this.chatPresenter = new ChatPresenter(this.sceneController);
    view.initChat(this.chatPresenter.getChatView());

    this.sceneController.getClientSocket().send(ClientCommands.userlistCommand());
  }

  public MainmenuView getMainmenuView() {
    return this.view;
  }

  public void updateUserlist(JSONArray userArray) {
    // Im Chat informieren wer gejoined/leaved ist
    boolean notifyInChat = true;
    if (playerList.getPlayers().isEmpty()) {
      notifyInChat = false;
    }

    if (notifyInChat) {
      List<String> list = playerList.getPlayers();
      List<String> joinedList = new ArrayList<>();
      List<String> leftList = new ArrayList<>();

      for (Object user : userArray) {
        joinedList.add(user.toString());
        leftList.add(user.toString());
      }

      joinedList.removeAll(list);
      list.removeAll(leftList);

      for (String username : list) {
        this.chatPresenter.addInfoMessage("- " + username + " hat den Chat verlassen", Color.RED);
      }

      for (String username : joinedList) {
        this.chatPresenter.addInfoMessage("+ " + username + " hat den Chat betreten", Color.GREEN);
      }
    }

    // Userliste leeren und neu füllen
    playerList.getPlayers().clear();

    for (Object user : userArray) {
      playerList.getPlayers().add(user.toString());
    }
  }

  public void toLoginScene() {
    sceneController.toLoginScene();
  }

  public PlayerList getPlayerList() {
    return this.playerList;
  }

  public void logout(){
    JSONObject logoutCommand = ClientCommands.logoutCommand();
    this.sceneController.getClientSocket().send(logoutCommand);
    this.chatPresenter.getChatView().getChatText().getChildren().clear();
  }

  public SceneController getSceneController() {
    return this.sceneController;
  }

  public ChatPresenter getChatPresenter() {
    return this.chatPresenter;
  }
}
