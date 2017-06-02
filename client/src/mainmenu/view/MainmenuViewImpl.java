package mainmenu.view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import mainmenu.presenter.MainmenuPresenter;

/**
 * Created by mirco, kristin on 28.05.2017.
 */
public class MainmenuViewImpl implements MainmenuView {

  private ObservableList <String> players = FXCollections.observableArrayList();

  private Scene mainmenuScene;
  private MainmenuPresenter mainmenuPresenter;

  public MainmenuViewImpl() { buildMainmenu(); }

  public void buildMainmenu() {
    BorderPane pane = new BorderPane();
    mainmenuScene = new Scene(pane);
    TabPane tabPane = new TabPane();
    pane.setCenter(tabPane);

    Tab chatTab = new Tab();
    chatTab.setText("Chat");
    chatTab.setClosable(false);
    chatTab.setTooltip(new Tooltip("chat with other players"));

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

    Tab logoutTab = new Tab();
    logoutTab.setText("Logout");
    logoutTab.setClosable(false);

    tabPane.getTabs().addAll(chatTab, gamesTab, profileTab, newGameTab, logoutTab);

    ListView <String> listView = new ListView <String>();
    listView.setItems(players);
    pane.setRight(listView);
    players.add("Test");
  }

  @Override
  public void setMainmenuPresenter(MainmenuPresenter mainmenuPresenter) {
    this.mainmenuPresenter = mainmenuPresenter;
  }


  public Scene getMainmenuScene() {
    return this.mainmenuScene;
  }

  public ObservableList<String> getPlayers(){
    return this.players;
  }
}

