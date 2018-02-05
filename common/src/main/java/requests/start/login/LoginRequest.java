package requests.start.login;

import requests.IRequest;
import requests.RequestType;

public class LoginRequest implements IRequest {

    private RequestType request = RequestType.LOGIN;
    private String username;
    private String pw;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.pw = password;
    }

    @Override
    public RequestType getType() {
        return this.request;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.pw;
    }


}
