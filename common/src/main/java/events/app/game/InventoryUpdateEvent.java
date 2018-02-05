package events.app.game;

import requests.gamemoves.CardType;

import java.util.ArrayList;

public class InventoryUpdateEvent extends GameEvent{
  private ArrayList<ArrayList<CardType>>  cardTypes;

  public InventoryUpdateEvent(ArrayList<ArrayList<CardType>> cardTypes, int lobbyId){
    this.cardTypes = cardTypes;
    setLobbyId(lobbyId);
  }

  public ArrayList<ArrayList<CardType>> getCardTypes() {
    return cardTypes;
  }
}
