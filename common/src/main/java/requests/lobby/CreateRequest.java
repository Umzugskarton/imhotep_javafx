package requests.lobby;

import requests.IRequest;
import requests.RequestType;

public class CreateRequest implements IRequest {

    private RequestType request = RequestType.CREATE;
    private String name;
    private int size;
    private String pw;

    public CreateRequest(String name, int size, String password) {
        this.size = size;
        this.pw = password;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public RequestType getType() {
        return this.request;
    }

    public int getSize() {
        return this.size;
    }

    public String getPassword() {
        return this.pw;
    }

}
