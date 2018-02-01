package games.view;

import data.lobby.CommonLobby;
import games.presenter.GamesPresenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
  private TableView<CommonLobby> table = new TableView();
  private int click = 0;
  private Scene gamesScene;

  public GamesViewImpl(GamesPresenter gamesPresenter) {
    this.gamesPresenter = gamesPresenter;
    buildGames();
  }

  public void buildGames() {
    GridPane root = this;
    root.setHgap(5);
    root.setVgap(5);
    root.setPadding(new Insets(5));

    gamesScene = new Scene(root);

    TableColumn firstNameCol = new TableColumn("CLTLobby Name");
    firstNameCol.setCellValueFactory(new PropertyValueFactory<CommonLobby, String>("name"));
    TableColumn lastNameCol = new TableColumn("Belegung");
    lastNameCol.setCellValueFactory(new PropertyValueFactory<CommonLobby, String>("belegung"));
    TableColumn idCol = new TableColumn("id");
    idCol.setCellValueFactory(
        new PropertyValueFactory<CommonLobby, String>("id"));

    TableColumn joinCol = new TableColumn(" ");
    joinCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));


      Callback<TableColumn<CommonLobby, String>, TableCell<CommonLobby, String>> cellFactory
          = new Callback<TableColumn<CommonLobby, String>, TableCell<CommonLobby, String>>() {

        @Override
        public TableCell call(final TableColumn<CommonLobby, String> param) {
          final TableCell<CommonLobby, String> cell = new TableCell<CommonLobby, String>() {
            final Button btn = new Button("Join");

            @Override
            public void updateItem(String item, boolean empty) {
              super.updateItem(item, empty);
              if (empty) {
                setGraphic(null);
                setText(null);
              } else {
                CommonLobby CLTLobby = getTableView().getItems().get(getIndex());

                HBox hbox = new HBox();
                ImageView img = new ImageView();
                img.setFitHeight(20);
                img.setFitWidth(15);
                hbox.setSpacing(5);
                img.setImage(new Image("ank.png"));
                if (CLTLobby.hasPW()) {
                  hbox.getChildren().addAll(btn, img);
                  btn.setOnAction(event -> popup(CLTLobby));
                  setGraphic(hbox);
                } else {
                  btn.setOnAction(event -> gamesPresenter.joinLobby(CLTLobby.getLobbyId(), null));
                  if (CLTLobby.getSize() > CLTLobby.getUsercount()) {
                    setGraphic(btn);
                  }
                }
                setText(null);
              }
            }
          };
          return cell;
        }
      };
      joinCol.setCellFactory(cellFactory);

    table.setOnMouseClicked(click -> {
      if (click.getClickCount() == 2 && table.getSelectionModel().getSelectedItem() != null) {
        CommonLobby selectedLobby = table.getSelectionModel().getSelectedItem();
        if (selectedLobby.hasPW()) {
          popup(selectedLobby);
        } else {
          gamesPresenter.joinLobby(selectedLobby.getLobbyId(), null);
        }
      }
    });

    table.getColumns().addAll(idCol, firstNameCol, lastNameCol, joinCol);

    root.add(table, 0, 1);
  }



  public void popup(CommonLobby selectedCLTLobby) {
    // Popup mit Passworteingabe
    final Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    VBox dialogVbox = new VBox(20);
    dialogVbox.getStyleClass().add("dialog");
    TextField password = new TextField();
    password.setPromptText("Passwort");
    Button sendPW = new Button("Senden");
    sendPW.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        gamesPresenter.joinLobby(selectedCLTLobby.getLobbyId(), password.getText());
        dialog.close();
      }
    });
    dialogVbox.getChildren().addAll(new Text("Enter Password:"), password, sendPW);
    Scene dialogScene = new Scene(dialogVbox, 300, 200);
    dialog.setScene(dialogScene);
    dialog.show();
  }

  public void initGameList() {
    //Liste der erstellter Spiele
    table.setItems(this.gamesPresenter.getGameList().getGames());
  }

  public Scene getGamesScene() {
    return this.gamesScene;
  }
}