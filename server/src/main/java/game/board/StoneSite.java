package game.board;

/**
 * Repräsentiert eine StoneSite.
 *
 * Diese Klasse ist nötig, um klar zwischen den meisten StoneSites und
 * dem Markt zu unterscheiden, der in einigen Punkten unterschiedlich funktioniert.
 */
public abstract class StoneSite extends Site implements IStoneSite {

  StoneSite(int playerCount) {
    super(playerCount);
  }
}
