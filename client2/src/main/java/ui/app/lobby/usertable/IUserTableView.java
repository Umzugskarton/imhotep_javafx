package ui.app.lobby.usertable;

import data.lobby.CommonLobby;
import data.lobby.LobbyUser;
import javafx.collections.ObservableList;
import mvp.view.IView;

public interface IUserTableView extends IView {

    void setUserListViewData(ObservableList<LobbyUser> datasource);

    public void updateTable();

    void initLobbyInfo();
}
