package ui.app.lobby;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.Lobby;
import data.user.User;
import mvp.presenter.Presenter;

public class LobbyPresenter extends Presenter<ILobbyView> {

    private final Connection connection;
    private final User user;
    private Lobby lobby;

    public LobbyPresenter(ILobbyView view, EventBus eventBus, Connection connection, User user , Lobby lobby) {
        super(view, eventBus);
        this.connection = connection;
        this.user = user;
        this.lobby= lobby;
    }

    public Connection getClientSocket() {
        return this.connection;
    }

    public void logout() {
        //getClientSocket().send(new LogoutRequest());
        //TODO Überlegen wie das umgesetzt werden soll
        //this.chatPresenter.getChatView().getChatText().getChildren().clear();
    }
}
