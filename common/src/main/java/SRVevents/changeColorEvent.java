package SRVevents;

import commonLobby.CLTLobby;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Slothan/Dennis Lindt on 09.11.2017.
 */
public class changeColorEvent implements Event {
    private String event = "changeColor";
    private Date date;
    private String msg;
    private ArrayList<String> colors;
    private CLTLobby lobby;

    public changeColorEvent() {

    }

    public changeColorEvent(ArrayList<String> colors) {
        this.colors = colors;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public Date getDate() {
        return this.date;
    }

    public ArrayList<String> getColors() { return this.colors; }

}
