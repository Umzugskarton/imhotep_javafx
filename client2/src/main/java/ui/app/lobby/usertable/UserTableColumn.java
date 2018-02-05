package ui.app.lobby.usertable;

import data.lobby.LobbyUser;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class UserTableColumn extends TableColumn<LobbyUser, String> {

  UserTablePresenter presenter;

  public UserTableColumn(String text, UserTablePresenter presenter) {
    super(text);
    this.presenter = presenter;

    this.setCellValueFactory(new PropertyValueFactory<LobbyUser, String>("DUMMY"));

    Callback<javafx.scene.control.TableColumn<LobbyUser, String>, TableCell<LobbyUser, String>> cellFactory
        = new Callback<javafx.scene.control.TableColumn<LobbyUser, String>, TableCell<LobbyUser, String>>() {
      @Override
      public TableCell call(final javafx.scene.control.TableColumn<LobbyUser, String> param) {
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
                //img.setImage(new Image("ank.png"));
                hbox.getChildren().add(img);
              }
              setGraphic(hbox);
            }
          }
        };
        return cell;
      }
    };
    this.setCellFactory(cellFactory);
  }
}
