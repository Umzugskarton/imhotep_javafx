package events.main;

import events.Event;

public class MainEvent extends Event {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
