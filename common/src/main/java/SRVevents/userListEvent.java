package SRVevents;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.Date;


public class userListEvent implements voidEvent{
    private Date date;
    private JSONArray userList;

    public userListEvent(){
      this.date= new Date();
    }

    public JSONArray getUserList(){
        return this.userList;
    }

    public Date getDate() {
        return this.date;
    }


}
