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
    private int id;
    private String color;

    public changeColorEvent() {
        date = new Date();
    }

    public changeColorEvent(int id,String color) {
        this.color = color;
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return this.date;
    }

    public String getColor() { return this.color; }

}
