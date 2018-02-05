package events.app.game;

import requests.gamemoves.CardType;

import java.util.ArrayList;

public class InventoryUpdateEvent extends GameEvent{
  private ArrayList<ArrayList<CardType>>  cardTypes;

  public InventoryUpdateEvent(ArrayList<ArrayList<CardType>> cardTypes, int lobbyId){
    this.cardTypes = cardTypes;
    this.lobbyId = lobbyId;
  }

  public ArrayList<ArrayList<CardType>> getCardTypes() {
    return cardTypes;
  }
}
