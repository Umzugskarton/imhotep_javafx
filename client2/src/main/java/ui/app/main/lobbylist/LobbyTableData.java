package ui.app.main.lobbylist;

import ui.app.lobby.LobbyView;

public class LobbyTableData {
    private int lobbyId, userSize, lobbySize;
    private String lobbyIdString, name, belegung, userSizeString, lobbySizeString;

    public LobbyTableData(int lobbyId, String name, int userSize, int lobbySize){
        setLobbyId(lobbyId);
        this.name = name;
        setBelegung(userSize, lobbySize);
        setUserSize(userSize);
        setLobbySize(lobbySize);
    }

    public int getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(int lobbyId) {
        this.lobbyId = lobbyId;
        this.lobbyIdString = String.valueOf(lobbyId);
    }

    public int getUserSize() {
        return userSize;
    }

    public void setUserSize(int userSize) {
        this.userSize = userSize;
        this.userSizeString = String.valueOf(userSize);
        setBelegung(userSize, this.lobbySize);
    }

    public int getLobbySize() {
        return lobbySize;
    }

    public void setLobbySize(int lobbySize) {
        this.lobbySize = lobbySize;
        this.lobbySizeString = String.valueOf(lobbySize);
        setBelegung(this.userSize, lobbySize);
    }

    public String getLobbyIdString() {
        return lobbyIdString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserSizeString() {
        return userSizeString;
    }

    public String getLobbySizeString() {
        return lobbySizeString;
    }

    private void setBelegung(String userSize, String lobbySize){
        this.belegung = userSize + " / " + lobbySize;
    }

    private void setBelegung(int userSize, int lobbySize){
        this.belegung = String.valueOf(userSize) + " / " + String.valueOf(lobbySize);
    }

    public String getBelegung() {
        return belegung;
    }
}
