package game.board;

/**
 * Repräsentiert eine Grabkammer.
 */
public class BurialChamber extends StoneSite {

    public BurialChamber(int playerCount) {
        super(playerCount);
    }

    /**
     * Gibt ein Array mit den Punkten zurück, die die durch die BurialChamber bekommen.
     *
     * @return Punkte für die jeweiligen Spieler an ihrem entsprechendem Index
     */
    @Override
    public int[] getPoints() {
        boolean[] checked = new boolean[stones.size()];
        int[] points = new int[playerCount];
        for (int i = 0; i < stones.size(); i++) {
            int playerId = stones.get(i).getPlayer().getId();
            int size = getFieldSize(i, playerId, checked);
            if (size == 1) {
                points[playerId] += 1;
            } else if (size == 2) {
                points[playerId] += 3;
            } else if (size == 3) {
                points[playerId] += 6;
            } else if (size == 4) {
                points[playerId] += 10;
            } else if (size == 5) {
                points[playerId] += 15;
            } else if (size > 5) {
                points[playerId] += 15 + (size - 5) * 2;
            }
        }
        return points;
    }

    private int getFieldSize(int position, int playerId, boolean[] checked) {
        if (position >= stones.size()
                || stones.get(position).getPlayer().getId() != playerId
                || checked[position]) {
            return 0;
        }
        checked[position] = true;
        int number = 1;
        if (position % 3 == 0 || position % 3 == 1) {
            number += getFieldSize(position + 1, playerId, checked);
        }
        number += getFieldSize(position + 3, playerId, checked);
        return number;
    }
}
