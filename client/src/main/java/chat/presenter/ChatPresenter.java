package chat.presenter;

import chat.view.ChatView;
import chat.view.ChatViewImpl;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import json.ClientCommands;
import main.SceneController;
import org.json.simple.JSONObject;

public class ChatPresenter {
    private ChatView view;
    private SceneController sceneController;

    public ChatPresenter(SceneController sc) {
        this.sceneController = sc;

        this.view = new ChatViewImpl(this);
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

        this.view.getChatText().getChildren().addAll(userText, messageText);
    }

    public void addMessage(String msg, Color color) {
        Text text = new Text(msg.toUpperCase() + "\n");
        text.setFill(color);
        text.setFont(new Font(null, 10));

        this.view.getChatText().getChildren().add(text);
    }

    public void addMessage(String msg) {
        addMessage(msg, Color.GRAY);
    }

    public ChatView getChatView() { return this.view; }
}
