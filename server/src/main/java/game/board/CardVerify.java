package game.board;


import GameMoves.Move;
import GameMoves.actionCardMove;

import java.util.HashMap;

public class CardVerify {
  static HashMap<Integer, String[]> Dict = new HashMap<>();

  static {
    String[] a = {"fillupStorageMove", "voyageToShipMove"};
    Dict.put(1, a);
  }


  public boolean validate(actionCardMove move){
    if (move.getMoves().size() > 2){
      return false;
    }

    String[] val =  Dict.get(move.getCardID());
    int vRate =0;
    for (Move m: move.getMoves()){
      for (String s : val){
        if (s.equals(m.getType())){
          vRate++;
          break;
        }
      }
    }

    if (vRate  == val.length){
      return true;
    }
    else {
      return true;
    }
  }
}
