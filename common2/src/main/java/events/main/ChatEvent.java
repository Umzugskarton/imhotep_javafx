package events.main;

public class ChatEvent extends MainEvent {

  private String event = "chat";
  private String user;
  private Integer lobbyId;

  public ChatEvent() {
    lobbyId = null;
  }

  public Integer getLobbyId() {
    return lobbyId;
  }

  public void setLobbyId(Integer lobbyId) {
    this.lobbyId = lobbyId;
  }

  public void setUser(String username) {
    this.user = username;
  }

  public String getUser() {
    return this.user;
  }


}
