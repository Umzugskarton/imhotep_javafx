package lobby;

import data.lobby.LobbyUser;
import data.user.User;
import events.app.lobby.changecolor.ChangeLobbyUserColorEvent;
import events.app.lobby.join.JoinLobbyEvent;
import events.app.lobby.leave.LeaveLobbyEvent;
import events.app.lobby.setready.SetReadyToPlayEvent;
import game.Game;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import socket.ClientListener;
import socket.Server;

public class Lobby {

  private final Logger log = LoggerFactory.getLogger(getClass().getName());

  private Server server;
  private String name;
  private int lobbyID;
  private User[] users;
  private boolean[] readyList;
  private String password;
  private boolean show = true;
  private int size;
  private boolean vacancy = true;
  private ArrayList<Integer> userColor = new ArrayList<>();
  private ArrayList<String> colors = new ArrayList<>();
  private Game game;

  public Lobby(int size, User host, String name, String password, Server server) {
    readyList = new boolean[size];
    this.users = new User[size];
    this.userColor.add(0);
    this.users[0] = host;
    this.name = name;
    this.size = size;
    this.password = password;
    this.server = server;
    generateColors();
  }

  public boolean isHost(User user) {
    return user == users[0];
  }

  private void swapHost() {
    for (int i = 0; i < users.length; i++) {
      if (users[i] != users[0]) {
        User temp = users[0];
        users[0] = users[i];
        users[i] = temp;
        break;
      }
    }
  }

  //Möglicherweise schaffen wir nicht mehr gezielten Host-Wechsel
  private void swapHost(User user) {
    if (user != null && user != users[0]) {
      User temp = users[0];
      users[0] = user;
      user = temp;
    }
  }

  public void startGame(ClientListener cl) {
    game = new Game(this, cl);
    this.show(false);
    Thread thread = new Thread(game);
    thread.start();
  }

  public Game getGame() {
    return game;
  }

  public String getHostName() {
    if (users[0] == null) {
      return "Kein User vorhanden";
    } else {
      return this.users[0].getUsername();
    }
  }

  public String getName() {
    return this.name;
  }

  public JoinLobbyEvent join(User user, String password) {
    if (!this.password.equals(password)) {
      return new JoinLobbyEvent("Das Passwort ist falsch.", false, lobbyID);
    }
    if (vacancy) {
      for (int i = 0; i < users.length; i++) {
        if (users[i] == null) {
          users[i] = user;
          int c = i;
          while (userColor.contains(c)) {
            c++;
          }
          userColor.add(c);
          return new JoinLobbyEvent("Erfolgreich beigetreten!", true, lobbyID);
        }
      }
      this.vacancy = false;
    }
    return new JoinLobbyEvent("Die Lobby ist voll.", false, lobbyID);
  }

  public boolean[] getReady() {
    return readyList;
  }

  public boolean hasPW() {
    return !this.password.isEmpty();
  }

  public int getSize() {
    return this.size;
  }

  public ArrayList<String> getColors() {
    ArrayList<String> currentColors = new ArrayList<>();
    for (Integer user : userColor) {
      currentColors.add(colors.get(user));
    }
    return currentColors;
  }

  public void setLobbyID(int id) {
    this.lobbyID = id;
  }

  public int getLobbyID() {
    return this.lobbyID;
  }

  public User[] getUsers() {
    return this.users;
  }

  public String[] getUsersStringArray() {
    String[] j = new String[this.users.length];
    int i = 0;
    for (User user : this.users) {
      if (user != null) {
        j[i] = user.getUsername();
        i++;
      }
    }
    return j;
  }

  public SetReadyToPlayEvent setReady(User user) {
    int userid = Arrays.asList(users).indexOf(user);
    readyList[userid] = !readyList[userid];
    return new SetReadyToPlayEvent(readyList, lobbyID);
  }

  public ChangeLobbyUserColorEvent replaceColor(User user) {
    int userid = Arrays.asList(users).indexOf(user);
    int newcolor = userColor.get(userid);
    do {
      newcolor = (newcolor + 1) % 10;
    } while (userColor.contains(newcolor));
    userColor.set(userid, newcolor);
    return new ChangeLobbyUserColorEvent(userid, colors.get(newcolor), lobbyID);
  }

  public ArrayList<LobbyUser> getLobbyUserArrayList() {
    ArrayList<LobbyUser> temp = new ArrayList<>();
    int i = 0;
    for (User user : this.users) {
      if (user != null) {
        temp.add(new LobbyUser(user, colors.get(userColor.get(i)), readyList[i]));
        i++;
      }
    }
    return temp;
  }

  public int getUserCount() {
    int users = 0;
    for (User user : this.users) {
      if (user != null) {
        users++;
      }
    }
    return users;
  }

  private void generateColors() {
    float interval = 360 / 10;
    for (float x = 0; x < 360; x += interval) {
      Color c = Color.getHSBColor(x / 360, 1, 1);
      String hex = String.format("#%02x%02x%02x", (c.getRed() + 255) / 2, (c.getGreen() + 255) / 2,
          (c.getBlue() + 255) / 2);
      this.colors.add(hex);
    }
  }

  public void show(boolean show) {
    this.show = show;
  }

  public boolean isVisible() {
    return show;
  }

  public LeaveLobbyEvent leave(User user) {
    if (user == users[0] && getUserCount() != 1) {
      swapHost();
    }
    for (int i = 0; i < users.length; i++) {
      if (users[i] == user) {
        users[i] = null;
        userColor.remove(i);
        break;
      }
    }
    log.info(
        "[Lobby " + this.getLobbyID() + "] " + user.getUsername() + " hat die Lobby verlassen.");
    Arrays.fill(readyList, false);
    if (getUserCount() == 0) {
      //Lobby wird unsichtbar gesetzt, wenn alle diese verlassen haben
      this.show = false;
      //Löschen der Lobby
      this.server.delLobby(this);
    }
    this.vacancy = true;
    return new LeaveLobbyEvent(true, lobbyID);
  }
}
