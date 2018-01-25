package SRVevents;


import java.io.Serializable;
import java.util.Date;

public interface Event extends Serializable{
    String event = null;
    Date date = null;
    String msg = null;
}
