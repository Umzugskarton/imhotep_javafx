package mainmenu.view;

import chat.view.ChatView;
import chat.view.ChatViewImpl;
import create.view.CreateView;
import create.view.CreateViewImpl;
import games.view.GamesView;
import games.view.GamesViewImpl;
import general.Delta;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
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
import mainmenu.presenter.MainmenuPresenter;
import profile.view.ProfileView;
import profile.view.ProfileViewImpl;

public class MainmenuViewImpl implements MainmenuView {

  private Scene mainmenuScene;
  private MainmenuPresenter mainmenuPresenter;
  private Label userList;
  private BorderPane main;
  private Tab chatTab;
  private Tab newGameTab;
  private Tab gamesTab;
  private Tab profileTab;

  public MainmenuViewImpl() {
    buildMainmenu();
  }

  public void buildMainmenu() {
    this.main = new BorderPane();
    main.setId("menuroot");
    GridPane grid = new GridPane();
    userList = new Label();
    grid.add(userList, 3, 5);
    TabPane tabPane = new TabPane();
    main.setCenter(tabPane);

    HBox nav = new HBox();
    nav.setId("nav");
    nav.setSpacing(10);
    nav.setAlignment(Pos.CENTER_RIGHT);
    nav.setPadding(new Insets(15, 15, 15, 12));
    main.setTop(nav);
    main.setRight(grid);

    Rectangle rect = new Rectangle(720, 480);
    rect.setArcHeight(30.0);
    rect.setArcWidth(30.0);
    main.setClip(rect);
    mainmenuScene = new Scene(main);
    mainmenuScene.setFill(Color.TRANSPARENT);

    chatTab = new Tab();
    chatTab.setText("Chat");
    chatTab.setClosable(false);
    chatTab.setTooltip(new Tooltip("chat with other players"));
    chatTab.setContent(null);

    Button logoutButton = new Button();
    logoutButton.setId("logout-button");
    logoutButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        mainmenuPresenter.toLoginScene();
        mainmenuPresenter.logout();
      }
    });

    gamesTab = new Tab();
    gamesTab.setText("Games");
    gamesTab.setClosable(false);
    gamesTab.setTooltip(new Tooltip("find open games"));

    profileTab = new Tab();
    profileTab.setText("Profile");
    profileTab.setClosable(false);
    profileTab.setTooltip(new Tooltip("edit your profile"));

    newGameTab = new Tab();
    newGameTab.setText("Create Game");
    newGameTab.setClosable(false);
    newGameTab.setTooltip(new Tooltip("create a new game"));

    Tab rulesTab = new Tab();
    rulesTab.setText("Rules");
    rulesTab.setClosable(false);
    rulesTab.setTooltip(new Tooltip("learn the game"));

    Button close = new Button("x");
    close.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        System.exit(0);
      }
    });

    Button min = new Button("_");
    min.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        mainmenuPresenter.getSceneController().getStage().setIconified(true);
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
            mainmenuPresenter.getSceneController().getStage().getX() - mouseEvent.getScreenX();
        dragDelta.y =
            mainmenuPresenter.getSceneController().getStage().getY() - mouseEvent.getScreenY();
      }
    });
    nav.setOnMouseDragged(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        mainmenuPresenter.getSceneController().getStage()
            .setX(mouseEvent.getScreenX() + dragDelta.x);
        mainmenuPresenter.getSceneController().getStage()
            .setY(mouseEvent.getScreenY() + dragDelta.y);
      }
    });

    nav.getChildren().addAll(logoutButton, min, close);
    //Tabs werden der TabPane der Reihe nach hinzugefügt
    tabPane.getTabs().addAll(chatTab, gamesTab, profileTab, newGameTab);
  }

  public void initPlayerList() {
    ListView<String> listView = new ListView<>();
    //Liste der eingeloggten Spieler als Item der View setzen
    listView.setItems(mainmenuPresenter.getPlayerList().getPlayers());
    //ListView rechts auf der BorderPane platzieren
    main.setRight(listView);

    listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent click) {
        if (click.getClickCount() == 2) {
          String selectedUser = listView.getSelectionModel().getSelectedItem();
          TextField messageInput = mainmenuPresenter.getChatPresenter().getChatView()
              .getMessageInput();
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

  public void initCreate(CreateView createView) {
    this.newGameTab.setContent((CreateViewImpl) createView);
  }

  public void initGames(GamesView gamesView) {
    this.gamesTab.setContent((GamesViewImpl) gamesView);
  }

  public void initProfile(ProfileView profileView) {
    this.profileTab.setContent((ProfileViewImpl) profileView);
  }


  public void openModal(String msg) {
    final Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    VBox dialogVbox = new VBox(20);
    dialogVbox.getStylesheets().add("style.css");
    dialogVbox.getStyleClass().add("dialog");
    Label info = new Label(msg);
    Button confirm = new Button("OK");
    confirm.addEventHandler(ActionEvent.ACTION, event -> dialog.close());
    dialogVbox.getChildren().addAll(new Text("Message:"), info, confirm);
    Scene dialogScene = new Scene(dialogVbox, 300, 200);
    dialog.setScene(dialogScene);
    dialog.show();
  }

  @Override
  public void setMainmenuPresenter(MainmenuPresenter mainmenuPresenter) {
    this.mainmenuPresenter = mainmenuPresenter;
  }

  public Scene getMainmenuScene() {
    return this.mainmenuScene;
  }
}

