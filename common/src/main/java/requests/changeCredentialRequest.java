package requests;

public class changeCredentialRequest implements IRequest {

  private String request = "changeCredential";
  private String credential;
  private String username;
  private Integer crednr;

  public changeCredentialRequest(String credential, Integer crednr) {
    this.credential = credential;
    this.crednr = crednr;
  }

  public String getRequest() {
    return this.request;
  }

  public String getCredential() {
    return this.credential;
  }

  public String getUsername() {
    return this.username;
  }

  public String getType() {
    return this.request;
  }

  public Integer getCrednr() {
    return crednr;
  }

}


