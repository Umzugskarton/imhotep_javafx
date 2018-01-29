package GameEvents;

import GameMoves.CardType.Type;
import SRVevents.Event;

public class CardNotInPossessionError implements Event {
  String type="CardNotInPossessionError";
  String card;
  public CardNotInPossessionError(Type card){

  }
}
