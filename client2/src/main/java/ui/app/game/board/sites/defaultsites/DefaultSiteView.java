package ui.app.game.board.sites.defaultsites;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.CommonLobby;
import helper.fxml.GenerateFXMLView;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import mvp.view.IView;
import ui.app.game.board.sites.ISitePresenter;
import ui.app.game.board.sites.ISiteView;
import ui.app.game.board.sites.obelisks.ObelisksPresenter;

import java.util.ArrayList;


public class DefaultSiteView implements ISiteView {

  @FXML
  private Pane stonePane;

  private final IView parentView;
  private final ISitePresenter mainPresenter;
  private final EventBus eventBus;
  private String site;

  // Own Parent
  private Parent myParent;

  public DefaultSiteView(IView parentView, EventBus eventBus, Connection connection, String site,
      CommonLobby lobby) {
    this.parentView = parentView;
    this.eventBus = eventBus;
    this.site = site;
    if (site.equals("Obelisks")) {
      mainPresenter = new ObelisksPresenter(this, eventBus, connection, lobby);
    } else {
      mainPresenter = new DefaultSitePresenter(this, eventBus, connection, lobby, site);
    }
    initOwnView();
  }


  @Override
  public void initOwnView() {
    if (this.myParent == null) {
      this.myParent = GenerateFXMLView.getINSTANCE()
          .loadView("/ui/fxml/app/game/sites/" + site + "View.fxml", this, eventBus);
    }
  }

  @Override
  public ArrayList<Group> getStones() {
    ArrayList<Group> stones = new ArrayList<>();
    for (Node node : stonePane.getChildren()) {
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
