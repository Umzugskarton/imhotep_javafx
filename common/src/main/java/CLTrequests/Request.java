package CLTrequests;

import java.util.Date;

/**
 * Created on 10.10.2017.
 */
public interface Request {
    String command = null;
    Date getDate();
    String getCommand();
}
