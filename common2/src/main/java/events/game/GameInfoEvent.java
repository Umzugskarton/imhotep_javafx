package events.game;

import java.util.ArrayList;

public class GameInfoEvent extends GameEvent {
  private String event = "gameInfo";
  private ArrayList<int[]> ships;
  private int round;
  private boolean[] storages;
  private String[] order;

  public GameInfoEvent() {
  }

  public void setCboats(int[] ship) {
    ships.add(ship);
  }

  public boolean[] getStorages() {
    return storages;
  }

  public int getRound() {
    return round;
  }

  public ArrayList<int[]> getShips() {
    return ships;
  }

  public String getEvent() {
    return event;
  }

  public String[] getOrder() {
    return order;
  }

  public void setOrder(String[] order) {
    this.order = order;
  }

  public void setRound(int round) {
    this.round = round;
  }

  public void setStorages(boolean[] storages) {
    this.storages = storages;
  }
}
