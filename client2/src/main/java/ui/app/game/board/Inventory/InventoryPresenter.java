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
  private final int userId;
  private ArrayList<CardType> myCardTypes;

  public InventoryPresenter(IInventoryView view, EventBus eventBus, Connection connection,
      int userId) {
    this.connection = connection;
    this.eventBus = eventBus;
    this.view = view;
    this.userId = userId;
    bind();
  }

  private void bind() {
    eventBus.register(this);
  }

  @Subscribe
  private void onInventoryUpdateEvent(InventoryUpdateEvent event) {
    view.getCardGridPane().getChildren().clear();
    myCardTypes = event.getCardTypes().get(userId);
    event.getCardTypes().get(userId).forEach(cardType -> {
      CardView cardView = new CardView(view, eventBus, connection, userId);
      cardView.setCardType(cardType);
      view.addCard(cardView);
    });
  }

}
