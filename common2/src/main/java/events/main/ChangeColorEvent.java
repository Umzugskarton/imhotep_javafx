package events.main;

public class ChangeColorEvent extends MainEvent {
    private String event = "changeColor";
    private int id;
    private String color;

    public ChangeColorEvent() {
    }

    public ChangeColorEvent(int id, String color) {
        this.color = color;
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getColor() { return this.color; }

}
