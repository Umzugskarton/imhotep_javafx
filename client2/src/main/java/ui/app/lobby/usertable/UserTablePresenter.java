package ui.app.lobby.usertable;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import data.lobby.CommonLobby;
import data.lobby.LobbyUser;
import data.user.User;
import events.app.lobby.SetReadyToPlayEvent;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import misc.soundtrack.Soundtrack;
import mvp.presenter.Presenter;
import requests.*;

public class UserTablePresenter extends Presenter<IUserTableView> {

    private final Connection connection;
    private User user;
    private CommonLobby lobby;
    private ObservableList<LobbyUser> lobbies = FXCollections.observableArrayList();


    public UserTablePresenter(IUserTableView view, EventBus eventBus, Connection connection, CommonLobby lobby, User user) {
        super(view, eventBus);
        this.connection = connection;
        this.user = user;
        this.lobby = lobby;
        bind();

        LobbyUser lobbyUser = new LobbyUser(user, Color.RED.toString(), false);
        lobbies.add(lobbyUser);
    }

    private void bind() {
        getEventBus().register(this);
    }

    public CommonLobby getLobby() {
        return lobby;
    }

    public User getUser(){
        return user;
    }

    public void sendChangeColorRequest() {
        //this.lobbyView.updateColorRectangle();
        ChangeColorRequest changeColorRequest = new ChangeColorRequest();
        this.connection.send(changeColorRequest);
    }

    public void sendSetReadyRequest() {
        SetReadyRequest setReadyRequest = new SetReadyRequest();
        this.connection.send(setReadyRequest);
    }

    public void startGame() {
        if (lobby.getUsers().size() == lobby.getSize()) {
            IRequest request = new StartGameRequest();
            this.connection.send(request);
            Soundtrack.imhotepTheme.loop();
        }
        else {
            //ToDo: Message ausgeben das noch nicht genug Spieler gejoined sind
        }
    }

    public void leaveLobbyRequest() {
        LeaveLobbyRequest leaveLobbyRequest = new LeaveLobbyRequest(lobby.getLobbyId());
        this.connection.send(leaveLobbyRequest);
    }

    @Subscribe
    public void setReadyEventListener(SetReadyToPlayEvent e) {
        for (boolean b :e.getReady()){
            System.out.println("SPECIAL DEBUG MODE : PING ready  e : " +b);
        }
        lobby.setReady(e.getReady());
    }

    public ObservableList<LobbyUser> getLobbyList() {
        return lobbies;
    }

}
