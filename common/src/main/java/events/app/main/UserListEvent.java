package events.app.main;

import events.Event;

import java.util.ArrayList;

public class UserListEvent extends Event {

  private ArrayList<String> userList;

  public void setUserList(ArrayList<String> userList) {
    this.userList = userList;
  }

  public ArrayList<String> getUserList() {
    return this.userList;
  }
}
