package game.board.cards;

import requests.gamemoves.CardType.Type;

public abstract class Card {

  private Type type;

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }
}
