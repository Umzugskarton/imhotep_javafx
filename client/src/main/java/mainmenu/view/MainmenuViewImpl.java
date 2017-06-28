package mainmenu.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import mainmenu.presenter.MainmenuPresenter;

public class MainmenuViewImpl implements MainmenuView {

  private Scene mainmenuScene;
  private MainmenuPresenter mainmenuPresenter;
  private Label userList;
  private BorderPane pane;

  public MainmenuViewImpl() { buildMainmenu(); }

  public void buildMainmenu() {
    GridPane grid = new GridPane();
    mainmenuScene = new Scene(grid);
    userList = new Label();
    grid.add(userList, 3, 5);
    pane = new BorderPane();
    mainmenuScene = new Scene(pane);
    TabPane tabPane = new TabPane();                                    //TabPane wird erstellt
    pane.setCenter(tabPane);                                            //TabPane wird auf BorderPane mittig platziert

    HBox menu = new HBox();
    Button profile = new Button("Profile");
    Button rules = new Button("Rules");
    Button logout = new Button("Logout");
    logout.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        mainmenuPresenter.toLoginScene();
        mainmenuPresenter.logout();
      }
    });
    menu.getChildren().addAll(profile, rules, logout);
    pane.setTop(menu);

    Tab chatTab = new Tab();                                            //neuer Tab wird erstellt
    chatTab.setText("Chat");                                            //Name des Tabs
    chatTab.setClosable(false);                                         //Tab soll nicht schließbar sein
    chatTab.setTooltip(new Tooltip("chat with other players"));    //wird angezeigt, wenn Maus sich über dem Tab befindet

    Tab gamesTab = new Tab();
    gamesTab.setText("Games");
    gamesTab.setClosable(false);
    gamesTab.setTooltip(new Tooltip("find open games"));

    Tab scoresTab = new Tab();
    scoresTab.setText("Highscores");
    scoresTab.setClosable(false);



    tabPane.getTabs().addAll(chatTab, gamesTab, scoresTab);     //Tabs werden der TabPane der Reihe nach hinzugefügt
  }
    public void initPlayerList(){
    ListView<String> listView = new ListView<String>();                                //ListView zum Anzeigen der eingeloggten Spieler
    listView.setItems(mainmenuPresenter.getPlayerList().getPlayers());                  //Liste der eingeloggten Spieler als Item der View setzen
    pane.setRight(listView);                                                            //ListView rechts auf der BorderPane platzieren
  }

  @Override
  public void setMainmenuPresenter(MainmenuPresenter mainmenuPresenter) {
    this.mainmenuPresenter = mainmenuPresenter;
  }

  public Scene getMainmenuScene() {
    return this.mainmenuScene;
  }
}

