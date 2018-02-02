package game.board.cards;

import requests.gamemoves.CardType.Type;
import java.util.EnumMap;

public class OrnamentCard extends Card {

  private static EnumMap<Type, Integer> typeMap;

  static {
    typeMap = new EnumMap<>(Type.class);
    typeMap.put(Type.PYRAMID, 0);
    typeMap.put(Type.TEMPLE, 1);
    typeMap.put(Type.BURIALCHAMBER, 2);
    typeMap.put(Type.OBELISK, 3);
  }

  /**
   * Diese Karte gibt dem besitzenden Spieler Bonuspunkte abhängig von ihrem Typ, also ob sie
   * zur Pyramide, dem Tempel, der Grabkammer oder dem Obelisken gehört. Sie wird am Ende des
   * Spiels gültig.
   *
   * @param type Art der Verzierung; Pyramide, Tempel, Burialchamber oder Obelisk
   */
  public OrnamentCard(Type type) {
    this.setType(type);
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
