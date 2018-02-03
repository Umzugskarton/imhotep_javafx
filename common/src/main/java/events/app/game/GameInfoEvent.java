package events.app.game;

import requests.gamemoves.CardType;

import java.util.ArrayList;
import java.util.List;

public class GameInfoEvent extends GameEvent {

  private int myId;
  private ArrayList<int[]> ships = new ArrayList<>();
  private int round;
  private ArrayList<Integer> storages;
  private String[] order;
  private String[] siteString;
  private int turnTime;
  private int[] sitesAllocation;
  private ArrayList<CardType.Type> cards = new ArrayList<>();

  public void setMyId(int id) {
    this.myId = id;
  }

  public int getMyId() {
    return myId;
  }

  public int[] getSitesAllocation() {
    return sitesAllocation;
  }

  public void setSitesAllocation(int[] sitesAllocation) {
    this.sitesAllocation = sitesAllocation;
  }

  public String[] getSiteString() {
    return siteString;
  }

  public void setSiteString(String[] siteString) {
    this.siteString = siteString;
  }

  public void setCurrentShips(int[] ship) {
    ships.add(ship);
  }

  public ArrayList<Integer> getStorages() {
    return storages;
  }

  public void setCards(List<CardType.Type> type){
    cards = (ArrayList<CardType.Type>) type;
  }

  public List<CardType.Type> getCards(){
    return cards;
  }

  public int getRound() {
    return round;
  }

  public void setTurnTime(int seconds) {
    this.turnTime = seconds;
  }

  public int getTurnTime() {
    return turnTime;
  }

  public ArrayList<int[]> getShips() {
    return ships;
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

  public void setStorages(ArrayList<Integer> storages) {
    this.storages = storages;
  }
}
