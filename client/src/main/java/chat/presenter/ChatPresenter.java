package chat.presenter;

import chat.view.ChatView;
import chat.view.ChatViewImpl;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import json.ClientCommands;
import main.SceneController;
import org.json.simple.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatPresenter {
    private ChatView view;
    private SceneController sceneController;

    public ChatPresenter(SceneController sc) {
        this.sceneController = sc;

        this.view = new ChatViewImpl(this);
    }

    public void sendChatMsg(String text) {
        JSONObject chatCommand = null;

        if (text.startsWith("/w") || text.startsWith("@")) {
            Pattern whisperPattern = Pattern.compile("(\\/w |@)([^\\s]+) (.+)");
            Matcher whisperMatcher = whisperPattern.matcher(text);
            if (whisperMatcher.find()) {
                String receiver = whisperMatcher.group(2);
                String message = whisperMatcher.group(3);

                chatCommand = ClientCommands.whisperCommand(receiver, message);
                addWhisper(receiver, message, false);
            } else {
                addInfoMessage("Invalide Whisper-Syntax: /w <Benutzername> <Nachricht>");
            }
        } else if (!text.isEmpty()) {
            chatCommand = ClientCommands.chatCommand(text);
        } else if (text.isEmpty()) {
            addInfoMessage("Bitte gib eine Nachricht ein, um zu chatten");
        }

        if (chatCommand != null) this.sceneController.getClientSocket().send(chatCommand);
    }

    public void addChatMessage(String user, String msg) {
        Text userText = new Text(user + ": ");
        userText.setStyle("-fx-font-weight: bold");
        Text messageText = new Text(msg + "\n");

        this.view.getChatText().getChildren().addAll(userText, messageText);
    }

    public void addWhisper(String user, String msg, boolean isClientReceiver) {
        String recipientText = "From";
        Color color = Color.web("#8A2BE2");

        if (!isClientReceiver) {
            recipientText = "To";
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

    public void addInfoMessage(String msg) {
        addInfoMessage(msg, Color.GRAY);
    }

    public ChatView getChatView() {
        return this.view;
    }
}
