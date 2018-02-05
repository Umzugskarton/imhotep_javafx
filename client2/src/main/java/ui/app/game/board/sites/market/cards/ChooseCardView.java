package ui.app.game.board.sites.market.cards;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.CommonLobby;
import helper.fxml.GenerateFXMLView;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import mvp.view.IView;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ChooseCardView implements IChooseCardView{

  @FXML
  private GridPane cardGrid;

  private final ChooseCardPresenter mainPresenter;
  private final IView parentview;
  private final EventBus eventBus;
  private final Connection connection;
  private ArrayList<CardView> cardViews;

  private Parent myParent;

  public ChooseCardView(IView view , EventBus eventBus, Connection connection , ArrayList<CardView> cardViews, CommonLobby lobby){
    this.parentview = view;
    this.eventBus = eventBus;
    this.cardViews = cardViews;
    this.connection = connection;
    mainPresenter = new ChooseCardPresenter(this, eventBus, connection, lobby.getLobbyId());
    initOwnView();
  }

  @FXML
  private void initialize(){
        cardViews.forEach(cardView -> cardGrid.getChildren().add(cardView.getRootParent()));
  }

  @Override
  public void setCards() {
    cardGrid.getChildren().clear();
    cardViews.forEach(cardView -> {
      if (cardView.isAvailable())
        cardGrid.getChildren().add(cardView.getRootParent());
    });
  }

  public List<CardView> getCardViews() {
    return cardViews;
  }

  public GridPane getCardGrid() {
    return cardGrid;
  }


  @Override
  public void initOwnView() {
    if (myParent == null)
      myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/app/game/board/ChooseCardView.fxml", this , eventBus);
  }

  @Override
  public Parent getRootParent() {
    return myParent;
  }

  @Override
  public String getTitle() {
    return this.getClass().getSimpleName();
  }

}
