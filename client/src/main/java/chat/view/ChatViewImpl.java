package chat.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import chat.presenter.ChatPresenter;



/**
 * Created by maditamuller on 19.06.17.
 */
public class ChatViewImpl implements ChatView {

        private Scene chatScene;
        private ChatPresenter chatPresenter;

        public ChatViewImpl() {
            buildChat();
        }

        public void buildChat() {
            GridPane grid = new GridPane();
            chatScene = new Scene(grid);

            TextField chatPartner = new TextField();
            ScrollPane scrollPane1 = new ScrollPane();
            TextArea chatHistory = new TextArea();
            ScrollPane scrollpane2 = new ScrollPane();
            TextArea typeMessage = new TextArea();
            typeMessage.setPromptText("Nachricht eingeben");
            Button sendButton = new Button("Senden");
            sendButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    updateChatHistory(typeMessage.getText());
                    //Senden an den Server fehlt noch
                }
            });


            //Positionen fehlen

        }


        public void updateChatHistory(String text) {

            //Methode zu Updaten des Chatverlaufes fehlt
        }

        public Scene getChatScene() {
            return this.chatScene;
        }

        @Override
        public void setChatPresenter(ChatPresenter chatPresenter) {
            this.chatPresenter = chatPresenter;
        }
    }
}
