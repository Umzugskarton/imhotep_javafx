package mainmenu.view;

import chat.view.ChatView;
import chat.view.ChatViewImpl;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import mainmenu.presenter.MainmenuPresenter;

public class MainmenuViewImpl implements MainmenuView {

    private Scene mainmenuScene;
    private MainmenuPresenter mainmenuPresenter;
    private Label userList;
    private BorderPane pane;
    private Tab chatTab;

    public MainmenuViewImpl() {
        buildMainmenu();
    }

    public void buildMainmenu() {
        GridPane grid = new GridPane();
        mainmenuScene = new Scene(grid);
        userList = new Label();
        grid.add(userList, 3, 5);
        pane = new BorderPane();
        mainmenuScene = new Scene(pane);
        TabPane tabPane = new TabPane();                                    //TabPane wird erstellt
        pane.setCenter(tabPane);                                            //TabPane wird auf BorderPane mittig platziert

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

        tabPane.getTabs().addAll(chatTab, gamesTab, profileTab, newGameTab, emptyTab);     //Tabs werden der TabPane der Reihe nach hinzugefügt
    }

    public void initPlayerList() {
        ListView<String> listView = new ListView<>();                                //ListView zum Anzeigen der eingeloggten Spieler
        listView.setItems(mainmenuPresenter.getPlayerList().getPlayers());                  //Liste der eingeloggten Spieler als Item der View setzen
        pane.setRight(listView);                                                            //ListView rechts auf der BorderPane platzieren
    }

    public void initChat(ChatView chatView) {
        this.chatTab.setContent((ChatViewImpl) chatView);
    }

    @Override
    public void setMainmenuPresenter(MainmenuPresenter mainmenuPresenter) {
        this.mainmenuPresenter = mainmenuPresenter;
    }

    public Scene getMainmenuScene() {
        return this.mainmenuScene;
    }
}

