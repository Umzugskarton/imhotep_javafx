package ui.app.game.board.sites.obelisks;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import data.lobby.CommonLobby;
import events.SiteType;
import events.app.game.ShipDockedEvent;
import events.app.game.StoneAddedToSiteEvent;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import mvp.presenter.Presenter;
import ui.app.game.board.sites.ISitePresenter;
import ui.app.game.board.sites.ISiteView;

import java.util.ArrayList;


public class ObelisksPresenter extends Presenter<ISiteView> implements ISitePresenter {

  private final Connection connection;
  private CommonLobby lobby;

  public ObelisksPresenter(ISiteView view, EventBus eventBus, Connection connection,
      CommonLobby lobby) {
    super(view, eventBus);
    this.lobby = lobby;
    this.connection = connection;
    bind();
  }

  private void bind() {
    eventBus.register(this);
  }


  public void setStones(ArrayList<Integer> newStones) {
    int[] playerStones = new int[lobby.getUsers().size()];
    for (Integer stone : newStones) {
      playerStones[stone]++;
    }
    int k = 0;
    for (int player : playerStones) {
      ArrayList<Group> stoneGroups = getView().getStones();
      int playerroot = (player * 5);
      for (int i = 0; i < player; i++) {
        stoneGroups.get(playerroot + i).setVisible(true);
        Rectangle r = getView().getColorStones(playerroot + i);
        r.setFill(Color.web(lobby.getUserbyLobbyId(k).getColor()));
      }
      k++;
    }

  }

  @Subscribe
  public void onStoneAddedEvent(StoneAddedToSiteEvent event){
    if (event.getSiteType().equals(SiteType.OBELISKS) ){
      setStones(event.getNewStones());
    }
  }

  @Subscribe
  public void onShiplDockedEvent(ShipDockedEvent event) {
    if (event.getSite().equals(SiteType.OBELISKS) ){
      setStones(event.getNewStones());
    }
  }
}
