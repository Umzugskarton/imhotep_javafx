package requests.profil;

import requests.IRequest;
import requests.RequestType;

public class ChangeCredentialRequest implements IRequest {

    private RequestType request = RequestType.CHANGE_CREDENTIAL;
    private String credential;
    private String username;
    private Integer crednr;

    public ChangeCredentialRequest(String credential, Integer crednr) {
        this.credential = credential;
        this.crednr = crednr;
    }

    public String getCredential() {
        return this.credential;
    }

    public String getUsername() {
        return this.username;
    }

    public RequestType getType() {
        return this.request;
    }

    public Integer getCrednr() {
        return crednr;
    }

}


