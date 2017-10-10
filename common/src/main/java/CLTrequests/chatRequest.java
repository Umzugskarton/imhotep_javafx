package CLTrequests;


import java.util.Date;

public class chatRequest implements Request {
    private String command = "chat";
    private Date date;
    private String msg;

    public chatRequest(){
    }

    public chatRequest(String msg){
        this.msg = msg;
        this.date = new Date();
    }

    public String getMsg(){
        return this.msg;
    }

    public String getCommand(){
        return this.command;
    }

    public Date getDate(){
        return this.date;
    }
}
