package ui.app.lobby.usertable;

import data.lobby.LobbyUser;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;

public class ColorTableColum extends TableColumn<LobbyUser, String> {

    UserTablePresenter presenter;

    public ColorTableColum(String text, UserTablePresenter presenter) {
        super(text);
        this.presenter = presenter;

        this.setCellValueFactory(new PropertyValueFactory<LobbyUser, String>("DUMMY"));

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
                            HBox hbox = new HBox();
                            Rectangle color = new Rectangle();
                            color.setHeight(15);
                            color.setWidth(15);
                            hbox.setSpacing(5);

                            color.setFill(Color.web(lobbyUser.getColor()));
                            hbox.getChildren().add(color);
                            setGraphic(hbox);
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
