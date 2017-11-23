package CLTrequests;

import java.util.Date;

public class changeCredentialRequest implements Request {

  private String request = "changeCredential";
  private Date date;
  private String credential;
  private String username;
  private Integer type;

  public changeCredentialRequest() {
  }

  public changeCredentialRequest(String credential, Integer type) {
    this.credential = credential;
    this.type = type;
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

  public Integer getType() {
    return this.type;
  }

}


