package events.main;

import javafx.scene.paint.Color;

public class ChatInfoEvent extends MainEvent {
    private String event = "chatInfo";
    private Color textColor = null;

    public ChatInfoEvent() {
    }

    public ChatInfoEvent(String message, Color textColor) {
        this.setMsg(message);
        this.textColor = textColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }
}
