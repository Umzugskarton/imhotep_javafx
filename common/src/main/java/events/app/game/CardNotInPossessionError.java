package events.app.game;

import requests.GameMoves.CardType.Type;

public class CardNotInPossessionError extends GameEvent {

  String card;

  public CardNotInPossessionError(Type card) {
  }
}
