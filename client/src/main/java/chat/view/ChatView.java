package chat.view;

import chat.presenter.ChatPresenter;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;

/**
 * Created by maditamuller on 19.06.17.
 */
public interface ChatView {

    public void buildChat();

    public Scene getChatScene();

    public void setChatPresenter(ChatPresenter t);
    public TextArea getMsgFld();
}
