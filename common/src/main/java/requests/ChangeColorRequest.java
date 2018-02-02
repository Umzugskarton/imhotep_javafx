package requests;



public class ChangeColorRequest extends Request {

  private String request = "changeColor";

  public ChangeColorRequest(){}
  public ChangeColorRequest(int lobbyId) {
    super(lobbyId);
  }

  public String getType() {
    return this.request;
  }

}
