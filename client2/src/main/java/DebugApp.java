import GameEvents.GameInfoEvent;
import GameEvents.TurnEvent;
import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import data.lobby.Lobby;
import data.lobby.LobbyUser;
import data.user.User;
import events.Event;
import events.game.StartGameEvent;
import events.main.*;
import events.main.lobby.LobbyJoinSuccessfulEvent;
import events.main.login.LoginFailedEvent;
import events.main.login.LoginSuccessfulEvent;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import requests.Request;
import requests.main.ChatRequest;
import requests.main.StartGameRequest;
import ui.popup.createLobby.ShowCreateLobbyPopupEvent;

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

    private void bind(){
       this.eventBus.register(this);
    }

    private void initView(){
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

    private void initLogin(){
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
                User user = new User(1,"Testuser", "blabla", "testuser@test.de");
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
                LoginFailedEvent myEvent = new LoginFailedEvent("Test fail message");
                logger.debug("Sende " + myEvent.getClass().getSimpleName() + " an EventBus!");
                getEventBus().post(myEvent);
            }
        });


        //Füge Button im View hinzu!
        vBox.getChildren().add(loginEvent1Button);
        vBox.getChildren().add(loginEvent2Button);
        vBox.getChildren().add(loginEvent3Button);
    }

    private void initRegister(){
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
                RegisterEvent myEvent = new RegisterEvent();
                logger.debug("Sende " + myEvent.getClass().getSimpleName() + " an EventBus!");
                getEventBus().post(myEvent);
            }
        });

        //Füge Button im View hinzu!
        vBox.getChildren().add(registerEvent1Button);
    }

    private void initUserList(){
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

    private void initLobbyList(){
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
                LobbylistEvent myEvent = new LobbylistEvent();
                ArrayList<Lobby> lobbys = new ArrayList<>();

                ArrayList<LobbyUser> lobbyUsers = new ArrayList<>();
                LobbyUser lobbyUser = new LobbyUser(new User(1,"Testuser", "blabla", "testuser@user.de"), "Red" ,false);
                lobbyUsers.add(lobbyUser);

                boolean[] readyStaus = new boolean[3];
                readyStaus[0] = false;

                ArrayList<String> colors = new ArrayList<>();
                colors.add("Red");
                Lobby lobby = new Lobby(1, "Lobby 1", lobbyUsers, false, 4, true, "Testuser", readyStaus, colors);
                lobby.setBelegung(2);
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
                lobbyUsers.add(new LobbyUser(new User(0 , "testuser", "xyz", "test@test.de" ), "#edc3f9", false));
                lobbyUsers.add(new LobbyUser(new User(1 , "testuser2", "xyz", "test@test.de" ), "#070fa1", false));
                boolean[] ready = {false,false};
                ArrayList<String> colors = new ArrayList<>();

                colors.add("#edc3f9");
                colors.add("#070fa1");

                Lobby lobby = new Lobby(0, "test", lobbyUsers, false,2,true, "testuser", ready,colors);
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
                lobbyUsers.add(new LobbyUser(new User(1 , "testuser", "xyz", "test@test.de" ), "#000", false));
                boolean[] ready = {false,false};
                ArrayList<String> colors = new ArrayList<>();

                colors.add("#000");
                colors.add("#fff");

                Lobby lobby = new Lobby(1, "test", lobbyUsers, false,2,true, "testuser", ready,colors);
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
                int[] sitesAllo = {-1,-1,-1,-1,-1};
                String[] x = {"Market", "Pyramids", "Temple", "BurialChamber", "Obelisks"};
                String[] y = {"test", "test2"};
                ArrayList<Integer> storages = new ArrayList<>();
                storages.add(1);
                storages.add(2);
                gameInfo.setSiteString(x);
                gameInfo.setSitesAllocation(sitesAllo);
                gameInfo.setOrder(y);
                gameInfo.setTurnTime(20);
                gameInfo.setRound(0);
                gameInfo.setStorages(storages);
                for (int i = 0 ; i < 4; i++) {
                    int[] f = new int[i];
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
                TurnEvent e = new TurnEvent(true, "testuser");
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
    }

    private void initPopup(){
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
                getEventBus().post(new ShowCreateLobbyPopupEvent());
            }
        });

        //Füge Button im View hinzu!
        vBox.getChildren().add(popupButton1);
    }

    private void initEventBusSniffer(){
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

    public Stage getStage(){
        return this.stage;
    }

    private EventBus getEventBus() {
        return eventBus;
    }

    @Subscribe
    public void gotDeadEvent(DeadEvent deadEvent){
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

    @Subscribe void gotChatRequest(ChatRequest request) {
        ChatEvent event = new ChatEvent();
        event.setMsg(request.getMsg());
        this.eventBus.post(event);
    }
}
