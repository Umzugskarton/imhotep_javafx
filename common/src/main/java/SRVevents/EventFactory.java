package SRVevents;

import GameEvents.*;

import java.util.HashMap;

public class EventFactory {

  private HashMap<String, Event> Dict = new HashMap<>();

  public EventFactory() {
    Dict.put("register", new registerEvent());
    Dict.put("login", new loginEvent());
    Dict.put("logout", new logoutEvent());
    Dict.put("userList", new userListEvent());
    Dict.put("whisper", new whisperEvent());
    Dict.put("chat", new chatEvent());
    Dict.put("chatInfo", new chatInfoEvent());
    Dict.put("lobbylist", new lobbylistEvent());
    Dict.put("lobbyInfo", new lobbyInfoEvent());
    Dict.put("join", new joinEvent());
    Dict.put("create", new createEvent());
    Dict.put("changeCredential", new changeCredentialEvent());
    Dict.put("changeColor", new changeColorEvent());
    Dict.put("setReady", new setReadyEvent());
    Dict.put("gameInfo", new GameInfoEvent());
    Dict.put("turn", new TurnEvent());
    Dict.put("leaveLobby", new leaveLobbyEvent());
    Dict.put("FillUpStorage", new FillUpStorageEvent());
    Dict.put("ShipLoaded", new ShipLoadedEvent());
    Dict.put("AlreadyAllocatedError", new AlreadyAllocatedError());
    Dict.put("ShipDocked", new ShipDockedEvent());
  }

  public Event getEvent(String command) {
    return Dict.get(command);
  }


}
