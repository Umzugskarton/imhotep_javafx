package CLTrequests;



public class changeColorRequest implements Request {

  private String request = "changeColor";

  public changeColorRequest() {
  }

  public String getType() {
    return this.request;
  }

}
