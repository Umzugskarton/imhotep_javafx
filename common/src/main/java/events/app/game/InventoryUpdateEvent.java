package events.app.game;

import requests.gamemoves.CardType;

import java.util.ArrayList;

public class InventoryUpdateEvent extends GameEvent{
  private ArrayList<CardType[]> cardTypes = new ArrayList<>();

  public ArrayList<CardType[]> getCardTypes() {
    return cardTypes;
  }

  public void setCardTypes(ArrayList<CardType[]> cardTypes) {
    this.cardTypes = cardTypes;
  }
}
