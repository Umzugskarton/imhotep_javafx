package chat.view;

import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;

public interface ChatView {
    void buildChat();

    TextFlow getChatText();

    TextField getMessageInput();
}
