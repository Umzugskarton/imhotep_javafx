package mainmenu.presenter;

import CLTrequests.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import create.presenter.CreatePresenter;
import games.presenter.GamesPresenter;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import lobby.presenter.LobbyPresenter;
import main.SceneController;
import mainmenu.model.PlayerList;
import mainmenu.model.PlayerListImpl;

import mainmenu.view.MainmenuViewImplFx;


import static general.TextBundle.getString;

public class MainmenuPresenter {

  private MainmenuViewImplFx view;
  private SceneController sceneController;
  private PlayerList playerList;
  private String username;
  private CreatePresenter createPresenter;
  private GamesPresenter gamesPresenter;
  private MainmenuPresenter mainmenuPresenter;

  public MainmenuPresenter(MainmenuViewImplFx view, SceneController sc) {
    this.view = view;
    this.sceneController = sc;
    view.setMainmenuPresenter(this);

    //Reihenfolge wichtig, sonst NullPointerException!
    this.playerList = new PlayerListImpl();
    view.initPlayerList();

    this.createPresenter = new CreatePresenter(this.sceneController);
    view.initCreate(this.createPresenter.getCreateView());

    this.gamesPresenter = new GamesPresenter(this.sceneController);
    view.initGames(this.gamesPresenter.getGamesView());


    this.sceneController.getClientSocket().send(new userlistRequest());
  }

  public MainmenuViewImplFx getMainmenuView() {
    return this.view;
  }

  public void updateUserlist(ArrayList<String> userArray) {
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
        this.addInfoMessage("- " + username + " hat den Chat verlassen", Color.RED);
      }

      for (String username : joinedList) {
        this.addInfoMessage("+ " + username + " hat den Chat betreten", Color.GREEN);
      }
    }

    // Userliste leeren und neu füllen
    playerList.getPlayers().clear();

    for (Object user : userArray) {
      playerList.getPlayers().add(user.toString());
    }
  }

  //Chat
  public void sendChatMsg(String text) {
    Request chatCommand = null;

    if (text.startsWith("/w") || text.startsWith("@")) {
      Pattern whisperPattern = Pattern.compile("(\\/w |@)([^\\s]+) (.+)");
      Matcher whisperMatcher = whisperPattern.matcher(text);
      if (whisperMatcher.find()) {
        String receiver = whisperMatcher.group(2);
        String message = whisperMatcher.group(3);

        chatCommand = new whisperRequest(receiver, message);
        addWhisper(receiver, message, false);
      } else {
        addInfoMessage(getString("invalidWhisperSyntax"));
      }
    } else if (!text.isEmpty()) {
      chatCommand = new chatRequest(text);
    } else if (text.isEmpty()) {
      addInfoMessage(getString("enterMessageToChat"));
    }

    if (chatCommand != null) {
      this.sceneController.getClientSocket().send(chatCommand);
    }
  }

  public void addChatMessage(String user, String msg) {
    Text userText = new Text(user + ": ");
    userText.setStyle("-fx-font-weight: bold");
    Text messageText = new Text(msg + "\n");

    this.view.getChatText().getChildren().addAll(userText, messageText);
  }

  public void addWhisper(String user, String msg, boolean isClientReceiver) {
    String recipientText = getString("from");
    Color color = Color.web("#8A2BE2");

    if (!isClientReceiver) {
      recipientText = getString("to");
      color = Color.web("#9c31ff");
    }

    Text userText = new Text(recipientText + " @" + user + ": ");
    userText.setStyle("-fx-font-weight: bold");
    userText.setFill(color);
    Text messageText = new Text(msg + "\n");
    messageText.setFill(color);

    this.view.getChatText().getChildren().addAll(userText, messageText);
  }

  public void addInfoMessage(String msg, Color color) {
    Text text = new Text(msg.toUpperCase() + "\n");
    text.setFill(color);
    text.setFont(new Font(null, 10));

    this.view.getChatText().getChildren().add(text);
  }

//Profil

  public void processChangeResponse(boolean validate, String msg) {
    if (validate) {
      this.view.updateStatusLabel(msg);
    } else {
      this.view.updateStatusLabel("Etwas lief schief.");
    }
  }

  public void updateUsernameLabel(String username) {
    this.view.getUsernameLabel().setText("Username: " + username);
  }

  public void updateEmailLabel(String email) {
    this.view.getEmailLabel().setText("Email: " + email);
  }

  public void sendChangeRequest(String credential, Integer type) {
    if (type == 1) {
      if (this.validate(credential)) {
        this.view.updateStatusLabel("");
        changeCredentialRequest changeCredentialsCommand = new changeCredentialRequest(credential,
                type);
        this.sceneController.getClientSocket().send(changeCredentialsCommand);
      }
    }
    if (type == 2) {
      this.view.updateStatusLabel("");
      changeCredentialRequest changeCredentialsCommand = new changeCredentialRequest(credential,
              type);
      this.sceneController.getClientSocket().send(changeCredentialsCommand);
    }
    if (type == 3) {
      this.view.updateStatusLabel("");
      changeCredentialRequest changeCredentialsCommand = new changeCredentialRequest(credential,
              type);
      this.sceneController.getClientSocket().send(changeCredentialsCommand);
    }

  }

  private boolean validate(String email) {
    String msg = "";
    if (!email.isEmpty()) {
      return true;
    } else {
      msg += "Bitte eine E-Mail eingeben. \n";
    }
    this.view.updateStatusLabel(msg);
    return false;
  }

  public void toLoginScene() {
    sceneController.toLoginScene();
  }

  public PlayerList getPlayerList() {
    return this.playerList;
  }

  public void logout() {
    this.sceneController.getClientSocket().send(new logoutRequest());
    this.mainmenuPresenter.getMainmenuView().getChatText().getChildren().clear();
  }

  public SceneController getSceneController() {
    return this.sceneController;
  }

  public GamesPresenter getGamesPresenter() {
    return this.gamesPresenter;
  }

  public void setUsername(String username) { this.username = username; }

  public String getUsername() { return username; }

  public void addInfoMessage(String msg) {
    addInfoMessage(msg, Color.GRAY);
  }
}
