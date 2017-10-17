package lobby;

import SRVevents.joinEvent;
import commonLobby.LobbyUser;
import org.json.simple.JSONArray;
import user.User;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Lobby {
    String name;
    private int LobbyID;
    private User[] lobby;
    private boolean[] ready;
    private String password;
    private int size;
    boolean vacancy = true;
    private ArrayList<String> colors = new ArrayList<>();


    public Lobby(int size, User host, String name){
        this.ready= new boolean[size];
        this.lobby = new User[size];
        this.lobby[0] = host;
        this.password=null;
        this.name=name;
        this.size = size;
        generateColors();
    }

    public boolean isHost(User user){
        return user == lobby[0];
    }

    public String getHostName(){
        return this.lobby[0].getUsername();
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



    public boolean hasPW(){
        return this.password!=null;
    }

    public int getSize(){
        return this.size;
    }

    public ArrayList<String> getColors(){
        return this.colors;
    }

    public void  setColors(ArrayList newColors){
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


    public ArrayList<LobbyUser> getLobbyUserArrayList(){
        ArrayList<LobbyUser> temp = new ArrayList<>();
        int i =0;


        for (User user : this.lobby) {
            if (user == null) {
                continue;
            }else {
                temp.add(new LobbyUser(user.getUsername(), colors.get(i), ready[i]));
                i++;
            }
        }
        return temp;
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

    public void generateColors() {
        int anzahl = 256 /this.size;
        for (int i = 0; i < this.size; i++) {
            int  area = (anzahl * (i+1)) % 255;

            int red = ThreadLocalRandom.current().nextInt(area - anzahl, area);
            int green = ThreadLocalRandom.current().nextInt(area - anzahl, area);
            int blue = ThreadLocalRandom.current().nextInt(area - anzahl, area);
            red = (red + 255) / 2;
            green = (green + 255) / 2;
            blue = (blue + 255) / 2;

            String hex = String.format("#%02x%02x%02x", red, green, blue);
            this.colors.add(hex);
        }
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
