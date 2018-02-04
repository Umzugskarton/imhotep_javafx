package ui.app.game.board.sites.market.cards;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import events.app.game.CardChosenEvent;
import mvp.presenter.Presenter;
import requests.gamemoves.ChooseCardMove;

import java.util.ArrayList;

public class ChooseCardPresenter extends Presenter<IChooseCardView> {
  private Connection connection;
  private ArrayList<CardView> cardViews;
  private int lobbyId;


  public ChooseCardPresenter(IChooseCardView view, EventBus eventBus, Connection connection, int lobbyId) {
    super(view, eventBus);
    cardViews = new ArrayList<>();
    this.connection = connection;
    this.lobbyId = lobbyId;
    bind();
  }

  private void bind(){
    eventBus.register(this);
  }

  public void sendChooseCard(int cardId){
    if (cardViews.get(cardId).isAvailable()){
      connection.send(new ChooseCardMove(cardId, lobbyId));
    }
  }

  @Subscribe
  private void cardChoosen(CardChosenEvent e){
    e.getCardId();
  }
}
