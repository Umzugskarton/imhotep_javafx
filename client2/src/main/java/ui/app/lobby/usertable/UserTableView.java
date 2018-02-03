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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Callback;
import mvp.view.INavigateableView;
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

    public UserTableView(INavigateableView parentView, ChatView chatView, CommonLobby lobby, EventBus eventBus, Connection connection, User user) {
        this.parentView = parentView;
        this.presenter = new UserTablePresenter(this, eventBus, connection, lobby, user);
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

        setUserListViewData(this.presenter.getLobbyList());

        TableColumn firstNameCol = new TableColumn("Username");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<LobbyUser, String>("DUMMY"));

        Callback<TableColumn<LobbyUser, String>, TableCell<LobbyUser, String>> cellFactory
                = new Callback<TableColumn<LobbyUser, String>, TableCell<LobbyUser, String>>() {
            @Override
            public TableCell call(final TableColumn<LobbyUser, String> param) {
                final TableCell<LobbyUser, String> cell = new TableCell<LobbyUser, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            LobbyUser lobbyUser = getTableView().getItems().get(getIndex());
                            Text name = new Text(lobbyUser.getUsername());
                            HBox hbox = new HBox();
                            hbox.setSpacing(5);
                            hbox.getChildren().add(name);

                            if (lobbyUser.getUsername().equals(presenter.getLobby().getHost())) {
                                ImageView img = new ImageView();
                                img.setFitHeight(20);
                                img.setFitWidth(15);
                                hbox.setSpacing(5);
//                                img.setImage(new Image("ank.png"));
                                hbox.getChildren().add(img);
                            }
                            setGraphic(hbox);
                        }
                    }
                };
                return cell;
            }
        };
        firstNameCol.setCellFactory(cellFactory);



        TableColumn lastNameCol = new TableColumn("Color");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<LobbyUser, String>("DUMMY"));

        Callback<TableColumn<LobbyUser, String>, TableCell<LobbyUser, String>> cellFactory2
                = new Callback<TableColumn<LobbyUser, String>, TableCell<LobbyUser, String>>() {
            @Override
            public TableCell call(final TableColumn<LobbyUser, String> param) {
                final TableCell<LobbyUser, String> cell = new TableCell<LobbyUser, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            LobbyUser lobbyUser = getTableView().getItems().get(getIndex());
                            HBox hbox = new HBox();
                            Rectangle color = new Rectangle();
                            color.setHeight(15);
                            color.setWidth(15);
                            hbox.setSpacing(5);


                            //Shit-Button-Lösung. Valve, pls fix
                            // Anmerkung: Ja shit Lösung weil er im Callback für die Colorzeile ist


                            color.setFill(Color.web(lobbyUser.getColor()));
                            color.setOnMouseClicked(event -> {
                                //Benutzer kann nur seine eigene Farbe ändern
                                if (presenter.getUser().getUsername().equals(lobbyUser.getUsername())) {
                                    presenter.sendChangeColorRequest();
                                    System.out.println(lobbyUser.getColor());
                                } else {
                                    System.out.println(presenter.getUser().getUsername());
                                    System.out.println(lobbyUser.getUsername());
                                }

                            });
                            hbox.getChildren().add(color);
                            setGraphic(hbox);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        lastNameCol.setCellFactory(cellFactory2);

        TableColumn joinCol = new TableColumn("Bereit");
        joinCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<LobbyUser, String>, TableCell<LobbyUser, String>> cellFactory3
                = new Callback<TableColumn<LobbyUser, String>, TableCell<LobbyUser, String>>() {
            @Override
            public TableCell call(final TableColumn<LobbyUser, String> param) {
                final TableCell<LobbyUser, String> cell = new TableCell<LobbyUser, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            LobbyUser lobbyUser = getTableView().getItems().get(getIndex());
                            if (lobbyUser.isReady()) {
                                this.setStyle("-fx-background-color: green");
                            } else {
                                this.setStyle("-fx-background-color: red");
                            }
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        joinCol.setCellFactory(cellFactory3);

        this.userTableView.getColumns().addAll(firstNameCol, lastNameCol, joinCol);
    }

    @FXML
    void handleUserTableViewClick(MouseEvent click){
        if (click.getClickCount() == 2) {
            String selectedUser = (String) userTableView.getSelectionModel().getSelectedItem();
            TextField messageInput = chatView.getChatTextField();
            messageInput.setText("@" + selectedUser + " ");
            messageInput.requestFocus();
            messageInput.end();
        }
    }

    @Override
    public Parent getRootParent() {
        return this.myParent;
    }

    @Override
    public void setUserListViewData(ObservableList<LobbyUser> datasource){
        this.userTableView.setItems(datasource);
    }
}
