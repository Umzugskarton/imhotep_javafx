package events.app.game;

import events.SiteType;
import java.util.ArrayList;
import java.util.List;
import requests.gamemoves.CardType;

public class GameInfoEvent extends GameEvent {

  private int myId;
  private ArrayList<int[]> ships = new ArrayList<>();
  private int round;
  private ArrayList<Integer> storages;
  private String[] order;
  private List<SiteType> siteTypes;
  private int turnTime;
  private int[] sitesAllocation;
  private ArrayList<CardType> cards = new ArrayList<>();


  public GameInfoEvent(int lobbyId) {
    this.lobbyId = lobbyId;
  }

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

  public List<SiteType> getSiteTypes() {
    return siteTypes;
  }

  public void setSiteTypes(List<SiteType> siteTypes) {
    this.siteTypes = siteTypes;
  }

  public void setCurrentShips(int[] ship) {
    ships.add(ship);
  }

  public ArrayList<Integer> getStorages() {
    return storages;
  }

  public void setCards(List<CardType> type) {
    cards = (ArrayList<CardType>) type;
  }

  public List<CardType> getCards() {
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
