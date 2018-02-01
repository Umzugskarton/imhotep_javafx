package ui.app.game;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.CommonLobby;
import data.user.User;
import helper.fxml.GenerateFXMLView;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import mvp.view.IDialogView;
import mvp.view.INavigateableView;
import mvp.view.ShowViewEvent;
import ui.app.game.board.BoardView;
import ui.app.game.userinterface.UserInterfaceView;
import ui.app.game.chat.ChatView;
import java.net.URL;
import java.util.ResourceBundle;

public class GameView implements IGameView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane dialogBackground;

    @FXML
    private Pane dialog;

    @FXML
    private AnchorPane mainViewRoot;

    @FXML
    private Pane subParentChat;

    @FXML
    private Pane subParentBoard;

    @FXML
    private Pane subParentUI;

    private final INavigateableView parentView;
    private final GamePresenter mainPresenter;
    private final EventBus eventBus;

    // Own Parent
    private Parent myParent;

    // Subviews
    private BoardView boardView;
    private UserInterfaceView userInterfaceView;
    private ChatView chatView;

    private final User user;
    private CommonLobby lobby;

    public GameView(INavigateableView parentView, EventBus eventBus, Connection connection, User user, CommonLobby lobby){
        this.parentView = parentView;
        this.eventBus = eventBus;
        this.lobby = lobby;
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
            this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/app/game/GameView.fxml", this, eventBus);
    }

    @FXML
    void initialize() {
        this.chatView = new ChatView(this,eventBus, mainPresenter.getClientSocket(), user);
        this.boardView = new BoardView(this, eventBus, mainPresenter.getClientSocket(), user, lobby);
        this.userInterfaceView = new UserInterfaceView(this, eventBus, mainPresenter.getClientSocket(), user , lobby);

        setSubParentChat(this.chatView.getRootParent());
        setSubParentBoard(this.boardView.getRootParent());
        setSubParentUI(this.userInterfaceView.getRootParent());
    }

    public void setSubParentChat(Parent subParent){
        this.subParentChat.getChildren().clear();
        this.subParentChat.getChildren().add(subParent);
    }

    public void setSubParentBoard(Parent subParent){
        this.subParentBoard.getChildren().clear();
        this.subParentBoard.getChildren().add(subParent);
    }

    public void setSubParentUI(Parent subParent){
        this.subParentUI.getChildren().clear();
        this.subParentUI.getChildren().add(subParent);
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
        return "Game";
    }

    @Override
    public Parent getRootParent() {
        return this.myParent;
    }

    public void showDialog(IDialogView view){
        dialogBackground.toFront();
        dialogBackground.setVisible(true);
        dialog.getChildren().add(view.getRootParent());
    }

    public void hideDialog(){
        dialogBackground.toBack();
        dialogBackground.setVisible(false);
        dialog.getChildren().clear();
    }
}
