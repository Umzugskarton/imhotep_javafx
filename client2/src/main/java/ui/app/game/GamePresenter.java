package ui.app.game;

import GameMoves.FillUpStorageMove;
import GameMoves.LoadUpShipMove;
import GameMoves.VoyageToStoneSiteMove;
import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.user.User;
import mvp.presenter.Presenter;

public class GamePresenter extends Presenter<IGameView> {

    private final Connection connection;
    private final User user;

    public GamePresenter(IGameView view, EventBus eventBus, Connection connection, User user) {
        super(view, eventBus);
        this.connection = connection;
        this.user = user;
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
