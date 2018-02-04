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

  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }
    if (this == o) {
      return true;
    }
    if (!(o instanceof Card)) {
      return false;
    }
    return ((Card) o).getType() == this.getType();
  }
}
