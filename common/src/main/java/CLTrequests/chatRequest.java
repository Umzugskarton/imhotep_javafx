package CLTrequests;


import java.util.Date;

public class chatRequest implements Request {
    private String request = "chat";
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

    public String getRequest(){
        return this.request;
    }

    public Date getDate(){
        return this.date;
    }
}
