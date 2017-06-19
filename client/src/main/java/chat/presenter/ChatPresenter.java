package chat.presenter;

/**
 * Created by maditamuller on 19.06.17.
 */
import json.ClientCommands;
import chat.view.ChatView;
import main.SceneController;
import org.json.simple.JSONObject;

public class ChatPresenter {

    private ChatView view;
    private SceneController sceneController;

    public ChatPresenter(ChatView view, SceneController sc) {
        this.view = view;
        this.sceneController = sc;
        view.setChatPresenter(this);
    }

}