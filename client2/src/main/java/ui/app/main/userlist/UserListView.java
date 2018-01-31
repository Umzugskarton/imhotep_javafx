package ui.app.main.userlist;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.user.User;
import helper.fxml.GenerateFXMLView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import mvp.view.INavigateableView;
import mvp.view.ShowViewEvent;
import ui.app.main.chat.ChatView;

import java.net.URL;
import java.util.ResourceBundle;

public class UserListView implements IUserListView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox userListRoot;

    @FXML
    private ListView userListView;

    private Parent myParent;

    private final INavigateableView parentView;
    private final UserListPresenter presenter;
    private final EventBus eventBus;
    private final ChatView chatView;

    public UserListView(INavigateableView parentView, ChatView chatView, EventBus eventBus, Connection connection, User user) {
        this.parentView = parentView;
        this.presenter = new UserListPresenter(this, eventBus, connection, user);
        this.chatView = chatView;
        this.eventBus = eventBus;
        initOwnView();
    }

    @FXML
    void initialize() {
        setUserListViewData(this.presenter.getUserList());
    }


    @Override
    public void initOwnView() {
        if (this.myParent == null)
            this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/app/main/userList/UserListView.fxml", this, eventBus);
        System.out.println("Check");
    }

    @FXML
    void handleListViewClick(MouseEvent click){
        if (click.getClickCount() == 2) {
            String selectedUser = (String) userListView.getSelectionModel().getSelectedItem();
            TextField messageInput = chatView.getMessageInput();
            messageInput.setText("@" + selectedUser + " ");
            messageInput.requestFocus();
            messageInput.end();
        }
    }

    @Override
    public INavigateableView getParentView() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public ShowViewEvent getEventToShowThisView() {
        return null;
    }

    @Override
    public Parent getRootParent() {
        return this.myParent;
    }

    @Override
    public void setUserListViewData(ObservableList<String> datasource){
        this.userListView.setItems(datasource);
    }
}