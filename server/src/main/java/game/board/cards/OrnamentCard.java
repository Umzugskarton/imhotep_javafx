package game.board.cards;

import java.util.EnumMap;
import requests.gamemoves.CardType;

public class OrnamentCard extends Card {

  private static EnumMap<CardType, Integer> typeMap;

  static {
    typeMap = new EnumMap<>(CardType.class);
    typeMap.put(CardType.PYRAMID, 0);
    typeMap.put(CardType.TEMPLE, 1);
    typeMap.put(CardType.BURIALCHAMBER, 2);
    typeMap.put(CardType.OBELISK, 3);
  }

  /**
   * Diese Karte gibt dem besitzenden Spieler Bonuspunkte abhängig von ihrem Typ, also ob sie
   * zur Pyramide, dem Tempel, der Grabkammer oder dem Obelisken gehört. Sie wird am Ende des
   * Spiels gültig.
   *
   * @param cardType Art der Verzierung; Pyramide, Tempel, Burialchamber oder Obelisk
   */
  public OrnamentCard(CardType cardType) {
    this.setType(cardType);
  }

  /**
   * Berechnet die Punkte, die diese Karte dem Spieler bringt: Für alle drei Steine, die auf der
   * jeweiligen StoneSite vorhanden sind, gibt es einen Punkt.
   *
   * @param stones die Anzahl der Steine der jeweiligen StoneSites.
   * @return Punkte, die der Spieler durch die Karte erhält
   */
  public int calc(int[] stones) {
    return stones[typeMap.get(this.getType())] / 3;
  }
}
