package game.board.cards;

import requests.gamemoves.CardType;

public abstract class Card {

  private CardType cardType;

  public CardType getType() {
    return cardType;
  }

  public void setType(CardType cardType) {
    this.cardType = cardType;
  }
}
