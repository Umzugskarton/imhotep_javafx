package requests.game;

import java.util.ArrayList;
import java.util.List;

public class ActionCardMove extends GameRequest{
  private ArrayList<GameRequest> moves;

  public ActionCardMove(int cardID) {
    moves = new ArrayList<>();
  }

  public void addMove(GameRequest move){
    moves.add(move);
  }

  public List getMoves() {
    return moves;
  }
}
