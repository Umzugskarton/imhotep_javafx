package lobby.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;

import java.util.Iterator;


public class Lobby {
    private int LobbyID;
    private String name;
    private String host;
    private int size;
    private boolean hasPW;
    private int usercount;
    private String belegung;
    private ObservableList<LobbyUser> users;
    private String[] colors;
    private boolean[] ready;


    public Lobby(int id, int size, String name, boolean hasPW, int usercount){
        this.LobbyID=id;
        this.hasPW=hasPW;
        this.size=size;
        this.name=name;
        this.users=  FXCollections.observableArrayList();
        this.ready = new boolean[size];
        this.colors= new String[size];
        for (int s =0 ; s<= size-1; s++ ){
            colors[s]="#fff";
        }
        this.usercount=usercount;
        this.belegung= (usercount + " / " + size);
    }


    public void setHost(String host){
        this.host = host;
    }

    public void setLobbyID(int id){
        this.LobbyID= id;
    }

    public void setUsers(JSONArray newUsers, JSONArray ready, JSONArray color){
        users.clear();
        LobbyUser newUser;
        Iterator i1 = newUsers.iterator();
        Iterator i2 = ready.iterator();
        Iterator i3 = color.iterator();
        while(i1.hasNext() && i2.hasNext() && i3.hasNext()) {
            newUser = new LobbyUser((String)i1.next(),(String)i3.next(),(boolean)i2.next());
            users.add(newUser);
        }
    }

    public int getId(){return this.LobbyID;}
    public String getName(){return this.name;}
    public int getSize(){return this.size;}
    public boolean hasPW(){return this.hasPW;}
    public int getUsercount(){return this.usercount;}
    public String getBelegung(){return this.belegung;}
    public void setBelegung(int usercount){
        this.belegung= (usercount + " / " + size);
    }

    public void  setColors(JSONArray newColors){
        int i = 0;
        for (Object color: newColors ){
            this.colors[i] = (String)color;
            i++;
        }
    }


    public void  setReady(JSONArray ready){
        int i = 0;
        for (Object r: ready){
            this.ready[i] = (boolean)r;
            i++;
        }
    }

    public ObservableList<LobbyUser> getUsers(){
        return this.users;
    }
}
