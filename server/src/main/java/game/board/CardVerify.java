package game.board;


import java.util.HashMap;

public class CardVerify {
  static HashMap<Integer, String[]> Dict = new HashMap<>();

  static {
    String[] a = {"fillupStorageMove", "voyageToShipMove"};
    Dict.put(1, a);
    String[] b = {"Sotas", "assda"};
    Dict.put(2,b);
  }


  public void validate(actionCardMove move){

  }
}
