package requests;

import java.util.Date;

public interface IRequest {
    String request = null;

    Date getDate();

    String getType();

    void doIt();
}