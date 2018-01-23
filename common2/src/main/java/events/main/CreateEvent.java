package events.main;

public class CreateEvent extends MainEvent {
    private String event = "create";
    private int id;
    private boolean success;


    public CreateEvent() {
    }

    public CreateEvent(boolean success, int lobbyID, String msg) {
        this.success = success;
        this.id = lobbyID;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public int getId() {
        return this.id;
    }

    public String getEvent() {
        return event;
    }
}
