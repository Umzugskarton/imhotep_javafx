package ui.app.main.lobbylist;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.user.User;
import data.lobby.Lobby;
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
import ui.app.lobby.ShowLobbyViewEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class LobbyListView implements ILobbyListView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox lobbyListViewRoot;

    @FXML
    private ListView lobbyListView;

    private Parent myParent;

    private final INavigateableView parentView;
    private final LobbyListPresenter presenter;
    private final EventBus eventBus;

    public LobbyListView(INavigateableView parentView, EventBus eventBus, Connection connection, User user) {
        this.parentView = parentView;
        this.presenter = new LobbyListPresenter(this, eventBus, connection, user);
        this.eventBus = eventBus;
        initOwnView();
    }

    @FXML
    void initialize() {
        setLobbyListViewData(this.presenter.getLobbyList());
    }


    @Override
    public void initOwnView() {
        if (this.myParent == null)
            this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/app/main/lobbyList/LobbyListView.fxml", this, eventBus);
    }

    @FXML
    void handleLobbyViewClick(MouseEvent click){
        Lobby selectedLobby = (Lobby) this.lobbyListView.getFocusModel().getFocusedItem();

        if (click.getClickCount() == 2) {
            this.presenter.joinLobby(selectedLobby.getLobbyId(),"");;
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
    public void setLobbyListViewData(ObservableList<Lobby> datasource){
        this.lobbyListView.setItems(datasource);
    }

    @Override
    public void joinLobby() {

    }
}
