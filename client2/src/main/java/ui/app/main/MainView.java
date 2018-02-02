package ui.app.main;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import data.user.User;
import helper.fxml.GenerateFXMLView;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ui.dialog.misc.IDialogableView;
import mvp.view.INavigateableView;
import mvp.view.ShowViewEvent;
import ui.app.main.chat.ChatView;
import ui.app.main.lobbylist.LobbyTableView;
import ui.app.main.userlist.UserListView;
import ui.dialog.DialogView;
import ui.dialog.IDialogView;
import ui.dialog.createlobby.CreateLobbyView;
import ui.dialog.createlobby.ShowCreateLobbyDialogEvent;
import ui.dialog.misc.ViewIdentifier;

import java.net.URL;
import java.util.ResourceBundle;

public class MainView implements IMainView, IDialogableView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane mainViewRoot;

    @FXML
    private Pane dialogBackground;

    @FXML
    private Pane dialog;

    @FXML
    private Pane subParentChat;

    @FXML
    private Pane subParentUserList;

    @FXML
    private Pane subParentLobbyList;

    @FXML
    private Pane popupPane;

    private final INavigateableView parentView;
    private final MainPresenter mainPresenter;
    private final EventBus eventBus;

    // Own Parent
    private Parent myParent;

    // Subviews
    private ChatView chatView;
    private UserListView userListView;
    private LobbyTableView lobbyListView;

    //Dialog
    private DialogView dialogView;

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
    private void initialize() {
        this.chatView = new ChatView(this,eventBus, mainPresenter.getConnection(), user);
        this.lobbyListView = new LobbyTableView(this, eventBus, mainPresenter.getConnection(), user);
        this.userListView = new UserListView(this,this.chatView, eventBus, mainPresenter.getConnection(), user);

        setSubParentChat(this.chatView.getRootParent());
        setSubParentUserList(this.lobbyListView.getRootParent());
        setSubParentLobbyList(this.userListView.getRootParent());

        this.dialogView = new DialogView(this, this.eventBus);
        this.dialog.getChildren().add(this.dialogView.getRootParent());
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


    @Override
    public void showDialog(IDialogView view){
        this.dialogView.showDialog(view);
        dialogBackground.toFront();
        dialogBackground.setVisible(true);
    }

    @Override
    public void hideDialog(){
        dialogBackground.toBack();
        dialogBackground.setVisible(false);
    }

    @Subscribe
    public void onShowCreateLoobyDialogEvent(ShowCreateLobbyDialogEvent e) {
        if(e.getViewIdentifier() == ViewIdentifier.MAIN_VIEW){
            showDialog(new CreateLobbyView(this.eventBus, this.mainPresenter.getConnection()));
        }
    }
}
