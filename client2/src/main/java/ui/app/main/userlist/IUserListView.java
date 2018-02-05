package ui.app.main.userlist;

import javafx.collections.ObservableList;
import mvp.view.IView;

public interface IUserListView extends IView {

  void setUserListViewData(ObservableList<String> datasource);
}
