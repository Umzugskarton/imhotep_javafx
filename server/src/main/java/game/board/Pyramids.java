package game.board;

/**
 * Repräsentiert eine Pyramide.
 */
public class Pyramids extends StoneSite {

    private int[] positionValues = {2, 1, 3, 2, 4, 3, 2, 1, 3, 2, 3, 1, 3, 4};
    private static final int STANDARD_VALUE = 1;
    private int currentTurnStones = 0;

    /**
     * Gibt Punktewerte für die verschiedenen Felder zurück.
     *
     * @return Punktewerte für die ersten Felder
     */
    public int[] getPositionValues() {
        return positionValues;
    }

    /**
     * Punktewert für die Felder nach den definierten.
     *
     * @return Standard-Punktewert
     */
    public int getStandardValue() {
        return STANDARD_VALUE;
    }

    /**
     * Erstellt eine neue Pyramide.
     *
     * @param playerCount die Anzahl der Spieler im Spiel
     */
    public Pyramids(int playerCount) {
        super(playerCount);
    }

    @Override
    public int[] getPoints() {
        int[] points = new int[playerCount];
        int i = 0;
        for (Stone s : stones) {
            if (i < positionValues.length) {
                points[s.getPlayer().getId()] += positionValues[i++];
            } else {
                points[s.getPlayer().getId()] += STANDARD_VALUE;
            }
        }
        return points;
    }

    /**
     * Gibt die Punkte zurück, die im Verlauf der aktuellen Runde gemacht wurden.
     * Bereitet die Pyramide auf die nächste Runde vor.
     *
     * @return Punkte für die aktuelle Runde
     */
    public int[] getPointsAndFinishTurn() {
        int[] points = new int[playerCount];
        for (int i = stones.size() - currentTurnStones; i < stones.size(); i++) {
            if (i < positionValues.length) {
                points[stones.get(i).getPlayer().getId()] += positionValues[i++];
            } else {
                points[stones.get(i).getPlayer().getId()] += STANDARD_VALUE;
            }
        }
        currentTurnStones = 0;
        return points;
    }

    @Override
    public void addStones(Stone[] stones) {
        for (Stone stone : stones) {
            if (stone != null) {
                currentTurnStones++;
                this.stones.add(stone);
            }
        }
    }
}
