package board.view;


import board.presenter.StoragePresenter;
import commonLobby.LobbyUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.io.IOException;


public class StorageViewImplFx {

  @FXML
  private Polygon polygon;

  @FXML
  private Rectangle flag;

  private StoragePresenter presenter;

  @FXML
  private AnchorPane root;


  public StorageViewImplFx() {

  }

  public void setUserColor(String color) {
    flag.setFill(Color.web(color));
    polygon.setFill(Color.web(color));
  }

  AnchorPane getRoot() {
    return root;
  }
}
