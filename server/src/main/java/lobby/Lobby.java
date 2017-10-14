package lobby;

import SRVevents.joinEvent;
import org.json.simple.JSONArray;
import user.User;

public class Lobby {
    String name;
    private int LobbyID;
    private User[] lobby;
    private boolean[] ready;
    private String password;
    private int size;
    boolean vacancy = true;
    String[] colors;

    public Lobby(int size, User host, String name){
        this.ready= new boolean[size];
        this.colors= new String[size];
        for (int s =0 ; s<= size-1; s++ ){
            colors[s]="#0000ff";
        }
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

    public joinEvent joinPW(User user, String password){
        if (password.equals(this.password)) {
           return join(user);
        }

            return new joinEvent("Das Passwort ist falsch.", false);
    }

    public joinEvent join(User user){
        if (vacancy) {
            for (int i = 0; i < lobby.length; i++) {
                if (lobby[i] == null) {
                    lobby[i] = user;
                    return new joinEvent("Erfolgreich beigetreten!", true);
                }
            }
        }
        this.vacancy = false;
        return new joinEvent("Die Lobby ist voll.", false);
    }

    public boolean[] getReady(){
        return this.ready;
    }

    public JSONArray getReadyJSONArray(){
        JSONArray j = new JSONArray();
        for (boolean r : this.ready){
            j.add(r);
        }
        return j;
    }

    public boolean hasPW(){
        return this.password!=null;
    }

    public int getSize(){
        return this.size;
    }

    public String[] getColors(){
        return this.colors;
    }

    public void  setColors(String[] newColors){
        this.colors = newColors;
    }

    public void setLobbyID(int id){
        this.LobbyID = id;
    }

    public int getLobbyID(){
        return this.LobbyID;
    }

    public User[] getUsers(){
        return this.lobby;
    }

    public String[] getUsersStringArray(){
        String[] j = new String[this.lobby.length];
        int i = 0;
        for(User user : this.lobby) {
            if (user!=null) {
                j[i] = user.getUsername();
                i++;
            }
        }
        return j;
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
