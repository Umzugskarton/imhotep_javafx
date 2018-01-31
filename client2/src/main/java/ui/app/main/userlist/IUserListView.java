package ui.app.main.userlist;

import javafx.collections.ObservableList;
import mvp.view.INavigateableSubView;

public interface IUserListView extends INavigateableSubView {

    void setUserListViewData(ObservableList<String> datasource);
}
