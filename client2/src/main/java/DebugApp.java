import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import data.lobby.CommonLobby;
import data.lobby.LobbyUser;
import data.user.User;
import events.Event;
import events.EventReason;
import events.SiteType;
import events.app.chat.ChatMessageEvent;
import events.app.game.GameInfoEvent;
import events.app.game.ShipLoadedEvent;
import events.app.game.StartGameEvent;
import events.app.game.TurnEvent;
import events.app.lobby.LobbyInfoEvent;
import events.app.lobby.LobbyJoinSuccessfulEvent;
import events.app.lobby.LobbyListEvent;
import events.app.main.UserListEvent;
import events.start.login.LoginEvent;
import events.start.login.LoginFailedEvent;
import events.start.login.LoginSuccessfulEvent;
import events.start.registration.RegistrationEvent;
import java.util.Arrays;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import requests.ChatRequest;
import requests.Request;
import requests.gamemoves.CardType;
import ui.dialog.lobby.createlobby.ShowCreateLobbyDialogEvent;
import ui.dialog.misc.ViewIdentifier;

import java.util.ArrayList;
import java.util.Date;

public class DebugApp {

  private final Logger logger = LoggerFactory.getLogger(getClass().getName());
  private final EventBus eventBus;

  private Stage stage;
  private TabPane tabPane;

  private ObservableList<Text> eventBusSnifferList;

  ListView<Text> eventBusSnifferListView;

  public DebugApp(EventBus eventBus) {
    this.eventBus = eventBus;
    bind();
    initView();
  }

  private void bind() {
    this.eventBus.register(this);
  }

  private void initView() {
    this.stage = new Stage();
    this.stage.setWidth(500);
    this.stage.setHeight(500);
    Scene scene = new Scene(new Group());

    this.tabPane = new TabPane();

    scene.setRoot(tabPane);
    stage.setScene(scene);


    //Erstelle alle Buttons
    initLogin();
    initRegister();
    initUserList();
    initLobbyList();
    initPopup();
    initEventBusSniffer();


    stage.show();
  }

