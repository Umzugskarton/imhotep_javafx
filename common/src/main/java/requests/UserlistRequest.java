package requests;

public class UserlistRequest implements IRequest {

  private String request = "userList";

  public String getType() {
    return this.request;
  }

}

