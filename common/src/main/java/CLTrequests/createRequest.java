package CLTrequests;

import java.util.Date;

public class createRequest implements Request {

  private String request = "create";
  private Date date;
  private String name;
  private int size;
  private String pw;

  public createRequest() {

  }

  public createRequest(String name, int size, String password) {
    this.size = size;
    this.pw = password;
    this.name = name;
    this.date = new Date();
  }

  public String getName() {
    return this.name;
  }

  public String getRequest() {
    return this.request;
  }

  public int getSize() {
    return this.size;
  }

  public String getPassword() {
    return this.pw;
  }

  public Date getDate() {
    return this.date;
  }
}