  private void initLogin() {
    String initName = "Login";

    VBox vBox = new VBox();

    Tab tab = new Tab(initName);
    tabPane.getTabs().add(tab);

    tab.setContent(vBox);

    // Buttons 1
    Button loginEvent1Button = new Button("Login Event");
    loginEvent1Button.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        LoginEvent myEvent = new LoginEvent();
        logger.debug("Sende " + myEvent.getClass().getSimpleName() + " an EventBus!");
        getEventBus().post(myEvent);
      }
    });

    // Buttons 2
    Button loginEvent2Button = new Button("LoginSuccessfulEvent");
    loginEvent2Button.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        User user = new User(1, "Testuser", "blabla", "testuser@test.de");
        LoginSuccessfulEvent myEvent = new LoginSuccessfulEvent(user);
        logger.debug("Sende " + myEvent.getClass().getSimpleName() + " an EventBus!");
        getEventBus().post(myEvent);
      }
    });

    // Buttons 3
    Button loginEvent3Button = new Button("LoginFailedEvent");
    loginEvent3Button.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        LoginFailedEvent myEvent = new LoginFailedEvent(EventReason.ALREADY_LOGGED_IN);
        logger.debug("Sende " + myEvent.getClass().getSimpleName() + " an EventBus!");
        getEventBus().post(myEvent);
      }
    });


    //Füge Button im View hinzu!
    vBox.getChildren().add(loginEvent1Button);
    vBox.getChildren().add(loginEvent2Button);
    vBox.getChildren().add(loginEvent3Button);
  }

  private void initRegister() {
    String initName = "Register";

    VBox vBox = new VBox();

    Tab tab = new Tab(initName);
    tabPane.getTabs().add(tab);

    tab.setContent(vBox);

    // Buttons 1
    Button registerEvent1Button = new Button("Register Event");
    registerEvent1Button.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        RegistrationEvent myEvent = new RegistrationEvent();
        logger.debug("Sende " + myEvent.getClass().getSimpleName() + " an EventBus!");
        getEventBus().post(myEvent);
      }
    });

    //Füge Button im View hinzu!
    vBox.getChildren().add(registerEvent1Button);
  }

  private void initUserList() {
    String initName = "userList";

    VBox vBox = new VBox();

    Tab tab = new Tab(initName);
    tabPane.getTabs().add(tab);

    tab.setContent(vBox);

    // Buttons 1
    Button userListButton1 = new Button("userList Event");
    userListButton1.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        UserListEvent myEvent = new UserListEvent();
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Hans im Glück");
        strings.add("Peter");
        strings.add("What the Fuck!");
        strings.add("What the Fuck!");
        strings.add("What the Fuck!");
        strings.add("What the Fuck!");
        strings.add("What the Fuck!");
        strings.add("What the Fuck!");
        strings.add("What the Fuck!");
        strings.add("What the Fuck!");
        strings.add("What the Fuck!");
        strings.add("What the Fuck!");
        strings.add("What the Fuck!");
        strings.add("What the Fuck!");


        myEvent.setUserList(strings);
        logger.debug("Sende " + myEvent.getClass().getSimpleName() + " an EventBus!");
        getEventBus().post(myEvent);
      }
    });

    //Füge Button im View hinzu!
    vBox.getChildren().add(userListButton1);
  }

  private void initLobbyList() {
    String initName = "LobbyList";

    VBox vBox = new VBox();

    Tab tab = new Tab(initName);
    tabPane.getTabs().add(tab);

    tab.setContent(vBox);

    // Buttons 1
    Button lobbyListButton1 = new Button("LobbyList Event");
    lobbyListButton1.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        LobbyListEvent myEvent = new LobbyListEvent();
        ArrayList<CommonLobby> lobbys = new ArrayList<>();

        ArrayList<LobbyUser> lobbyUsers = new ArrayList<>();
        User user = new User(1, "Testuser", "blabla", "testuser@user.de");
        LobbyUser lobbyUser = new LobbyUser(user, "Red", false);
        lobbyUsers.add(lobbyUser);

        boolean[] readyStaus = new boolean[3];
        readyStaus[0] = false;

        ArrayList<String> colors = new ArrayList<>();
        colors.add("Red");
        CommonLobby lobby = new CommonLobby(1, "Lobby 1", lobbyUsers, false, 4, true, "Testuser", readyStaus, colors);
        lobbys.add(lobby);

        myEvent.setLobbies(lobbys);
        logger.debug("Sende " + myEvent.getClass().getSimpleName() + " an EventBus!");
        getEventBus().post(myEvent);
      }
    });

    // Buttons 2
    Button lobbyListButton2 = new Button("LobbyJoinSuccessfulEvent");
    lobbyListButton2.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        LobbyJoinSuccessfulEvent myEvent = new LobbyJoinSuccessfulEvent();
        logger.debug("Sende " + myEvent.getClass().getSimpleName() + " an EventBus!");
        getEventBus().post(myEvent);
      }
    });


    // Buttons 3
    Button lobbyListButton3 = new Button("LobbyInfo 0");
    lobbyListButton3.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {

        ArrayList<LobbyUser> lobbyUsers = new ArrayList<>();
        User user1 = new User(0, "testuser", "xyz", "test@test.de");
        User user2 = new User(1, "testuser2", "xyz", "test@test.de");
        lobbyUsers.add(new LobbyUser(user1, "#edc3f9", false));
        lobbyUsers.add(new LobbyUser(user2, "#070fa1", false));
        boolean[] ready = {false, false};
        ArrayList<String> colors = new ArrayList<>();
        colors.add("#edc3f9");
        colors.add("#070fa1");

        CommonLobby lobby = new CommonLobby(0, "test", lobbyUsers, false, 2, true, "testuser", ready, colors);
        LobbyInfoEvent lobbyInfoEvent = new LobbyInfoEvent(lobby);

        getEventBus().post(lobbyInfoEvent);
      }
    });

    // Buttons 4
    Button lobbyListButton4 = new Button("LobbyInfo 1");
    lobbyListButton4.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {

        ArrayList<LobbyUser> lobbyUsers = new ArrayList<>();
        User user1 = new User(2, "testuser3", "xyz", "test@test.de");
        lobbyUsers.add(new LobbyUser(user1, "#000", false));
        boolean[] ready = {false, false};
        ArrayList<String> colors = new ArrayList<>();

        colors.add("#000");
        colors.add("#fff");

        CommonLobby lobby = new CommonLobby(1, "test", lobbyUsers, false, 2, true, "testuser", ready, colors);
        LobbyInfoEvent lobbyInfoEvent = new LobbyInfoEvent(lobby);

        getEventBus().post(lobbyInfoEvent);
      }
    });

    // Buttons 5
    Button lobbyListButton5 = new Button("Start Game Lobby 0");
    lobbyListButton5.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        StartGameEvent startGameEvent = new StartGameEvent(0);
        getEventBus().post(startGameEvent);
      }
    });

    // Buttons 6
    Button lobbyListButton6 = new Button("Game Info Lobby 0");
    lobbyListButton6.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        GameInfoEvent gameInfo = new GameInfoEvent();
        int[] sitesAllo = {-1, -1, -1, -1, -1};
        SiteType[] st = {SiteType.MARKET, SiteType.PYRAMID, SiteType.TEMPLE,
            SiteType.BURIALCHAMBER, SiteType.OBELISKS};
        List<SiteType> x = new ArrayList<>(Arrays.asList(st));
        Arrays.asList();
        String[] y = {"test", "test2"};
        ArrayList<Integer> storages = new ArrayList<>();
        storages.add(1);
        storages.add(2);
        ArrayList<CardType> type = new ArrayList<>();
        type.add(CardType.BURIALCHAMBER);
        type.add(CardType.CHISEL);
        type.add(CardType.PAVEDPATH);
        type.add(CardType.STATUE);
        gameInfo.setCards(type);
        gameInfo.setSiteTypes(x);
        gameInfo.setSitesAllocation(sitesAllo);
        gameInfo.setOrder(y);
        gameInfo.setTurnTime(20);
        gameInfo.setRound(0);
        gameInfo.setStorages(storages);
        for (int i = 1; i < 5; i++) {
          int[] f = new int[i];
          for (int l = 0 ; l < f.length ; l++){
            f[l] = -1;
          }
          gameInfo.setCurrentShips(f);
        }

        eventBus.post(gameInfo);

      }
    });

    // Buttons 7
    Button lobbyListButton7 = new Button("Turn Event 0");
    lobbyListButton7.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        TurnEvent e = new TurnEvent(true, "testuser", 0);
        eventBus.post(e);
      }
    });


    // Buttons 8
    Button lobbyListButton8 = new Button("ShipLoaded Event 0");
    lobbyListButton8.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        int[] cargo = {0};
        ShipLoadedEvent e = new ShipLoadedEvent(0, 0, cargo ,0);
        eventBus.post(e);
      }
    });

    //Füge Button im View hinzu!
    vBox.getChildren().add(lobbyListButton1);
    vBox.getChildren().add(lobbyListButton2);
    vBox.getChildren().add(lobbyListButton3);
    vBox.getChildren().add(lobbyListButton4);
    vBox.getChildren().add(lobbyListButton5);
    vBox.getChildren().add(lobbyListButton6);
    vBox.getChildren().add(lobbyListButton7);
    vBox.getChildren().add(lobbyListButton8);
  }

  private void initPopup() {
    String initName = "Popup";

    VBox vBox = new VBox();

    Tab tab = new Tab(initName);
    tabPane.getTabs().add(tab);

    tab.setContent(vBox);

    // Buttons 1
    Button popupButton1 = new Button("Popup CreateLobby");
    popupButton1.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        getEventBus().post(new ShowCreateLobbyDialogEvent(ViewIdentifier.MAIN_VIEW));
      }
    });

    //Füge Button im View hinzu!
    vBox.getChildren().add(popupButton1);
  }

  private void initEventBusSniffer() {
    String initName = "EventBusSniffer";

    VBox vBox = new VBox();

    Tab tab = new Tab(initName);
    tabPane.getTabs().add(tab);

    tab.setContent(vBox);

    this.eventBusSnifferListView = new ListView<>();

    //ScrollPane scrollPane = new ScrollPane(eventBusSnifferListView);

    //scrollPane.vvalueProperty().bind(eventBusSnifferListView.heightProperty());

    this.eventBusSnifferList = eventBusSnifferListView.getItems();

    //Füge Button im View hinzu!
    vBox.getChildren().add(eventBusSnifferListView);

    System.out.println();
    System.out.println();

  }

  public Stage getStage() {
    return this.stage;
  }

  private EventBus getEventBus() {
    return eventBus;
  }

  @Subscribe
  public void gotDeadEvent(DeadEvent deadEvent) {
    Date date = new Date();
    EventBus eventBus = (EventBus) deadEvent.getSource();
    logger.debug("Got dead event " + deadEvent.getEvent() + ", from " + eventBus);

    Text text = new Text(date.toString() + " - Got dead event " + deadEvent.getEvent() + ", from " + eventBus);
    text.setFill(Color.RED);
    this.eventBusSnifferList.add(text);
    //this.eventBusSnifferListView.getFocusModel().focusNext();
    //this.eventBusSnifferListView.scrollTo(this.eventBusSnifferListView.getFocusModel().getFocusedIndex());
  }

  @Subscribe
  public void gotAllEvent(Event event) {
    Date date = new Date();
    logger.debug("Got event " + event.toString());

    Text text = new Text(date.toString() + " - Got event " + event.toString());
    this.eventBusSnifferList.add(text);
    //this.eventBusSnifferListView.getFocusModel().focusNext();
    //this.eventBusSnifferListView.scrollTo(this.eventBusSnifferListView.getFocusModel().getFocusedIndex());
  }

  @Subscribe
  public void gotAllReqeust(Request request) {
    Date date = new Date();
    logger.debug("Got event " + request.toString());

    Text text = new Text(date.toString() + " - Got request " + request.toString());
    this.eventBusSnifferList.add(text);
    //this.eventBusSnifferListView.getFocusModel().focusNext();
    //this.eventBusSnifferListView.scrollTo(this.eventBusSnifferListView.getFocusModel().getFocusedIndex());
  }

  @Subscribe
  void gotChatRequest(ChatRequest request) {
    ChatMessageEvent event = new ChatMessageEvent();
    event.setMsg(request.getMsg());
    this.eventBus.post(event);
  }
}
