package chat.presenter;

/**
 * Created by maditamuller on 19.06.17.
 */

import json.ClientCommands;
import chat.view.ChatView;
import chat.view.ChatViewImpl;
import main.SceneController;
import org.json.simple.JSONObject;

public class ChatPresenter {
    private SceneController sceneController;
    private ChatView chatView;

    public ChatPresenter(ChatView view, SceneController sc) {
        this.chatView = view;
        this.sceneController = sc;
        this.chatView = new ChatViewImpl();
        chatView.setChatPresenter(this);
    }

    public ChatView getChatView(){
        return this.chatView;
    }

    public void sendMsg(String text){
        JSONObject chatCommand = ClientCommands.chatCommand(this.sceneController.getUsername(), text);
        this.sceneController.getClientSocket().send(chatCommand);
    }

    public void updateChat(String user, String msg){
        this.chatView.getMsgFld().setText(user+": " + msg);
    }
}