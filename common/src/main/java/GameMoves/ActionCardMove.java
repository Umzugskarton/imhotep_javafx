package GameMoves;


import java.util.ArrayList;
import java.util.Date;

public class ActionCardMove implements Move{
  private String move = "ActionCard";
  private int cardID;
  private Date date;
  private ArrayList<Move> moves;

  public ActionCardMove() {

  }

  public ActionCardMove(int cardID) {
    date = new Date();
    this.cardID = cardID;
    moves = new ArrayList<>();
  }

  public int getCardID() {
    return cardID;
  }

  public void addMove(Move move){
    moves.add(move);
  }
  @Override
  public String getType() {
    return move;
  }

  public ArrayList<Move> getMoves() {
    return moves;
  }

  @Override
  public Date getDate() {
    return date;
  }
}
