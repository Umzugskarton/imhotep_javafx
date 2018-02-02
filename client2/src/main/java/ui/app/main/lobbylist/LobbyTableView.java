package ui.app.main.lobbylist;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.user.User;
import data.lobby.CommonLobby;
import helper.fxml.GenerateFXMLView;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import mvp.view.INavigateableView;
import mvp.view.ShowViewEvent;
import ui.dialog.createLobby.CreateLobbyView;
import ui.dialog.createLobby.ShowCreateLobbyDialogEvent;
import ui.dialog.misc.ViewIdentifier;

import java.net.URL;
import java.util.ResourceBundle;

public class LobbyTableView implements ILobbyTableView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox lobbyListViewRoot;

    @FXML
    private TableView lobbyTableView;

    @FXML
    private TableColumn<CommonLobby, String> tableColumnId;

    @FXML
    private TableColumn<CommonLobby, String> tableColumnName;

    @FXML
    private TableColumn<CommonLobby, String> tableColumnBelegung;



    private Parent myParent;

    private final INavigateableView parentView;
    private final LobbyTablePresenter presenter;
    private final EventBus eventBus;

    public LobbyTableView(INavigateableView parentView, EventBus eventBus, Connection connection, User user) {
        this.parentView = parentView;
        this.presenter = new LobbyTablePresenter(this, eventBus, connection, user);
        this.eventBus = eventBus;
        initOwnView();
    }

    @FXML
    void initialize() {
        setLobbyListViewData(this.presenter.getLobbyList());
    }


    @Override
    public void initOwnView() {
        if (this.myParent == null){
            this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/app/main/lobbyList/LobbyListView.fxml", this, eventBus);

            this.tableColumnId.setCellValueFactory(new PropertyValueFactory<CommonLobby, String>("lobbyID"));
            this.tableColumnName.setCellValueFactory(new PropertyValueFactory<CommonLobby, String>("name"));
            this.tableColumnBelegung.setCellValueFactory(new PropertyValueFactory<CommonLobby, String>("belegung"));

            TableColumn<CommonLobby, CommonLobby> tableColumnJoinButton;
            tableColumnJoinButton = new TableColumn<>("");
            tableColumnJoinButton.setMinWidth(40);
            tableColumnJoinButton.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
            tableColumnJoinButton.setCellFactory(param -> new TableCell<CommonLobby, CommonLobby>() {
                private final Button joinButton = new Button("Join");

                @Override
                protected void updateItem(CommonLobby lobby, boolean empty) {
                    super.updateItem(lobby, empty);

                    if (lobby == null) {
                        setGraphic(null);
                        return;
                    }

                    setGraphic(joinButton);
                    joinButton.setOnAction(event -> System.out.println("Push the Button"));
                }
            });

            this.lobbyTableView.getColumns().add(tableColumnJoinButton);
        }
    }


    public LobbyTablePresenter getPresenter(){
        return this.presenter;
    }

    @FXML
    private void handleLobbyViewClick(MouseEvent click){
        System.out.println("Lobby doubleclick");
        CommonLobby selectedLobby = (CommonLobby) this.lobbyTableView.getFocusModel().getFocusedItem();

        if (click.getClickCount() == 2) {
            this.presenter.joinLobby(selectedLobby.getLobbyId(),"");;
        }
    }

    @FXML
    private void handleCreateLobbyButton(ActionEvent event) {
        this.eventBus.post(new ShowCreateLobbyDialogEvent(ViewIdentifier.MAIN));
    }

    @Override
    public Parent getRootParent() {
        return this.myParent;
    }


    @Override
    public void setLobbyListViewData(ObservableList<CommonLobby> datasource){
        this.lobbyTableView.setItems(datasource);
    }

    @Override
    public void joinLobby() {

    }
}
