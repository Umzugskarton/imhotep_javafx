package GameObjects;


public class Boat {
    private Stone[] spaces;
    private boolean available;

    public Boat(int spaces){
        this.spaces = new Stone[spaces];
    }

    public boolean isAvailable() {
        return available;
    }

    public Stone[] getSpaces() {
        return spaces;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setSpaces(Stone[] spaces) {
        this.spaces = spaces;
    }

    public void setSpaceNr(int nr, Stone stone){
        this.spaces[nr] = stone;
    }
}
