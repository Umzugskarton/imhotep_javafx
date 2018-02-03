package board.presenter;

import board.view.ShipViewImplFx;
import data.lobby.CommonLobby;
import events.SiteType;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class ShipPresenter {

  private ShipViewImplFx view;
  private CommonLobby lobby;
  private int[] cargo;
  private boolean docked;
  private SiteType location;

  public ShipPresenter(CommonLobby lobby, ShipViewImplFx view, int[] placement) {
    this.view = view;
    this.lobby = lobby;
    docked = false;
    this.cargo = placement;
    view.getSprite().setId("ship" + placement.length);
    ArrayList<Group> stones = view.getStones();
    for (Group g : stones) {
      g.setLayoutX(g.getLayoutX() - (4 - placement.length) * 2);
      g.setLayoutY(g.getLayoutY() - (4 - placement.length) * 10);
    }

    updateCargo();
  }

  public void setCargo(int[] cargo) {
    this.cargo = cargo;
    updateCargo();
  }

  public boolean isDocked() {
    return docked;
  }

  public void setDocked(boolean docked) {
    this.docked = docked;
  }

  public void setLocation(SiteType site) {
    for (Group p : view.getStones()) {
      p.setVisible(false);
    }
    location = site;
    docked = true;
  }

  private void updateCargo() {
    int i = 0;
    for (int owner : cargo) {
      if (owner != -1) {
        Group p = view.getStones().get(i);
        p.setVisible(true);
        Rectangle r = view.getColorStones().get(i);
        r.setFill(Color.web(lobby.getUserbyLobbyId(owner).getColor()));
      }
      i++;
    }
  }

}
