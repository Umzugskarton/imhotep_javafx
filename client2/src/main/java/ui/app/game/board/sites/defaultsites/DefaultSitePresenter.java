package ui.app.game.board.sites.defaultsites;

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

public class DefaultSitePresenter extends Presenter<ISiteView> implements ISitePresenter {
  private final Connection connection;
  private CommonLobby lobby;
  private final  SiteType site;

  public DefaultSitePresenter(ISiteView view, EventBus eventBus, Connection connection, CommonLobby lobby, SiteType site) {
    super(view, eventBus);
    this.site = site;
    this.connection = connection;
    this.lobby = lobby;
    bind();
  }

  private void bind(){
    eventBus.register(this);
  }

  public void setStones(ArrayList<Integer> newStones){

      ArrayList<Group> stoneGroups = getView().getStones();
      for (int i = 0; i <newStones.size(); i++) {
        stoneGroups.get(i).setVisible(true);
        Rectangle r = getView().getColorStones(i);
        r.setFill(Color.web(lobby.getUserbyLobbyId(newStones.get(i)).getColor()));
      }
  }

  @Subscribe
  public void onStoneDockedEvent(ShipDockedEvent event) {
    if (site.equals(event.getSite()) ){
      setStones(event.getNewStones());
    }
  }

  @Subscribe
  public void onSoneAddedEvent(StoneAddedToSiteEvent event){
    if (site.equals(event.getSiteType()) ){
      setStones(event.getNewStones());
    }
  }
}
