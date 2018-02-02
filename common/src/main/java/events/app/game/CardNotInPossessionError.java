package events.app.game;

import requests.gamemoves.CardType;

public class CardNotInPossessionError extends GameEvent {

  String card;

  public CardNotInPossessionError(CardType card) {
  }
}
