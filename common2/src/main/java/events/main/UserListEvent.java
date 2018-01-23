package events.main;

import java.util.ArrayList;

public class UserListEvent extends MainEvent {
    private String event = "userlist";
    private ArrayList<String> userList;

    public UserListEvent() {
    }

    public void setUserList(ArrayList<String> userList) {
        this.userList = userList;
    }

    public ArrayList<String> getUserList() {
        return this.userList;
    }
}
