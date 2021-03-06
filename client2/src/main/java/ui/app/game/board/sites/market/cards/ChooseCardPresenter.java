package ui.app.game.board.sites.market.cards;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import events.app.game.ChooseCardEvent;
import mvp.presenter.Presenter;
import requests.gamemoves.ChooseCardMove;

import java.util.List;

public class ChooseCardPresenter extends Presenter<IChooseCardView> {

  private Connection connection;
  private List<CardView> cardViews;
  private int lobbyId;


  public ChooseCardPresenter(IChooseCardView view, EventBus eventBus, Connection connection,
      int lobbyId, List<CardView> cardViews) {
    super(view, eventBus);
    this.lobbyId = lobbyId;
    this.cardViews = cardViews;
    this.connection = connection;
    this.lobbyId = lobbyId;
    bind();
  }

  private void bind() {
    eventBus.register(this);
  }

  public void sendChooseCard(int cardId) {
    if (cardViews.get(cardId).isAvailable()) {
      connection.send(new ChooseCardMove(cardId, lobbyId));
    }
  }

  @Subscribe
  private void onChooseCardEvent(ChooseCardEvent e) {
    for (int i : e.getChoosenCardsId()) {
      if(i != -1)
        cardViews.get(i).setAvailable(false);
    }
    cardViews.forEach(cardView -> cardView.setClickable(true));
    view.resetCards();
    view.setCards();
    eventBus.post(view);
  }

}
