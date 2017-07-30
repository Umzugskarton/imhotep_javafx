package games.view;

import games.model.Lobby;
import games.presenter.GamesPresenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;



public class GamesViewImpl extends GridPane implements GamesView {
    private GamesPresenter gamesPresenter;
    private TableView<Lobby> table = new TableView();

    public GamesViewImpl(GamesPresenter gamesPresenter) {
        this.gamesPresenter = gamesPresenter;
        buildGames();
    }

    public void buildGames() {
        GridPane root = this;
        root.setHgap(5);
        root.setVgap(5);
        root.setPadding(new Insets(5));
        Button sendButton = new Button("Senden");
        sendButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

            }
        });

        TableColumn firstNameCol = new TableColumn("Lobby Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Lobby, String>("name"));
        TableColumn lastNameCol = new TableColumn("Belegung");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Lobby, String>("belegung"));
        TableColumn joinCol = new TableColumn("Join");
        joinCol.setCellValueFactory(new PropertyValueFactory<Lobby, String>("id"));

        table.getColumns().addAll(firstNameCol, lastNameCol, joinCol);

        root.add(table ,0, 1);
    }

    public void initGameList(){

        //Liste der eingeloggten Spieler als Item der View setzen
        table.setItems(this.gamesPresenter.getGameList().getGames());
        //ListView rechts auf der BorderPane platzieren

    }

}
