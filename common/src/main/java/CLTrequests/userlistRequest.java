package CLTrequests;

public class userlistRequest implements IRequest {

  private String request = "userlist";

  public String getType() {
    return this.request;
  }

}
