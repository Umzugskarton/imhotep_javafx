package ui.app.lobby.userTable;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.user.User;
import helper.fxml.GenerateFXMLView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import mvp.view.INavigateableView;
import mvp.view.ShowViewEvent;
import ui.app.lobby.chat.ChatView;

import java.net.URL;
import java.util.ResourceBundle;

public class UserTableView implements IUserTableView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox userListRoot;

    @FXML
    private TableView userTableView;

    @FXML
    private TableColumn username;


    private Parent myParent;

    private final INavigateableView parentView;
    private final UserTablePresenter presenter;
    private final EventBus eventBus;
    private final ChatView chatView;

    public UserTableView(INavigateableView parentView, ChatView chatView, EventBus eventBus, Connection connection, User user) {
        this.parentView = parentView;
        this.presenter = new UserTablePresenter(this, eventBus, connection, user);
        this.chatView = chatView;
        this.eventBus = eventBus;
        initOwnView();
    }

    @FXML
    void initialize() {

    }


    @Override
    public void initOwnView() {
        if (this.myParent == null)
            this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/app/lobby/userTable/UserTableView.fxml", this, eventBus);
        System.out.println("Check");
    }

    @FXML
    void handleListViewClick(MouseEvent click){
        if (click.getClickCount() == 2) {
            String selectedUser = (String) userTableView.getSelectionModel().getSelectedItem();
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
        this.userTableView.setItems(datasource);
    }
}
