package ui.app.main.lobbylist;

import data.lobby.CommonLobby;
import javafx.collections.ObservableList;
import mvp.view.INavigateableSubView;

public interface ILobbyTableView extends INavigateableSubView {

    void setLobbyListViewData(ObservableList<CommonLobby> datasource);

    void joinLobby();
}
