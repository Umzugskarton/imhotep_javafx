package ui.app.game.board.Inventory;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import events.app.game.InventoryUpdateEvent;
import mvp.presenter.Presenter;
import requests.gamemoves.CardType;
import ui.app.game.board.sites.market.cards.CardView;

import java.util.ArrayList;

public class InventoryPresenter extends Presenter<IInventoryView> {

  private final Connection connection;
  private final int playerId;
  private ArrayList<CardType> myCardTypes;

  public InventoryPresenter(IInventoryView view, EventBus eventBus, Connection connection,
      int playerId) {
    this.connection = connection;
    this.eventBus = eventBus;
    this.view = view;
    this.playerId = playerId;
    bind();
  }

  private void bind() {
    eventBus.register(this);
  }

  @Subscribe
  private void onInventoryUpdateEvent(InventoryUpdateEvent event) {
    view.getCardGridPane().getChildren().clear();
    myCardTypes = event.getCardTypes().get(playerId);
    event.getCardTypes().get(playerId).forEach(cardType -> {
      CardView cardView = new CardView(view, eventBus, connection, playerId);
      cardView.setCardType(cardType);
      view.addCard(cardView);
    });
  }

}
