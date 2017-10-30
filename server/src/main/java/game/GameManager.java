package game;


import GameEvents.gameInfoEvent;
import GameObjects.Bauwerke.Pyramid;
import GameObjects.Boat;
import lobby.Lobby;
import socket.ClientListener;
import user.User;
import java.util.concurrent.ThreadLocalRandom;

public class GameManager {
    private int gameID;
    private Lobby lobby;
    private Boat[] cboats;
    private int round;
    private boolean[][] storages;
    private User[] order;
    private Pyramid pyramid;

    private ClientListener clientListener;

    public GameManager(Lobby lobby, ClientListener clientListener){
        this.lobby=lobby;
        this.gameID=this.lobby.getLobbyID();
        this.clientListener = clientListener;
        lobby.show(false);
        this.round = 1;
        this.cboats = new Boat[lobby.getSize()];
        order = new User[lobby.getSize()];
        storages = new boolean[lobby.getSize()-1][5];
        setGame();
        this.pyramid = new Pyramid();
        setStartCards();
        sendAllGameinfo();
    }

    public void resetCboats(){
        for (int i = 0 ; i <= lobby.getSize() -1 ; i++){
            this.cboats[i] = new Boat(ThreadLocalRandom.current().nextInt(1, 4));
        }
    }

    public void setGame(){
        int seq = ThreadLocalRandom.current().nextInt(0, this.lobby.getSize()-1);
        for (int i = 0; i <= lobby.getSize()-1; i++){
            this.cboats[i] = new Boat(ThreadLocalRandom.current().nextInt(1, 4));
            this.order[i] = lobby.getUsers()[seq];
            seq = seq +1 % lobby.getSize()-1;
        }
    }

    public void sendAllGameinfo(){
        for(User user : this.order) {
            this.clientListener.getServer().sendTo(getGameinfo(), user.getUsername());
        }
    }

    public gameInfoEvent getGameinfo(){
        String[] users = new String[this.lobby.getSize()-1];

        for (int i=0; i <= this.lobby.getSize()-1 ; i++ ){
            users[i] = this.order[i].getUsername();
        }

        gameInfoEvent gameInfo = new gameInfoEvent();
        gameInfo.setCboats(this.cboats);
        gameInfo.setOrder(users);
        gameInfo.setRound(this.round);
        gameInfo.setStorages(this.storages);

        return gameInfo;
    }

    public void setStartCards(){

    }

    public int getGameID() {
        return gameID;
    }

    public void updateStorages(boolean[][] storages) {
        this.storages = storages;
    }

    public void updateRound(int round) {
        this.round = round;
    }

    public void updateCboats(Boat[] cboats) {
        this.cboats = cboats;
    }
}
