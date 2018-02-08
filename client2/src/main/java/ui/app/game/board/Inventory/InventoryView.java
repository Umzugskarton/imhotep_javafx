package ui.app.game.board.Inventory;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import helper.fxml.GenerateFXMLView;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import mvp.view.IView;
import ui.app.game.board.sites.market.cards.CardView;

public class InventoryView implements IInventoryView {

  @FXML
  private GridPane cardGridPane;

  private final IView parentView;
  private final InventoryPresenter mainPresenter;
  private final EventBus eventBus;

  private Connection connection;

  // Own Parent
  private Parent myParent;

  public InventoryView(IView parentView, EventBus eventBus, Connection connection, int user) {
    this.parentView = parentView;
    this.connection = connection;
    this.eventBus = eventBus;
    this.mainPresenter = new InventoryPresenter(this, eventBus, connection, user);
    initOwnView();
  }

  @Override
  public String getTitle() {
    return null;
  }

  @Override
  public Parent getRootParent() {
    return myParent;
  }

  @Override
  public void initOwnView() {
    if (myParent == null) {
      myParent = GenerateFXMLView.getINSTANCE()
          .loadView("/ui/fxml/app/game/board/InventoryView.fxml", this, eventBus);
    }
  }

  @Override
  public GridPane getCardGridPane() {
    return cardGridPane;
  }

  @Override
  public void addCard(CardView cardView) {
    Integer maxColIndex = cardGridPane.getChildren().stream().filter(node -> node.isManaged()).map(node -> GridPane.getColumnIndex(node)).max((o1, o2) -> (o1 > o2?o1:o2)).orElse(-1);
    cardGridPane.add(cardView.getRootParent() ,maxColIndex+1, maxColIndex/4);
  }

  @Override
  public void closeDialog() {
    //TODO hier sollte irgendwas passieren, weil ich ein Dialog bin
  }
}
