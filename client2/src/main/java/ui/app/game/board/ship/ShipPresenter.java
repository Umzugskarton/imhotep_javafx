package ui.app.game.board.ship;

import GameEvents.ShipLoadedEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import data.lobby.Lobby;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import mvp.presenter.Presenter;

public class ShipPresenter extends Presenter<IShipView>{
  private Lobby lobby;
  private int[] cargo;
  private boolean docked;
  private String location;
  private final Connection connection;
  private final int shipId;

  public ShipPresenter(IShipView view, EventBus eventBus, Connection connection, int[] cargo, Lobby lobby, int shipId) {
    super( view , eventBus);
    this.lobby = lobby;
    docked = false;
    this.shipId = shipId;
    this.cargo = cargo;
    this.connection= connection;

    updateCargo();
  }

  @Subscribe
  public void setCargo(ShipLoadedEvent e) {
    this.cargo = e.getCargo();
    updateCargo();
  }

  public boolean isDocked(){
    return docked;
  }

  public void setDocked(boolean docked) {
    this.docked = docked;
  }

  public void setLocation(String site){
    for (Group p :view.getStones()){
      p.setVisible(false);
    }
    location = site;
    docked = true;
  }

  private void updateCargo(){
    int i = 0;
    for (int owner : cargo){
      if (owner != -1){
        Group p = view.getStones().get(i);
        p.setVisible(true);
        Rectangle r = view.getColorStones().get(i);
        r.setFill(Color.web(lobby.getUserbyLobbyId(owner).getColor()));
      }
      i++;
    }
  }

}
