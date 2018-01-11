package game.board;

import java.util.HashMap;

public class ToolCard extends Card {

  static final HashMap<String, String> description = new HashMap<>();

  static {
    description.put("lever",
        "Fahre 1 Boot zu einem Ort. Bestimme selbst die Reihenfolge beim Abladen der Steine.");
    description.put("sail", "Platziere 1 Stein auf 1 Boot UND fahre das Boot zu einem Ort.");
    description.put("hammer",
        "Hole dir 3 Steine aus dem Steinbruch in deinen Vorrat UND platziere 1 Stein auf 1 Boot.");
    description.put("chisel", "Platziere 2 Steine auf 1 Boot ODER je 1 Stein auf 2 Booten.");
  }

  public ToolCard(String name) {
    this.setName(name);
  }

  public String getText() {
    return description.get(getName());
  }
}
