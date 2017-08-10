package lobby.model;

import javafx.collections.ObservableList;
import org.json.simple.JSONArray;


public class Lobby {
    private int id;
    private String name;
    private String host;
    private int size;
    private boolean hasPW;
    private int usercount;
    private String belegung;
    private ObservableList<String> users;
    private String[] colors;
    private boolean[] ready;

    public Lobby(int id, int size, String name, boolean hasPW, int usercount){
        this.id=id;
        this.hasPW=hasPW;
        this.size=size;
        this.name=name;
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
        this.id= id;
    }

    public void setUsers(JSONArray newUsers){
        users.clear();
        for (Object user : newUsers){
            users.add((String) user);
        }
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

    public ObservableList getUsers(){
        return this.users;
    }
}
