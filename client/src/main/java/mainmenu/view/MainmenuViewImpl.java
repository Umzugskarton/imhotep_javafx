package mainmenu.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import mainmenu.presenter.MainmenuPresenter;

public class MainmenuViewImpl implements MainmenuView {

  private Scene mainmenuScene;
  private MainmenuPresenter mainmenuPresenter;
  private Label userList;
  private BorderPane pane;

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
    TabPane tabPane = new TabPane();
    pane.setCenter(tabPane);

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

    Tab chatTab = new Tab();
    chatTab.setText("Chat");
    chatTab.setClosable(false);
    chatTab.setTooltip(new Tooltip("chat with other players"));

    Tab gamesTab = new Tab();
    gamesTab.setText("Games");
    gamesTab.setClosable(false);
    gamesTab.setTooltip(new Tooltip("find open games"));

    Tab scoresTab = new Tab();
    scoresTab.setText("Highscores");
    scoresTab.setClosable(false);

    tabPane.getTabs().addAll(chatTab, gamesTab, scoresTab);
  }

  public void initPlayerList() {
    ListView<String> listView = new ListView<String>();
    listView.setItems(mainmenuPresenter.getPlayerList().getPlayers());
    pane.setRight(listView);
  }

  @Override
  public void setMainmenuPresenter(MainmenuPresenter mainmenuPresenter) {
    this.mainmenuPresenter = mainmenuPresenter;
  }

  public Scene getMainmenuScene() {
    return this.mainmenuScene;
  }
}

