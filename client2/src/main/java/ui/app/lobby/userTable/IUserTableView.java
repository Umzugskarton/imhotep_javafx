package ui.app.lobby.userTable;

import javafx.collections.ObservableList;
import mvp.view.INavigateableSubView;
import mvp.view.IView;

public interface IUserTableView extends IView {

    void setUserListViewData(ObservableList<String> datasource);
}
