package ui.app.lobby.usertable;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.CommonLobby;
import data.lobby.LobbyUser;
import data.user.User;
import helper.fxml.GenerateFXMLView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import mvp.view.INavigateableView;
import ui.app.lobby.chat.ChatView;
import ui.app.lobby.control.LobbyControlView;

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
    private TableView<LobbyUser> userTableView;

    @FXML
    private TableColumn username;


    private Parent myParent;

    private final INavigateableView parentView;
    private final UserTablePresenter presenter;
    private final EventBus eventBus;
    private final ChatView chatView;
    private final LobbyControlView controlView;

    public UserTableView(INavigateableView parentView, ChatView chatView, CommonLobby lobby, LobbyControlView controlView, EventBus eventBus, Connection connection, User user) {
        this.parentView = parentView;
        this.presenter = new UserTablePresenter(this, eventBus, connection, lobby, user);
        this.chatView = chatView;
        this.eventBus = eventBus;
        this.controlView = controlView;
        initOwnView();
    }

    @FXML
    void initialize() {
    }


    @Override
    public void initOwnView() {
        if (this.myParent == null)
            this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/app/lobby/userTable/UserTableView.fxml", this, eventBus);

        //TODO Ersteinmal kommplette Lobby übernommen
        //setUserListViewData(this.presenter.getLobbyList());

        UserTableColumn userTableColumn = new UserTableColumn("User", this.presenter);
        ColorTableColum colorTableColum = new ColorTableColum("Color", this.presenter);
        ReadyTableColum readyTableColum = new ReadyTableColum("Ready", this.presenter);

        this.userTableView.getColumns().addAll(userTableColumn, colorTableColum, readyTableColum);
    }

    @FXML
    void handleUserTableViewClick(MouseEvent click) {
        if (click.getClickCount() == 2) {
            System.out.println("Doubleclick");
        }
    }

    @Override
    public Parent getRootParent() {
        return this.myParent;
    }

    @Override
    public void setUserListViewData(ObservableList<LobbyUser> datasource) {
        this.userTableView.setItems(datasource);
    }

    @Override
    public void updateTable() {
        for (int i = 0; i < userTableView.getItems().size(); i++) {
            userTableView.getItems().clear();
        }
        userTableView.setItems(presenter.getLobby().getObservableUsers());
    }

    @Override
    public void initLobbyInfo() {
        if (presenter.checkHost()) {
            this.controlView.getStartGameButton().addEventHandler(ActionEvent.ACTION, e -> {
                if (presenter.checkAllReady()) {
                    presenter.startGame();
                } else {
                    System.out.print("Es sind nicht alle bereit!");
                }
            });
        } else {
            this.controlView.getStartGameButton().setVisible(false);
        }

        userTableView.setItems(presenter.getLobby().getObservableUsers());
        this.controlView.getUserSizeLabel().setText("Players:" + String.valueOf(presenter.getLobby().getObservableUsers().size()) + " / " + this.presenter.getLobby().getSize());
    }

    public void updateLobby(CommonLobby lobby) {
        presenter.updateLobby(lobby);
    }
}
