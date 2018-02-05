package ui.app.lobby.usertable;

import data.lobby.LobbyUser;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class ReadyTableColum extends TableColumn<LobbyUser, String> {

  UserTablePresenter presenter;

  public ReadyTableColum(String text, UserTablePresenter presenter) {
    super(text);
    this.presenter = presenter;

    this.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

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
    this.setCellFactory(cellFactory);
  }
}
