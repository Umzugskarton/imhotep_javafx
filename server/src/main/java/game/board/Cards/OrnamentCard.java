package game.board.Cards;

import GameMoves.CardType.Type;
import java.util.EnumMap;

public class OrnamentCard extends Card {

  private static EnumMap<Type, Integer> typeMap;
  static {
    typeMap = new EnumMap<Type, Integer>(Type.class);
    typeMap.put(Type.PYRAMID, 0);
    typeMap.put(Type.TEMPLE, 1);
    typeMap.put(Type.BURIALCHAMBER, 2);
    typeMap.put(Type.OBELISK, 3);
  }

  public OrnamentCard(Type type) {
    this.setType(type);
  }

  public int calc(int[] stones) {
    return stones[typeMap.get(this.getType())] / 3;
  }
}
