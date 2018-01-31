package requests.game;

import java.util.ArrayList;

public class ActionCardMove extends GameRequest{
  private String move = "actionCard";
  private int cardID;
  private ArrayList<GameRequest> moves;

  public ActionCardMove(int cardID) {

    this.cardID = cardID;
    moves = new ArrayList<>();
  }

  public void addMove(GameRequest move){
    moves.add(move);
  }

  public ArrayList<GameRequest> getMoves() {
    return moves;
  }
}
