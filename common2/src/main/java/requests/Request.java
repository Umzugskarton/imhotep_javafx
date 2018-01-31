package requests;

import java.io.Serializable;
import java.util.Date;

public class Request implements IRequest, Serializable {
    private final Date date;
    private final String refactoryClass = this.getClass().getName();

    public Request(){
        this.date = new Date();
    }

    public Date getDate(){
        return this.date;
    }

    public String getType(){
        return this.refactoryClass;
    }

    public void doIt(){
        System.out.print("Ich tuhe es!");
    }
}
