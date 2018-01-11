package game.board;

public abstract class Card {

  private String name;
  private String text;

  String getName() {
    return name;
  }

  String getText() {
    return text;
  }

  void setText(String text) {
    this.text = text;
  }

  public void setName(String name) {
    this.name = name;
  }
}
