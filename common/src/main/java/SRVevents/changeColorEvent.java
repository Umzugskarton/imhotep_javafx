package SRVevents;

public class changeColorEvent implements Event {

  private String event = "changeColor";
  private int id;
  private String color;

  public changeColorEvent(int id, String color) {
    this.color = color;
    this.id = id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public String getColor() {
    return this.color;
  }

}
