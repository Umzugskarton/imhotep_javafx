package events.app.game;

import java.util.ArrayList;
import requests.gamemoves.CardType;

public class InventoryUpdateEvent extends GameEvent {

  private ArrayList<CardType[]> cardTypes = new ArrayList<>();

  public ArrayList<CardType[]> getCardTypes() {
    return cardTypes;
  }

  public void setCardTypes(ArrayList<CardType[]> cardTypes) {
    this.cardTypes = cardTypes;
  }
}
