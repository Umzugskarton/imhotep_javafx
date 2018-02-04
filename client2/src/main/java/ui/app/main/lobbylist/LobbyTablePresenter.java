package ui.app.main.lobbylist;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import data.lobby.CommonLobby;
import data.user.User;
import events.app.lobby.LobbyListEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mvp.presenter.Presenter;
import requests.lobby.JoinRequest;

import java.util.ArrayList;

public class LobbyTablePresenter extends Presenter<ILobbyTableView> {

    private final Connection connection;
    private User user;
    private ObservableList<LobbyTableData> lobbys = FXCollections.observableArrayList();


    public LobbyTablePresenter(ILobbyTableView view, EventBus eventBus, Connection connection, User user) {
        super(view, eventBus);
        this.connection = connection;
        this.user = user;
        bind();
    }

    private void bind() {
        getEventBus().register(this);
    }

    public void updateLobbylist(ArrayList<CommonLobby> lobbys){
        this.lobbys.clear();

        for (CommonLobby lobby: lobbys){
            LobbyTableData lobbyTableData = new LobbyTableData(
                    lobby.getLobbyId(),
                    lobby.getName(),
                    lobby.getUsercount(),
                    lobby.getSize());

            this.lobbys.add(lobbyTableData);
        }

    }

    public void joinLobby(int id, String pw){
        JoinRequest join = new JoinRequest(id, pw);
        this.connection.send(join);
    }

    public ObservableList<LobbyTableData> getLobbyList() {
        return this.lobbys;
    }

    public Connection getConnection() {
        return connection;
    }

    @Subscribe
    public void onLobbyListEvent(LobbyListEvent e) {
        updateLobbylist(e.getLobbies());
    }
}
