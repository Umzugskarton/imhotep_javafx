package games.view;

import games.model.Lobby;
import games.presenter.GamesPresenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;



public class GamesViewImpl extends GridPane implements GamesView {
    private GamesPresenter gamesPresenter;
    private TableView<Lobby> table = new TableView();
    private int click = 0;

    public GamesViewImpl(GamesPresenter gamesPresenter) {
        this.gamesPresenter = gamesPresenter;
        buildGames();
    }

    public void buildGames() {
        GridPane root = this;
        root.setHgap(5);
        root.setVgap(5);
        root.setPadding(new Insets(5));

        TableColumn firstNameCol = new TableColumn("Lobby Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Lobby, String>("name"));
        TableColumn lastNameCol = new TableColumn("Belegung");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Lobby, String>("belegung"));
        TableColumn idCol = new TableColumn("id");
        idCol.setCellValueFactory(
                new PropertyValueFactory<Lobby, String>("id"));
        TableColumn joinCol = new TableColumn(" ");
        joinCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<Lobby, String>, TableCell<Lobby, String>> cellFactory
                = //
                new Callback<TableColumn<Lobby, String>, TableCell<Lobby, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Lobby, String> param) {
                        final TableCell<Lobby, String> cell = new TableCell<Lobby, String>() {
                            final Button btn = new Button("Join");
                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    Lobby lobby = getTableView().getItems().get(getIndex());
                                    HBox hbox = new HBox();
                                    ImageView img = new ImageView();
                                    img.setFitHeight(20);
                                    img.setFitWidth(15);
                                    hbox.setSpacing(5);
                                    img.setImage(new Image("ank.png"));
                                    if (lobby.hasPW()){
                                        hbox.getChildren().addAll(btn, img);
                                        btn.setOnAction(event -> popup(lobby));
                                        setGraphic(hbox);
                                    } else{
                                        btn.setOnAction(event -> gamesPresenter.joinLobby(lobby.getId(), null));
                                        setGraphic(btn);
                                    }
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        joinCol.setCellFactory(cellFactory);

        table.setOnMouseClicked( click ->{
                if (click.getClickCount() == 2 && table.getSelectionModel().getSelectedItem() != null) {
                    Lobby selectedLobby = table.getSelectionModel().getSelectedItem();
                    if (selectedLobby.hasPW()){
                      popup(selectedLobby);
                    }else {
                        gamesPresenter.joinLobby(selectedLobby.getId(), null);
                    }
                }
        });


        table.getColumns().addAll(idCol, firstNameCol, lastNameCol, joinCol);

        root.add(table ,0, 1);
    }

    public void popup(Lobby selectedLobby){
                // Popup mit Passworteingabe
                final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                VBox dialogVbox = new VBox(20);
                TextField password= new TextField();
                password.setPromptText("Passwort");
                Button sendPW = new Button("Senden");
                sendPW.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        gamesPresenter.joinLobby(selectedLobby.getId(), password.getText());
                    }
                });
                dialogVbox.getChildren().addAll(new Text("Enter Password:"), password, sendPW);
                Scene dialogScene = new Scene(dialogVbox, 300, 200);
                dialog.setScene(dialogScene);
                dialog.show();
    }

    public void initGameList(){
        //Liste der erstellter Spiele
        table.setItems(this.gamesPresenter.getGameList().getGames());

    }

}
