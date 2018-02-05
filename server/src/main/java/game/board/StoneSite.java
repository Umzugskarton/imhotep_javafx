package game.board;

import java.util.ArrayList;
import java.util.List;

/**
 * Repräsentiert eine StoneSite.
 */
public abstract class StoneSite extends Site implements IStoneSite {

    ArrayList<Stone> stones = new ArrayList<>();

    StoneSite(int playerCount) {
        super(playerCount);
    }

    @Override
    public void addStones(Stone[] stones) {
        for (Stone stone : stones) {
            if (stone != null) {
                this.stones.add(stone);
            }
        }
    }

    public void addStone(Stone stone) {
        if (stone != null) {
            stones.add(stone);
        }
    }

    @Override
    public List<Stone> getStones() {
        return stones;
    }

    @Override
    public boolean dockShip(Ship ship) {
        if (this.getDockedShip() != null) {
            return false;
        }
        this.setDockedShip(ship);
        addStones(ship.getStones());
        return true;
    }

    @Override
    public boolean isDocked() {
        return this.getDockedShip() != null;
    }
}