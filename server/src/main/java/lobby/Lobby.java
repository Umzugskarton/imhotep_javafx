package lobby;

import SRVevents.changeColorEvent;
import SRVevents.joinEvent;
import SRVevents.setReadyEvent;
import commonLobby.LobbyUser;
import game.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import socket.ClientListener;
import user.User;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


public class Lobby {
    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    String name;
    private int LobbyID;
    private User[] lobby;
    private boolean[] ready;
    private String password;
    private boolean show;
    private int size;
    boolean vacancy = true;
    private ArrayList<Integer> userColor;
    private ArrayList<String> colors = new ArrayList<>();
    private Game game;

    public Lobby(int size, User host, String name) {
        this.ready = new boolean[size];
        this.lobby = new User[size];
        this.userColor = new ArrayList<>();
        this.userColor.add(0);
        this.lobby[0] = host;
        this.password = null;
        this.name = name;
        this.size = size;
        this.show = true;
        generateColors();
    }

    public boolean isHost(User user) {
        return user == lobby[0];
    }

    public void startGame(ClientListener cl) {
        this.game = new Game(this, cl);
    }

    public Game getGame() {
        return game;
    }

    public boolean isShow() {
        return show;
    }

    public String getHostName() {
        return this.lobby[0].getUsername();
    }

    public void setPassword(String psw) {
        this.password = psw;
    }

    public String getName() {
        return this.name;
    }

    public joinEvent joinPW(User user, String password) {
        if (password.equals(this.password)) {
            return join(user);
        }

        return new joinEvent("Das Passwort ist falsch.", false);
    }

    public joinEvent join(User user) {
        if (vacancy) {
            for (int i = 0; i < lobby.length; i++) {
                if (lobby[i] == null) {
                    lobby[i] = user;
                    int c = i;
                    while (userColor.contains(c)) {
                        c++;
                    }
                    userColor.add(c);
                    return new joinEvent("Erfolgreich beigetreten!", true);
                }
            }
        }
        this.vacancy = false;
        return new joinEvent("Die Lobby ist voll.", false);
    }

    public boolean[] getReady() {
        return this.ready;
    }


    public boolean hasPW() {
        return this.password != null;
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
        this.LobbyID = id;
    }

    public int getLobbyID() {
        return this.LobbyID;
    }

    public User[] getUsers() {
        return this.lobby;
    }

    public String[] getUsersStringArray() {
        String[] j = new String[this.lobby.length];
        int i = 0;
        for (User user : this.lobby) {
            if (user != null) {
                j[i] = user.getUsername();
                i++;
            }
        }
        return j;
    }

    public setReadyEvent setReady(User user) {
        int userid = Arrays.asList(lobby).indexOf(user);
        ready[userid] = !ready[userid];
        return new setReadyEvent(ready);
    }

    public changeColorEvent replaceColor(User user) {
        int userid = Arrays.asList(lobby).indexOf(user);
        int newcolor = userColor.get(userid);
        do {
            newcolor = (newcolor + 1) % 10;
        } while (userColor.contains(newcolor));
        userColor.set(userid, newcolor);
        return new changeColorEvent(userid, colors.get(newcolor));
    }

    public ArrayList<LobbyUser> getLobbyUserArrayList() {
        ArrayList<LobbyUser> temp = new ArrayList<>();
        int i = 0;

        for (User user : this.lobby) {
            if (user != null) {
                temp.add(new LobbyUser(user.getUsername(), colors.get(userColor.get(i)), ready[i]));
                i++;
            }
        }
        return temp;
    }

    public int getUserCount() {
        int users = 0;
        for (int i = 0; i < lobby.length; i++) {
            if (lobby[i] != null) {
                users++;
            }
        }
        return users;
    }

    public void generateColors() {
        float interval = 360 / 10;
        for (float x = 0; x < 360; x += interval) {
            Color c = Color.getHSBColor(x / 360, 1, 1);
            String hex = String.format("#%02x%02x%02x", (c.getRed() + 255) / 2, (c.getGreen() + 255) / 2, (c.getBlue() + 255) / 2);
            this.colors.add(hex);
        }
    }

    public void show(boolean show) {
        this.show = show;
    }

    public boolean isVisible() {
        return show;
    }

    public void leave(User user) {
        for (int i = 0; i < lobby.length; i++) {
            if (lobby[i] == user) {
                lobby[i] = null;
                break;
            }
        }

        log.info("[Lobby " + this.getLobbyID() + "] " + user.getUsername() + " hat die Lobby verlassen.");

        this.vacancy = true;
    }
}
