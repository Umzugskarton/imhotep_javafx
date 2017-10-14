package commonLobby;

public class LobbyUser {
    private String username;
    private String color;
    private boolean ready;

    public LobbyUser(String username , String color, boolean ready){
        this.username=username;
        this.color= color;
        this.ready=ready;
    }

    public boolean isReady(){
        return this.ready;
    }

    public String getUsername(){ return  this.username;}

    public String getColor(){return this.color;}
}
