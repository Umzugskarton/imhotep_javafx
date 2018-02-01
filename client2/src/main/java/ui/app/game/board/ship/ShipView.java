package ui.app.game.board.ship;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.Lobby;
import helper.fxml.GenerateFXMLView;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import mvp.view.INavigateableView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created on 28.01.2018.
 */
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


  private final INavigateableView parentView;
  private final ShipPresenter mainPresenter;
  private final EventBus eventBus;

  private Lobby lobby;
  private int[] cargo;

  // Own Parent
  private Parent myParent;

  public ShipView(INavigateableView parentView, EventBus eventBus, Connection connection, Lobby lobby , int[] cargo, int shipId){
    this.parentView = parentView;
    this.lobby = lobby;
    this.cargo = cargo;
    this.eventBus = eventBus;
    this.mainPresenter = new ShipPresenter(this, eventBus, connection, cargo, lobby, shipId);
    initOwnView();
  }

  @Override
  public void initOwnView() {
    if(this.myParent == null) {
      this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/app/game/board/ShipView.fxml", this, eventBus);
      sprite.setId("ship" + cargo.length);
      ArrayList<Group> stones = getStones();
      //todo bessere Methode um neue pos der Steine zu bestimmen bei verschiedenen Schiffgrößen
      for (Group g : stones) {
        g.setLayoutX(g.getLayoutX() - (4 - cargo.length) * 2);
        g.setLayoutY(g.getLayoutY() - (4 - cargo.length) * 10);
      }
    }
  }

  @Override
  public Parent getRootParent() {
    return myParent;
  }

  public ArrayList<Group> getStones() {
    ArrayList<Group> a = new ArrayList<>();
    Collections.addAll(a, stone0, stone1,stone2,stone3);
    return a;
  }


  // TODO Auslagerung in allen Spielgegenständen
  public  ArrayList<Rectangle> getColorStones(){
    ArrayList<Rectangle> a = new ArrayList<>();
    for (Group g : getStones()){
      int i = 0;
      for(Node x : g.getChildren()){
        if (i==0){
          a.add( (Rectangle) x);
        }
        i++;
      }
    }
    return a;
  }
}
