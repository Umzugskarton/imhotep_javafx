package events.app.profil;

import events.Event;

public class ChangeProfilDataEvent extends Event {

  private String username;
  private String msg;
  private boolean validate;
  private String credential;
  private Integer type;

  public void setUsername(String username) {
    this.username = username;
  }

  public void setMsg(String msg) {
    this.msg = msg;
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

  public String getMsg() {
    return this.msg;
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