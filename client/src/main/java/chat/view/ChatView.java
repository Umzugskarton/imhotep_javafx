package chat.view;

import javafx.scene.text.TextFlow;

public interface ChatView {
    void buildChat();

    TextFlow getChatText();
}
