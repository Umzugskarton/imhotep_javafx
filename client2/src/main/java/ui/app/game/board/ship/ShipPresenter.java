package ui.app.game.board.ship;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import data.lobby.CommonLobby;
import events.app.game.GameInfoEvent;
import events.app.game.ShipDockedEvent;
import events.app.game.ShipLoadedEvent;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import mvp.presenter.Presenter;

public class ShipPresenter extends Presenter<IShipView> {

  private CommonLobby lobby;
  private int[] cargo;
  private boolean docked;
  private String location;
  private final Connection connection;
  private final int shipId;

  public ShipPresenter(IShipView view, EventBus eventBus, Connection connection, int[] cargo,
      CommonLobby lobby, int shipId) {
    super(view, eventBus);
    this.lobby = lobby;
    docked = false;
    this.shipId = shipId;
    this.cargo = cargo;
    this.connection = connection;
    bind();
  }

  public void bind() {
    eventBus.register(this);
  }

  @Subscribe
  public void setCargo(ShipLoadedEvent e) {
    if (shipId == e.getShipID()) {
      this.cargo = e.getCargo();
      updateCargo();
    }
  }

  @Subscribe
  public void dumpCargo(ShipDockedEvent e) {
    if (shipId == e.getShipID()) {
      for (Group p : view.getStones()) {
        p.setVisible(false);
      }
      docked = true;
    }
  }

  @Subscribe
  public void initCargo(GameInfoEvent e) {
    cargo = e.getShips().get(shipId);
    for (int i = 0; i < cargo.length; i++) {
      System.out.println("CARGO SHIP " + shipId + " : " + cargo[i]);
    }

    updateCargo();
  }

  public boolean isDocked() {
    return docked;
  }

  public void setDocked(boolean docked) {
    this.docked = docked;
  }

  public void setLocation(String site) {
    for (Group p : view.getStones()) {
      p.setVisible(false);
    }
    location = site;
    docked = true;
  }

  private void updateCargo() {
    int i = 0;
    System.out.println("Been there done that");
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
