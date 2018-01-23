package ui.app.main;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.user.User;
import helper.fxml.GenerateFXMLView;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import mvp.view.INavigateableView;
import mvp.view.ShowViewEvent;
import ui.app.chat.ChatView;
import ui.app.main.lobbylist.LobbyListView;
import ui.app.main.userlist.UserListView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainView implements IMainView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane mainViewRoot;

    @FXML
    private Pane subParentChat;

    @FXML
    private Pane subParentUserList;

    @FXML
    private Pane subParentLobbyList;

    private final INavigateableView parentView;
    private final MainPresenter mainPresenter;
    private final EventBus eventBus;

    // Own Parent
    private Parent myParent;

    // Subviews
    private ChatView chatView;
    private UserListView userListView;
    private LobbyListView lobbyListView;

    private final User user;

    public MainView(INavigateableView parentView, EventBus eventBus, Connection connection, User user){
        this.parentView = parentView;
        this.eventBus = eventBus;
        this.user = user;
        this.mainPresenter = new MainPresenter(this, eventBus, connection, user);
        bind();
        initOwnView();
    }

    private void bind() {
        eventBus.register(this);
    }

    @Override
    public void initOwnView() {
        if(this.myParent == null)
            this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/app/main/MainView.fxml", this, eventBus);
    }

    @FXML
    void initialize() {
        this.chatView = new ChatView(this,eventBus, mainPresenter.getConnection(), user);
        this.lobbyListView = new LobbyListView(this, eventBus, mainPresenter.getConnection(), user);
        this.userListView = new UserListView(this,this.chatView, eventBus, mainPresenter.getConnection(), user);

        setSubParentChat(this.chatView.getRootParent());
        setSubParentUserList(this.lobbyListView.getRootParent());
        setSubParentLobbyList(this.userListView.getRootParent());
    }

    public void setSubParentChat(Parent subParent){
        this.subParentChat.getChildren().clear();
        this.subParentChat.getChildren().add(subParent);
    }

    public void setSubParentUserList(Parent subParent){
        this.subParentUserList.getChildren().clear();
        this.subParentUserList.getChildren().add(subParent);
    }

    public void setSubParentLobbyList(Parent subParent){
        this.subParentLobbyList.getChildren().clear();
        this.subParentLobbyList.getChildren().add(subParent);
    }

    @Override
    public INavigateableView getParentView() {
        //return AppView
        return this.parentView;
    }

    //TODO Main-Tab in AppView soll angezeigt werden durch diese Methode.
    @Override
    public ShowViewEvent getEventToShowThisView() {
        return null;
    }


    @Override
    public String getTitle() {
        return "Main";
    }

    @Override
    public Parent getRootParent() {
        return this.myParent;
    }
}
