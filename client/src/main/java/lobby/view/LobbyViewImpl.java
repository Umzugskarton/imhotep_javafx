package lobby.view;

import general.Delta;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lobby.presenter.LobbyPresenter;


public class LobbyViewImpl implements LobbyView{
    private Scene LobbyScene;
    private LobbyPresenter lobbyPresenter;
    private Label userList;
    private BorderPane main;
    private TextField messageField;

    public LobbyViewImpl() {
        buildLobby();
    }

    void buildLobby() {
        this.main = new BorderPane();
        main.setId("menuroot");
        GridPane grid = new GridPane();
        userList = new Label();
        grid.add(userList, 3, 5);

        HBox nav = new HBox();
        nav.setId("nav");
        nav.setSpacing(10);
        nav.setAlignment(Pos.CENTER_RIGHT);
        nav.setPadding(new Insets(15, 15, 15, 12));
        main.setTop(nav);
        main.setCenter(grid);

        Rectangle rect = new Rectangle(720, 480);
        rect.setArcHeight(30.0);
        rect.setArcWidth(30.0);
        main.setClip(rect);
        LobbyScene = new Scene(main);
        LobbyScene.setFill(Color.TRANSPARENT);

        messageField = new TextField();
        grid.add(messageField, 0 ,7);

        Button close = new Button("x");
        close.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        Button min = new Button("_");
        min.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                lobbyPresenter.getSceneController().getStage().setIconified(true);
            }
        });

        min.setMinWidth(20);
        close.setMinWidth(20);

        final Delta dragDelta = new Delta();
        nav.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // record a delta distance for the drag and drop operation.
                dragDelta.x =
                        lobbyPresenter.getSceneController().getStage().getX() - mouseEvent.getScreenX();
                dragDelta.y =
                        lobbyPresenter.getSceneController().getStage().getY() - mouseEvent.getScreenY();
            }
        });
        nav.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                lobbyPresenter.getSceneController().getStage()
                        .setX(mouseEvent.getScreenX() + dragDelta.x);
                lobbyPresenter.getSceneController().getStage()
                        .setY(mouseEvent.getScreenY() + dragDelta.y);
            }
        });

        nav.getChildren().addAll(min, close);
    }

    public void initUserList() {
        ListView<String> listView = new ListView<>();
        listView.setItems(lobbyPresenter.getLobby().getUsers());
        main.setRight(listView);

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    String selectedUser = listView.getSelectionModel().getSelectedItem();
                    TextField messageInput = messageField;
                    messageInput.setText("@" + selectedUser + " ");
                    messageInput.requestFocus();
                    messageInput.end();
                }
            }
        });
    }


    public void openModal(String msg){
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getStylesheets().add("style.css");
        dialogVbox.getStyleClass().add("dialog");
        Label info= new Label(msg);
        Button confirm = new Button("OK");
        confirm.addEventHandler(ActionEvent.ACTION , event -> dialog.close());
        dialogVbox.getChildren().addAll(new Text("Message:"),info, confirm);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    public void setLobbyPresenter(LobbyPresenter lobbyPresenter) {
        this.lobbyPresenter = lobbyPresenter;
    }

    public Scene getLobbyScene() {
        return this.LobbyScene;
    }
}
