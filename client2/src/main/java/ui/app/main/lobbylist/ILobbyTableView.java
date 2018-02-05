package ui.app.main.lobbylist;

import javafx.collections.ObservableList;
import mvp.view.IView;

public interface ILobbyTableView extends IView {

  void setLobbyListViewData(ObservableList<LobbyTableData> datasource);

  void joinLobby();
}
