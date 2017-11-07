package game;

import GameEvents.gameInfoEvent;
import GameObjects.Bauwerke.Pyramid;
import GameObjects.Boat;
import SRVevents.Event;
import lobby.Lobby;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import socket.ClientListener;
import user.User;
import java.util.concurrent.ThreadLocalRandom;

public class Game implements Runnable {
    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    private int gameID;
    private Lobby lobby;
    private Boat[] cboats;
    private boolean[][] storages;
    private Player[] order;
    private int round;
    private Pyramid pyramid;
    private ClientListener clientListener;

    public Game(Lobby lobby, ClientListener clientListener) {
        this.lobby = lobby;
        this.gameID = this.lobby.getLobbyID();
        this.clientListener = clientListener;
        lobby.show(false);
        this.cboats = new Boat[lobby.getSize()];
        order = new Player[lobby.getSize()];
        storages = new boolean[lobby.getSize() - 1][5];
        setGame();
        this.pyramid = new Pyramid();
        setStartCards();
        sendAllGameinfo();
        run();
    }

    public void resetCboats() {
        for (int i = 0; i <= lobby.getSize() - 1; i++) {
            this.cboats[i] = new Boat(ThreadLocalRandom.current().nextInt(1, 4));
        }
    }

    public void setGame() {
        int seq = ThreadLocalRandom.current().nextInt(0, this.lobby.getSize() - 1);
        for (int i = 0; i <= lobby.getSize() - 1; i++) {
            this.cboats[i] = new Boat(ThreadLocalRandom.current().nextInt(1, 4));
            this.order[i] = new Player(lobby.getUsers()[seq], i);
            seq = seq + 1 % lobby.getSize() - 1;
        }
    }

    public void sendAllGameinfo() {
        for (Player player : this.order) {
            sendGameInfoTo(player.getUser());
        }
    }


    public void sendGameInfoTo(User user) {
        this.clientListener.getServer().sendTo(getGameinfo(), user.getUsername());
    }

    public gameInfoEvent getGameinfo() {
        String[] users = new String[this.lobby.getSize() - 1];

        for (int i = 0; i <= this.lobby.getSize() - 1; i++) {
            users[i] = this.order[i].getUser().getUsername();
        }

        gameInfoEvent gameInfo = new gameInfoEvent();
        gameInfo.setCboats(this.cboats);
        gameInfo.setOrder(users);
        gameInfo.setRound(this.round);
        gameInfo.setStorages(this.storages);

        return gameInfo;
    }

    public void setStartCards() {

    }


    @Override
    public void run() {
        for (int i = 0; i <= 6; i++) {
            this.round = i;
            // allGameInfoEvent event = new allGameInfoEvent Allen Players senden Wer Dran , Reihenfolge? , Steine, punkte , Boote
            while (true) {
                try {
                    sendAndwait(new userListEvent());
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
                break;
            }
        }
    }

    public synchronized void sendAndwait(Event event) throws InterruptedException {
        this.wait(30);
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
