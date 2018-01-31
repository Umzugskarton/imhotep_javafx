package CLTrequests;

public class createRequest implements IRequest {

  private String request = "create";
  private String name;
  private int size;
  private String pw;

  public createRequest(String name, int size, String password) {
    this.size = size;
    this.pw = password;
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public String getType() {
    return this.request;
  }

  public int getSize() {
    return this.size;
  }

  public String getPassword() {
    return this.pw;
  }

}
