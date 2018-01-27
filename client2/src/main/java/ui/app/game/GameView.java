package ui.app.game;

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
import ui.app.game.board.BoardView;
import ui.app.game.userinterface.UserInterfaceView;
import ui.app.main.chat.ChatView;
import ui.app.main.lobbylist.LobbyTableView;
import ui.app.main.userlist.UserListView;

import java.net.URL;
import java.util.ResourceBundle;

public class GameView implements IGameView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane mainViewRoot;

    @FXML
    private Pane subParentChat;

    @FXML
    private Pane subParentBoard;

    @FXML
    private Pane subParentUserList;

    @FXML
    private Pane subParentLobbyList;

    private final INavigateableView parentView;
    private final GamePresenter mainPresenter;
    private final EventBus eventBus;

    // Own Parent
    private Parent myParent;

    // Subviews
    private BoardView boardView;
    private UserInterfaceView userInterfaceView;
    private ChatView chatView;
    private UserListView userListView;
    private LobbyTableView lobbyListView;

    private final User user;

    public GameView(INavigateableView parentView, EventBus eventBus, Connection connection, User user){
        this.parentView = parentView;
        this.eventBus = eventBus;
        this.user = user;
        this.mainPresenter = new GamePresenter(this, eventBus, connection, user);
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
        this.chatView = new ChatView(this,eventBus, mainPresenter.getClientSocket(), user);
        this.lobbyListView = new LobbyTableView(this, eventBus, mainPresenter.getClientSocket(), user);
        this.userListView = new UserListView(this,this.chatView, eventBus, mainPresenter.getClientSocket(), user);
        this.boardView = new BoardView(this, eventBus, mainPresenter.getClientSocket(), user);
        this.userInterfaceView = new UserInterfaceView(this, eventBus, mainPresenter.getClientSocket(), user);

        setSubParentChat(this.chatView.getRootParent());
        setSubParentUserList(this.lobbyListView.getRootParent());
        setSubParentLobbyList(this.userListView.getRootParent());
        setSubParentBoard(this.userInterfaceView.getRootParent());
    }

    public void setSubParentChat(Parent subParent){
        this.subParentChat.getChildren().clear();
        this.subParentChat.getChildren().add(subParent);
    }

    public void setSubParentBoard(Parent subParent){
        this.subParentBoard.getChildren().clear();
        this.subParentBoard.getChildren().add(subParent);
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
