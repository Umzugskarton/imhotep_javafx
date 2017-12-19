package GameEvents;


import SRVevents.Event;

import java.util.ArrayList;
import java.util.Date;

public class GameInfoEvent implements Event {
  private String event = "gameInfo";
  private int myId;
  private ArrayList<int[]> ships = new ArrayList<>();
  private int round;
  private ArrayList<Integer> storages;
  private String[] order;
  private Date date;
  private int turnTime;

  public GameInfoEvent() {
    this.date = new Date();
  }

  public void setMyId(int id) {
    this.myId = id;
  }

  public int getMyId() {
    return myId;
  }

  public void setCurrentShips(int[] ship) {
    ships.add(ship);
  }

  public ArrayList<Integer> getStorages() {
    return storages;
  }

  public int getRound() {
    return round;
  }

  public void setTurnTime(int seconds) { this.turnTime = seconds; }

  public int getTurnTime() { return turnTime; }

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

  public void setStorages(ArrayList storages) {
    this.storages = storages;
  }

  public Date getDate() {
    return this.date;
  }
}
