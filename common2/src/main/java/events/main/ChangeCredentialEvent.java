package events.main;



public class ChangeCredentialEvent extends MainEvent {

  private String event = "changeCredential";
  private String username;
  private boolean validate;
  private String credential;
  private Integer type;

  public ChangeCredentialEvent() {

  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setSuccess(boolean validate) {
    this.validate = validate;
  }

  public void setCredential(String credential) {
    this.credential = credential;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getUsername() {
    return this.username;
  }

  public boolean getSuccess() {
    return this.validate;
  }

  public String getCredential() {
    return this.credential;
  }

  public Integer getType() {
    return this.type;
  }
}
