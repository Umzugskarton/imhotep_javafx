package CLTrequests;

import java.util.Date;

public interface Request {
    String command = null;

    Date getDate();

    String getRequest();
}
