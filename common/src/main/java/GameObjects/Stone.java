package GameObjects;


public class Stone {
    private int owner;
    private String HexColor;

    public int getOwner() {
        return owner;
    }

    public String getHexColor() {
        return HexColor;
    }

    public void setHexColor(String hexColor) {
        HexColor = hexColor;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
}
