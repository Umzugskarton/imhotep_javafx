package requests.main;

public class ChangeCredentialRequest extends MainRequest {

  private String request = "changeCredential";
  private String credential;
  private String username;
  private Integer crednr;

  public ChangeCredentialRequest() {
  }

  public ChangeCredentialRequest(String credential, Integer crednr) {
    this.credential = credential;
    this.crednr= crednr;
  }

  public Integer getTypeOfC(){
    return crednr;
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

  public Integer getCrednr(){
    return crednr;
  }

}


