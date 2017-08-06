package games.model;

public class Lobby {
    private int id;
    private String name;
    private int size;
    private boolean hasPW;
    private int usercount;
    private String belegung;

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
}
