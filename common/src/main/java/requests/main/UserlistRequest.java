package requests.main;

import requests.IRequest;
import requests.RequestType;

public class UserlistRequest implements IRequest {

    private RequestType request = RequestType.USERLIST;

    public RequestType getType() {
        return this.request;
    }

}

