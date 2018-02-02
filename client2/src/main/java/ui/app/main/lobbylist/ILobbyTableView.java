package ui.app.main.lobbylist;

import data.lobby.CommonLobby;
import javafx.collections.ObservableList;
import mvp.view.INavigateableSubView;
import mvp.view.IView;

public interface ILobbyTableView extends IView {

    void setLobbyListViewData(ObservableList<CommonLobby> datasource);

    void joinLobby();
}
