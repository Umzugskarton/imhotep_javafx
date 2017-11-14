package CLTrequests;

import java.util.Date;

public class changeCredentialRequest implements Request {

  private String request = "changeCredential";
  private Date date;
  private String credential;
  private String username;
  private Integer crednr;

  public changeCredentialRequest() {
  }

  public changeCredentialRequest(String credential, Integer crednr) {
    this.credential = credential;
    this.crednr= crednr;
  }

  public String getRequest() {
    return this.request;
  }

  public Date getDate() {
    return this.date;
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

  public Integer getCrednr(){
    return crednr;
  }

}


