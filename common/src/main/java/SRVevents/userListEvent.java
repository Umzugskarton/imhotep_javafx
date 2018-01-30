package SRVevents;

import java.util.ArrayList;

public class userListEvent implements Event {

  private String event = "userlist";
  private ArrayList<String> userList;

  public void setUserList(ArrayList<String> userList) {
    this.userList = userList;
  }

  public ArrayList<String> getUserList() {
    return this.userList;
  }
}
