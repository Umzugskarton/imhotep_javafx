package ui.app.game.board.sites.market.cards;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import events.app.game.ChooseCardEvent;
import mvp.presenter.Presenter;
import requests.gamemoves.ChooseCardMove;
import java.util.ArrayList;
import java.util.List;

public class ChooseCardPresenter extends Presenter<IChooseCardView> {
  private Connection connection;
  private List<CardView> cardViews;
  private int lobbyId;


  public ChooseCardPresenter(IChooseCardView view, EventBus eventBus, Connection connection, int lobbyId) {
    super(view, eventBus);
    this.lobbyId = lobbyId;
    cardViews = new ArrayList<>();
    this.connection = connection;
    this.lobbyId = lobbyId;
    cardViews = view.getCardViews();
    bind();
  }

  private void bind(){
    eventBus.register(this);
  }

  public void sendChooseCard(int cardId){
    if (cardViews.get(cardId).isAvailable()){
      connection.send(new ChooseCardMove(cardId,lobbyId));
    }
  }

  @Subscribe
  private void onChooseCardEvent(ChooseCardEvent e){
    e.getChoosenCardsId().forEach(card -> {
      cardViews.get((card)).setAvailable(false);
      view.getCardGrid().getChildren().remove(cardViews.get(card).getRootParent());
      cardViews.remove((int)card);
    });

    cardViews.forEach(cardView -> cardView.setClickable(true));
    eventBus.post(view);
  }

}
