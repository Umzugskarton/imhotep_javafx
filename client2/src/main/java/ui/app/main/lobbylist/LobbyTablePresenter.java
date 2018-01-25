package ui.app.main.lobbylist;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import data.user.User;
import data.lobby.Lobby;
import events.main.LobbylistEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mvp.presenter.Presenter;
import requests.main.JoinRequest;

import javax.swing.table.TableColumn;
import java.util.ArrayList;

public class LobbyTablePresenter extends Presenter<ILobbyTableView> {

    private final Connection connection;
    private User user;
    private ObservableList<Lobby> lobbys = FXCollections.observableArrayList();


    public LobbyTablePresenter(ILobbyTableView view, EventBus eventBus, Connection connection, User user) {
        super(view, eventBus);
        this.connection = connection;
        this.user = user;
        bind();
    }

    private void bind() {
        getEventBus().register(this);
    }

    public void updateLobbylist(ArrayList<Lobby> lobbys){
        this.lobbys.clear();

        for (Lobby lobby: lobbys){
            this.lobbys.add(lobby);
        }

    }

    public void joinLobby(int id, String pw){
        JoinRequest join = new JoinRequest(id, pw);
        this.connection.send(join);
    }

    public ObservableList<Lobby> getLobbyList() {
        return this.lobbys;
    }

    @Subscribe
    public void onLobbyListEvent(LobbylistEvent e) {
        updateLobbylist(e.getLobbies());
    }
}
