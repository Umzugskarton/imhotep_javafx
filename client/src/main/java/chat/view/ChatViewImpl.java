package chat.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import chat.presenter.ChatPresenter;

import java.io.IOException;

public class ChatViewImpl implements ChatView {

    private Scene chatScene;
    public ChatPresenter chatPresenter;
    public TextArea msgFld; //nachrichtenverlauf


    public ChatViewImpl() {
        buildChat();
    }

    public void buildChat() {
        GridPane root = new GridPane();
        chatScene = new Scene(root);
        TextField message = new TextField();
        ScrollPane scrollPane1 = new ScrollPane();
        TextArea msgFld = new TextArea();
        this.msgFld = msgFld;
        message.setPromptText("Nachricht eingeben");
        Button buttonPressed = new Button("Senden");
        buttonPressed.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                chatPresenter.sendMsg(message.getText());
            }

        });


        //Positionen

        root.setPrefSize(300, 300);
        ColumnConstraints column3 = new ColumnConstraints(30); // Breite der dritten
        ColumnConstraints column1 = new ColumnConstraints(100); // Breite der ersten Spalte
        root.add(message, 2, 1);
        root.add(msgFld, 3, 1);
        root.add(buttonPressed, 1, 3);

    }

    public void onClickSend() {
/*
        msg = message.getText();
        System.out.println("name" + " : " + msg);
        message.setText("");
        message.requestFocus();*/


    }

    public void buttonPressed(KeyEvent e) {
        if (e.getCode().toString().equals("ENTER")) {
            onClickSend();
        }
    }
    public void setChatPresenter(ChatPresenter chatPresenter) {
        this.chatPresenter = chatPresenter;
    }


    public TextArea getMsgFld(){
        return this.msgFld;
    }

    public Scene getChatScene() {
        return this.chatScene;
    }


}


