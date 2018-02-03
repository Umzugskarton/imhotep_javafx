package requests;

public class UserlistRequest implements IRequest {

  private RequestType request = RequestType.USERLIST;

  public RequestType getType() {
    return this.request;
  }

}

