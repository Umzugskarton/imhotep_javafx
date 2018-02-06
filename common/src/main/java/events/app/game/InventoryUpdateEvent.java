package events.app.game;

import requests.gamemoves.CardType;

import java.util.ArrayList;

public class InventoryUpdateEvent extends GameEvent{
  private ArrayList<ArrayList<CardType>>  cardTypes;
  private ArrayList<Integer> chosenCards;

  public InventoryUpdateEvent(ArrayList<ArrayList<CardType>> cardTypes, int lobbyId){
    this.cardTypes = cardTypes;
    chosenCards = new ArrayList<>();
    this.lobbyId = lobbyId;
  }

  public InventoryUpdateEvent(ArrayList<ArrayList<CardType>> cardTypes, ArrayList<Integer> chosenCards, int lobbyId){
    this.cardTypes = cardTypes;
    this.chosenCards = chosenCards;
    this.lobbyId = lobbyId;
  }

  public ArrayList<Integer> getChosenCards() {
    return chosenCards;
  }

  public ArrayList<ArrayList<CardType>> getCardTypes() {
    return cardTypes;
  }
}
