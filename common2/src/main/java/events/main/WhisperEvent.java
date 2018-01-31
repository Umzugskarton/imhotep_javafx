package events.main;

public class WhisperEvent extends MainEvent {
    private String event = "whisper";
    private String from;
    private String to;

    public WhisperEvent() {
    }

    public void setTo(String username) {
        this.to = username;
    }

    public void setFrom(String username) {
        this.from = username;
    }

    public String getTo() {
        return this.to;
    }

    public String getFrom() {
        return this.from;
    }
}
