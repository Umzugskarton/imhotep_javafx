package ui.app.game.board.sites.defaultSites;

import events.app.game.ShipDockedEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import data.lobby.CommonLobby;
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
  private final  String site;

  public DefaultSitePresenter(ISiteView view, EventBus eventBus, Connection connection, CommonLobby lobby, String site) {
    super(view, eventBus);
    this.site = site;
    this.connection = connection;
    this.lobby = lobby;
  }

  @Subscribe
  public void setStones(ShipDockedEvent e) {
    if (site.equals(e.getSite())) {
      ArrayList<Group> stoneGroups = getView().getStones();
      for (int i = 0; i < e.getNewstones().size(); i++) {
        stoneGroups.get(i).setVisible(true);
        Rectangle r = getView().getColorStones(i);
        r.setFill(Color.web(lobby.getUserbyLobbyId(e.getNewstones().get(i)).getColor()));
      }
    }
  }
}
