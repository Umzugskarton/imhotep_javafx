package game.board;

import java.util.List;

/**
 * Interface für StoneSites. Die hier deklarierten Methoden müssen von jeder StoneSite implementiert werden.
 */
public interface IStoneSite {

  /**
   * Gibt ein Array mit den Punkten des Spieler zurück. Die Punkte für den ersten Spieler sind
   * an Index 0, die für den zweiten an Index 1 usw.
   *
   * @return Punkte der Spieler
   */
  int[] getPoints();

  /**
   * @param stones Die Steine, die zur StoneSite hinzugefügt werden sollen.
   */
  void addStones(Stone[] stones);

  /**
   * Gibt eine ArrayList aller vorhandenen Steine auf der StoneSite zurück.
   * @return alle Steine auf der StoneSite
   */
  List<Stone> getStones();
}
