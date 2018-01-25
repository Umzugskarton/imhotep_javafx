package ui.app.main.lobbylist;

import data.lobby.Lobby;
import javafx.collections.ObservableList;
import mvp.view.INavigateableSubView;

public interface ILobbyTableView extends INavigateableSubView {

    void setLobbyListViewData(ObservableList<Lobby> datasource);

    void joinLobby();
}
