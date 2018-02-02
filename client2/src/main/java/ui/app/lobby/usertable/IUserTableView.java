package ui.app.lobby.usertable;

import javafx.collections.ObservableList;
import mvp.view.IView;

public interface IUserTableView extends IView {

    void setUserListViewData(ObservableList<String> datasource);
}
