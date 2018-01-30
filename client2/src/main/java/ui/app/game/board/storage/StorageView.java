package ui.app.game.board.storage;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.LobbyUser;
import helper.fxml.GenerateFXMLView;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import mvp.view.INavigateableView;
import java.util.ArrayList;


public class StorageView implements IStorageView{

  @FXML
  private Polygon polygon;

  @FXML
  private Rectangle flag;

  @FXML
  private AnchorPane root;

  @FXML
  private Label pointsLabel;

  @FXML
  private Circle pointsCircle;

  @FXML
  private Pane stonePane;

  private final INavigateableView parentView;
  private final StoragePresenter mainPresenter;
  private final EventBus eventBus;

  // Own Parent
  private Parent myParent;

  public StorageView(INavigateableView parentView, EventBus eventBus, Connection connection, LobbyUser user, boolean myStorage, int lobbyId){
    this.parentView = parentView;
    this.eventBus = eventBus;
    this.mainPresenter = new StoragePresenter(this, eventBus, connection, user, myStorage, lobbyId);
    initOwnView();
  }

  @Override
  public void initOwnView() {
    if(this.myParent == null)
      this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/app/main/game/StorageView.fxml", this, eventBus);
  }

  @FXML
  public void initialize(){
    setUserColor(mainPresenter.getUser().getColor());
  }

  public void setUserColor(String color) {
    flag.setFill(Color.web(color));
    pointsCircle.setFill(Color.web(color));
    polygon.setFill(Color.web(color));
    for (Rectangle rec : getColorStones()) {
      rec.setFill(Color.web(color));
    }
  }

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

  public ArrayList<Group> getStones() {
    ArrayList<Group> a = new ArrayList<>();
    for(Node node : stonePane.getChildren()){
      a.add((Group) node);
    }
    return a;
  }

  public Label getPointsLabel() {
    return this.pointsLabel;
  }

  @Override
  public Parent getRootParent() {
    return myParent;
  }
}

