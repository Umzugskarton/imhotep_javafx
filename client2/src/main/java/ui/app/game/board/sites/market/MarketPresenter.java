package ui.app.game.board.sites.market;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import events.app.game.GameInfoEvent;
import events.app.game.InventoryUpdateEvent;
import mvp.presenter.Presenter;
import ui.app.game.board.sites.ISitePresenter;


public class MarketPresenter extends Presenter<IMarketView> implements ISitePresenter {

  private final Connection connection;



  public MarketPresenter(IMarketView view, EventBus eventBus, Connection connection) {
    super(view, eventBus);
    this.connection = connection;
    bind();
  }

  private void bind() {
    eventBus.register(this);
  }

  @Subscribe
  private void onGameInfoEvent(GameInfoEvent event) {
    for (int i = 0; i < event.getCards().size(); i++) {
      view.getCardViews().get(i).setCardType(event.getCards().get(i));
      view.getCards().get(i)
          .setId(view.getCardViews().get(i).getStyleType(event.getCards().get(i)));
      view.getCards().get(i).setOpacity(1);
      view.setCardVisibility(i, true);


    }
    view.initChooseCardView();
  }

  @Subscribe
  private void onInventoryUpdateEvent(InventoryUpdateEvent event){
    if (event.getChosenCards().size() > 0 ) {
      event.getChosenCards().forEach(id -> {
        view.setCardVisibility(id, false);
        view.getCards().get(id).setOpacity(0);
      });
    }
  }

}
