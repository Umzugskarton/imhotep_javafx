package ui.app.game.board.sites.defaultSites;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.CommonLobby;
import data.lobby.LobbyUser;
import helper.fxml.GenerateFXMLView;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import mvp.view.INavigateableView;
import ui.app.game.board.sites.ISitePresenter;
import ui.app.game.board.sites.ISiteView;
import ui.app.game.board.sites.Obelisks.ObelisksPresenter;
import ui.app.game.board.sites.market.MarketPresenter;
import ui.app.game.board.storage.StoragePresenter;

import java.util.ArrayList;

public class DefaultSiteView implements ISiteView {

  @FXML
  private Pane stonePane;

  private final INavigateableView parentView;
  private final ISitePresenter mainPresenter;
  private final EventBus eventBus;
  private String site;

  // Own Parent
  private Parent myParent;

  public DefaultSiteView(INavigateableView parentView, EventBus eventBus, Connection connection, String site, CommonLobby lobby){
    this.parentView = parentView;
    this.eventBus = eventBus;
    this.site=site;
    if (site.equals("Market")){
      mainPresenter = new MarketPresenter();
    }
    else if (site.equals("Obelisks")){
      mainPresenter = new ObelisksPresenter(this,  eventBus, connection, lobby);
    }
    else {
      mainPresenter = new DefaultSitePresenter(this, eventBus, connection, lobby);
    }
    initOwnView();
  }


  @Override
  public void initOwnView() {
    if(this.myParent == null)
      this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/app/game/sites/" + site + "View.fxml", this, eventBus);
  }

  @Override
  public ArrayList<Group> getStones() {
    ArrayList<Group> stones = new ArrayList<>();
    for (Node node : stonePane.getChildren()){
      stones.add((Group) node);
    }
    return stones;
  }

  @Override
  public Rectangle getColorStones(int i) {
    return (Rectangle) getStones().get(i).getChildren().get(0);
  }

  @Override
  public Parent getRootParent() {
    return myParent;
  }


}
