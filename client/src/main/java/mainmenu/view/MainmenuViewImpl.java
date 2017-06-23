package mainmenu.view;

import chat.view.ChatView;
import chat.view.ChatViewImpl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import mainmenu.presenter.MainmenuPresenter;

public class MainmenuViewImpl implements MainmenuView {

    private Scene mainmenuScene;
    private MainmenuPresenter mainmenuPresenter;
    private Label userList;
    private BorderPane main;
    private Tab chatTab;

    public MainmenuViewImpl() {
        buildMainmenu();
    }

    public void buildMainmenu() {
        this.main = new BorderPane();
        main.setId("menuroot");
        GridPane grid = new GridPane();
        userList = new Label();
        grid.add(userList, 3, 5);
        TabPane tabPane = new TabPane();                                    //TabPane wird erstellt
        main.setCenter(tabPane);

        HBox nav = new HBox();
        nav.setId("nav");
        nav.setSpacing(10);
        nav.setAlignment(Pos.CENTER_RIGHT);
        nav.setPadding(new Insets(15, 15, 15, 12));
        main.setTop(nav);
        main.setRight(grid);

        Rectangle rect = new Rectangle(720,480);
        rect.setArcHeight(30.0);
        rect.setArcWidth(30.0);
        main.setClip(rect);
        mainmenuScene = new Scene(main);
        mainmenuScene.setFill(Color.TRANSPARENT);


        chatTab = new Tab();//neuer Tab wird erstellt
        chatTab.setText("Chat");                                            //Name des Tabs
        chatTab.setClosable(false);//Tab soll nicht schließbar sein
        chatTab.setTooltip(new Tooltip("chat with other players"));//wird angezeigt, wenn Maus sich über dem Tab befindet
        chatTab.setContent(null);

        Tab gamesTab = new Tab();
        gamesTab.setText("Games");
        gamesTab.setClosable(false);
        gamesTab.setTooltip(new Tooltip("find open games"));

        Tab profileTab = new Tab();
        profileTab.setText("Profile");
        profileTab.setClosable(false);
        profileTab.setTooltip(new Tooltip("edit your profile"));

        Tab newGameTab = new Tab();
        newGameTab.setText("Create Game");
        newGameTab.setClosable(false);
        newGameTab.setTooltip(new Tooltip("create a new game"));

        Tab rulesTab = new Tab();
        rulesTab.setText("Rules");
        rulesTab.setClosable(false);
        rulesTab.setTooltip(new Tooltip("learn the game"));

        Tab emptyTab = new Tab();
        emptyTab.setText("");
        emptyTab.setClosable(false);


        Button close = new Button("x");
        close.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        Button min =  new Button("_");
        min.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                mainmenuPresenter.getSceneController().getStage().setIconified(true);
            }
        });

        min.setMinWidth(20);
        close.setMinWidth(20);

        final Delta dragDelta = new Delta();
        nav.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                // record a delta distance for the drag and drop operation.
                dragDelta.x =  mainmenuPresenter.getSceneController().getStage().getX() - mouseEvent.getScreenX();
                dragDelta.y =  mainmenuPresenter.getSceneController().getStage().getY() - mouseEvent.getScreenY();
            }
        });
        nav.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                mainmenuPresenter.getSceneController().getStage().setX(mouseEvent.getScreenX() + dragDelta.x);
                mainmenuPresenter.getSceneController().getStage().setY(mouseEvent.getScreenY() + dragDelta.y);
            }
        });



        nav.getChildren().addAll(min,close);
        tabPane.getTabs().addAll(chatTab, gamesTab, profileTab, newGameTab, emptyTab);     //Tabs werden der TabPane der Reihe nach hinzugefügt
    }

    public void initPlayerList() {
        ListView<String> listView = new ListView<>();                                //ListView zum Anzeigen der eingeloggten Spieler
        listView.setItems(mainmenuPresenter.getPlayerList().getPlayers());                  //Liste der eingeloggten Spieler als Item der View setzen
        main.setRight(listView);                                                            //ListView rechts auf der BorderPane platzieren

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    String selectedUser = listView.getSelectionModel().getSelectedItem();
                    TextField messageInput = mainmenuPresenter.getChatPresenter().getChatView().getMessageInput();
                    messageInput.setText("@" + selectedUser + " ");
                    messageInput.requestFocus();
                    messageInput.end();
                }
            }
        });
    }

    public void initChat(ChatView chatView) {
        this.chatTab.setContent((ChatViewImpl) chatView);
    }

    @Override
    public void setMainmenuPresenter(MainmenuPresenter mainmenuPresenter) {
        this.mainmenuPresenter = mainmenuPresenter;
    }

    class Delta { double x, y; }


    public Scene getMainmenuScene() {
        return this.mainmenuScene;
    }
}

