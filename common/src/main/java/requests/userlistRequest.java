package requests;

public class userlistRequest implements IRequest {

  private String request = "userList";

  public String getType() {
    return this.request;
  }

}
