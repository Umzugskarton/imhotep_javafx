package ui.app.game.board.sites.market;


import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.CommonLobby;
import helper.fxml.GenerateFXMLView;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import mvp.view.IView;
import ui.app.game.board.sites.market.cards.CardView;
import ui.app.game.board.sites.market.cards.ChooseCardView;
import ui.dialog.IDialogView;

import java.util.ArrayList;

public class MarketView implements IMarketView {

  @FXML
  private Pane cardPane;

  private final IView parentView;
  private final MarketPresenter mainPresenter;
  private final EventBus eventBus;
  private final ArrayList<CardView> cardViews = new ArrayList<>();
  private ChooseCardView chooseCardView;

  private final Connection connection;

  private final CommonLobby lobby;

  // Own Parent
  private Parent myParent;

  public MarketView(IView parentView, EventBus eventBus, Connection connection, CommonLobby lobby) {
    this.parentView = parentView;
    this.lobby = lobby;
    this.eventBus = eventBus;
    this.connection = connection;
    this.mainPresenter = new MarketPresenter(this, eventBus, connection);
    bind();
    initOwnView();
  }

  private void bind() {
    eventBus.register(this);
  }

  @FXML
  private void initialize() {
    for (int i = 0; i < 4; i++) {
      cardViews.add(new CardView(this, eventBus, connection, i, lobby.getLobbyId()));
    }
  }

  @FXML
  private void showCard0() {
    showOnDialogPane(cardViews.get(0));
  }

  @FXML
  private void showCard1() {
    showOnDialogPane(cardViews.get(1));
  }

  @FXML
  private void showCard2() {
    showOnDialogPane(cardViews.get(2));
  }

  @FXML
  private void showCard3() {
    showOnDialogPane(cardViews.get(3));
  }

  private void showOnDialogPane(IDialogView view) {
    eventBus.post(view);
  }

  public void initChooseCardView() {
    chooseCardView = new ChooseCardView(this, eventBus, connection, cardViews, lobby);
  }

  @FXML
  public void setCardVisibility(int cardId, boolean visible){
    Pane card = getCards().get(cardId);
    card.setVisible(visible);
  }

  @Override
  public ArrayList<CardView> getCardViews() {
    return cardViews;
  }

  @Override
  public ArrayList<Pane> getCards() {
    ArrayList<Pane> cardPanes = new ArrayList<>();
    for (Node node : cardPane.getChildren()) {
      cardPanes.add((Pane) node);
    }
    return cardPanes;
  }

  @Override
  public void initOwnView() {
    if (myParent == null) {
      this.myParent = GenerateFXMLView.getINSTANCE()
          .loadView("/ui/fxml/app/game/sites/MarketView.fxml", this, eventBus);
    }
  }

  @Override
  public Parent getRootParent() {
    return myParent;
  }

}

