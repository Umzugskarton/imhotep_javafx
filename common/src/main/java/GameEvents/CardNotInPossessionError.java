package GameEvents;

import SRVevents.Event;

public class CardNotInPossessionError implements Event {
  String type="CardNotInPossessionError";
  String card;
  public CardNotInPossessionError(String card){

  }
}
