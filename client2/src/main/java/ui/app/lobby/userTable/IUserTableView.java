package ui.app.lobby.userTable;

import javafx.collections.ObservableList;
import mvp.view.INavigateableSubView;

public interface IUserTableView extends INavigateableSubView {

    void setUserListViewData(ObservableList<String> datasource);
}
