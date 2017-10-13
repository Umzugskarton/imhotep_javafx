package SRVevents;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Date;


public class userListEvent implements voidEvent{
    private Date date;
    private ArrayList<String> userList;

    public userListEvent(){
      this.date= new Date();
    }

    public void setUserList(ArrayList<String> userList){
        this.userList = userList;
    }

    public ArrayList<String> getUserList(){
        return this.userList;
    }

    public Date getDate() {
        return this.date;
    }


}
