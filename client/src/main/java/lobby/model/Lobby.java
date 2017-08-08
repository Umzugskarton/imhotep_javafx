package lobby.model;

import javafx.collections.ObservableList;
import org.json.simple.JSONArray;

import java.util.ArrayList;

public class Lobby {
    private int id;
    private String name;
    private int size;
    private boolean hasPW;
    private int usercount;
    private String belegung;
    private ObservableList<String> users;

    public Lobby(int id, int size, String name, boolean hasPW, int usercount){
        this.id=id;
        this.hasPW=hasPW;
        this.size=size;
        this.name=name;
        this.usercount=usercount;
        this.belegung= (usercount + " / " + size);
    }

    public int getId(){return this.id;}
    public String getName(){return this.name;}
    public int getSize(){return this.size;}
    public boolean hasPW(){return this.hasPW;}
    public int getUsercount(){return this.usercount;}
    public String getBelegung(){return this.belegung;}
    public void setBelegung(int usercount){
        this.belegung= (usercount + " / " + size);
    }

    public void updateUsers(JSONArray users){
        this.users.clear();
        for (Object user: users){
            this.users.add((String) user);
        }
    }

    public ObservableList getUsers(){
        return this.users;
    }
}
