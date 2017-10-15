package commonLobby;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Iterator;


public class CLTLobby {
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


    public CLTLobby(int id, int size, String name, boolean hasPW, int usercount, ArrayList<LobbyUser> lobbyUsers){
        this.LobbyID=id;
        this.hasPW=hasPW;
        this.size=size;
        this.name=name;
        this.users=  FXCollections.observableArrayList();
        users.addAll(lobbyUsers);
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

    public void setUsers(ArrayList<LobbyUser> newUsers, boolean[] ready, String[] colors){
        users.clear();
        users.addAll(newUsers);
        this.colors= colors;
        this.ready = ready;
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
