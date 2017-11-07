package SRVevents;

import java.util.ArrayList;
import java.util.Date;


public class userListEvent implements Event {
    private String event = "userlist";
    private Date date;
    private ArrayList<String> userList;

    public userListEvent() {
        this.date = new Date();
    }

    public void setUserList(ArrayList<String> userList) {
        this.userList = userList;
    }

    public ArrayList<String> getUserList() {
        return this.userList;
    }

    public Date getDate() {
        return this.date;
    }


}
