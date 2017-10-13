package SRVevents;

import java.util.Date;

public class userNotFoundError implements voidEvent{

        private Date date;
        private String msg;

        public userNotFoundError(){
            this.date = new Date();
        }

        public void setMsg(String msg){
            this.msg=msg;
        }
        public String getMsg() {
            return this.msg;
        }

        public Date getDate() {
            return this.date;
        }


}
