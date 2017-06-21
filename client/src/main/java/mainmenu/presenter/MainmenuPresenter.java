package mainmenu.presenter;

import javafx.scene.paint.Color;
import javafx.scene.text.*;
import json.ClientCommands;
import main.SceneController;
import chat.view.ChatView;
import chat.view.ChatViewImpl;
import mainmenu.model.PlayerList;
import mainmenu.model.PlayerListImpl;
import mainmenu.view.MainmenuView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MainmenuPresenter {

    private MainmenuView view;
    private SceneController sceneController;
    private PlayerList playerList;
    private ChatView chatView;

    public MainmenuPresenter(MainmenuView view, SceneController sc) {
        this.view = view;
        this.sceneController = sc;
        view.setMainmenuPresenter(this);

        this.playerList = new PlayerListImpl();   //Reihenfolge wichtig, sonst NullPointerException!
        view.initPlayerList();

        this.chatView = new ChatViewImpl(this);
        view.initChat(chatView);

        this.sceneController.getClientSocket().send(ClientCommands.userlistCommand());
    }

    public MainmenuView getMainmenuView() {
        return this.view;
    }

    public void updateUserlist(JSONArray userArray) {
        playerList.getPlayers().clear();

        for (Object user : userArray) {
            playerList.getPlayers().add(user.toString());
        }
    }

    public void sendChatMsg(String text) {
        if (!text.isEmpty()) {
            JSONObject chatCommand = ClientCommands.chatCommand(text);
            this.sceneController.getClientSocket().send(chatCommand);
        }
    }

    public void addChatMessage(String user, String msg) {
        Text userText = new Text(user + ": ");
        userText.setStyle("-fx-font-weight: bold");
        Text messageText = new Text(msg + "\n");

        this.chatView.getChatText().getChildren().addAll(userText, messageText);
    }

    public void addChatMessage(String msg) {
        Text text = new Text(msg + "\n");
        text.setFill(Color.CORNFLOWERBLUE);
        text.setStyle("-fx-font-style: italic;");

        this.chatView.getChatText().getChildren().add(text);
    }

    public void toLoginScene() {
        sceneController.toLoginScene();
    }

    public PlayerList getPlayerList() {
        return this.playerList;
    }
}
