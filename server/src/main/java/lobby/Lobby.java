package lobby;

import json.ServerCommands;
import org.json.simple.JSONObject;
import user.User;

public class Lobby {
    String name;
    private int LobbyID;
    private User[] lobby;
    private String password;
    private int size;
    boolean vacancy = true;

    public Lobby(int size, User host, String name){
        this.lobby = new User[size];
        this.lobby[0] = host;
        this.password=null;
        this.name=name;
        this.size = size;
    }

    public void setPassword(String psw){
        this.password=psw;
    }

    public String getName(){
        return this.name;
    }

    public JSONObject joinPW(User user, String password){
        JSONObject response;
        if (password.equals(this.password)) {
           return join(user);
        }
            response = ServerCommands.joinCommand("Das Passwort ist falsch.", false);
            return response;
    }

    public JSONObject join(User user){
        JSONObject response;
        if (vacancy) {
            for (int i = 0; i < lobby.length; i++) {
                if (lobby[i] == null) {
                    lobby[i] = user;
                    response = ServerCommands.joinCommand("Erfolgreich beigetreten!", true);
                    return response;
                }
            }
        }
        this.vacancy = false;
        response = ServerCommands.joinCommand("Die Lobby ist voll.", false);
        return response;
    }

    public boolean hasPW(){
        return this.password!=null;
    }

    public int getSize(){
        return this.size;
    }

    public int getUserCount(){
        int users=0;
        for (int i = 0; i < lobby.length; i++) {
            if (lobby[i] != null) {
                users++;
            }
        }
        return users;
    }

    public void leave(User user){
        for (int i = 0; i < lobby.length ; i++){
            if (lobby[i] == user){
                lobby[i]=null;
                break;
            }
        }
        this.vacancy = true;
    }

}
