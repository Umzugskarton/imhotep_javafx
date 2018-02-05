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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import mvp.view.IView;
import ui.app.game.board.Inventory.IInventoryView;
import ui.app.game.board.Inventory.InventoryView;
import ui.app.game.board.sites.market.cards.CardView;

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

  private final IView parentView;
  private final StoragePresenter mainPresenter;
  private final EventBus eventBus;

  private InventoryView inventoryView;

  // Own Parent
  private Parent myParent;

  public StorageView(IView parentView, EventBus eventBus, Connection connection, LobbyUser user, boolean myStorage, int lobbyId){
    this.parentView = parentView;
    this.eventBus = eventBus;
    inventoryView = new InventoryView(this, eventBus, connection, lobbyId );
    this.mainPresenter = new StoragePresenter(this, eventBus, connection, user, myStorage, lobbyId);
    initOwnView();
  }

  @Override
  public void initOwnView() {
    if(this.myParent == null)
      this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/app/game/board/StorageView.fxml", this, eventBus);
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


  public void highlightPointsLabel(boolean highlight) {
    if (highlight) {
     pointsLabel.setUnderline(true);
     pointsLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 14));
    } else {
      pointsLabel.setUnderline(false);
      pointsLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
    }
  }

  public void setPoints(int points) {
    pointsLabel.setText(points + "");
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

  @FXML
  public void showCardStack(){
    System.out.println("Cardstack gedrückt");
    this.inventoryView.addCard(new CardView(this, eventBus, new Connection(),  1231, 1));
    eventBus.post(this.inventoryView);
  }
}

