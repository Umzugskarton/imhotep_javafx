package ui.app.main.lobbylist;

public class LobbyTableData {
    private String lobbyId, name, belegung, userSize, lobbySize;

    public LobbyTableData(String lobbyId, String name, String userSize, String lobbySize){
        this.lobbyId = lobbyId;
        this.name = name;
        this.belegung = userSize + " / " + lobbySize;
        this.userSize = userSize;
        this.lobbySize = lobbySize;
    }

    public String getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(String lobbyId) {
        this.lobbyId = lobbyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBelegung() {
        return belegung;
    }

    public String getUserSize() {
        return userSize;
    }

    public void setUserSize(String userSize) {
        this.userSize = userSize;
        setBelegung(userSize, this.lobbySize);
    }

    public String getLobbySize() {
        return lobbySize;
    }

    public void setLobbySize(String lobbySize) {
        this.lobbySize = lobbySize;
        setBelegung(this.userSize, lobbySize);
    }

    private void setBelegung(String userSize, String lobbySize){
        this.belegung = userSize + " / " + lobbySize;
    }
}
