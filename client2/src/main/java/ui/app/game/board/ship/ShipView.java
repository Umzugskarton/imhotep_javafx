package ui.app.game.board.ship;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.CommonLobby;
import helper.fxml.GenerateFXMLView;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import mvp.view.IView;

import java.util.ArrayList;
import java.util.Collections;


public class ShipView implements IShipView {

  @FXML
  private Group stone0;

  @FXML
  private Group stone1;

  @FXML
  private Group stone2;

  @FXML
  private Group stone3;

  @FXML
  private Pane sprite;


  private final IView parentView;
  private final ShipPresenter mainPresenter;
  private final EventBus eventBus;

  private CommonLobby lobby;
  private int[] cargo;

  // Own Parent
  private Parent myParent;

  public ShipView(IView parentView, EventBus eventBus, Connection connection, CommonLobby lobby,
      int[] cargo, int shipId) {
    this.parentView = parentView;
    this.lobby = lobby;
    this.cargo = cargo;
    this.eventBus = eventBus;
    this.mainPresenter = new ShipPresenter(this, eventBus, connection, cargo, lobby, shipId);
    initOwnView();
  }


  public int[] getCargo() {
    return cargo;
  }

  @Override
  public void initOwnView() {
    if (this.myParent == null) {
      this.myParent = GenerateFXMLView.getINSTANCE()
          .loadView("/ui/fxml/app/game/board/ShipView.fxml", this, eventBus);
      sprite.setId("ship" + cargo.length);
      ArrayList<Group> stones = getStones();
      //todo bessere Methode um neue pos der Steine zu bestimmen bei verschiedenen Schiffgrößen
      for (Group g : stones) {
        g.setLayoutX(g.getLayoutX() - (4 - cargo.length) * 2.5);
        g.setLayoutY(g.getLayoutY() - (4 - cargo.length) * 3);
      }
    }
  }

  @Override
  public Parent getRootParent() {
    return myParent;
  }

  public ArrayList<Group> getStones() {
    ArrayList<Group> a = new ArrayList<>();
    Collections.addAll(a, stone0, stone1, stone2, stone3);
    return a;
  }


  // TODO Auslagerung in allen Spielgegenständen
  public ArrayList<Rectangle> getColorStones() {
    ArrayList<Rectangle> a = new ArrayList<>();
    for (Group g : getStones()) {
      int i = 0;
      for (Node x : g.getChildren()) {
        if (i == 0) {
          a.add((Rectangle) x);
        }
        i++;
      }
    }
    return a;
  }
}
