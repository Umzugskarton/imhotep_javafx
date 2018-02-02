package game.board;

import data.user.User;
import lobby.Lobby;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LobbyTest {


    @Test
    public void swapHostTest() {
        User host = new User(1, "host", "123", "email");
        User test = new User(2, "test2", "123", "email");
        Lobby lobby = new Lobby(2, host,"", "");
        lobby.join(test, "");

        lobby.leave(host);
        assertEquals("test2", lobby.getHostName());
    }

    @Test
    public void getHostNameTest() {
        User host = new User(1, "host", "123", "email");
        Lobby lobby = new Lobby(2, host,"", "");
        assertEquals("host", lobby.getHostName());
    }

    @Test
    public void joinTest() {
        User host = new User(1, "host", "123", "email");
        User user1 = new User(2, "user1", "123", "email");
        User user2 = new User(3, "user2", "123", "email");
        User user3 = new User(4, "user3", "123", "email");
        Lobby lobby = new Lobby(2, host,"", "123");

        //User1 joint mit richtigem Passwort
        lobby.join(user1,"123");
        assertEquals("user1", lobby.getUsers()[1].getUsername());
        //User2 gibt falsches Passwort bei Eintritt in die Lobby ein
        User[] userList = lobby.getUsers();
        lobby.join(user2,"1234");
        assertEquals(userList, lobby.getUsers());

        //User2 und User3 möchten beitreten, aber die Lobby hat bereits nach User2 die maximale Kapazität erreicht
        lobby.join(user2, "123");
        userList = lobby.getUsers();
        lobby.join(user3, "123");
        assertEquals(userList, lobby.getUsers());
    }

    @Test
    public void getColorsTest(){
        User host = new User(1, "host", "123", "email");
        User user1 = new User(2, "user1", "123", "email");
        User user2 = new User(3, "user2", "123", "email");
        User user3 = new User(4, "user3", "123", "email");
        Lobby lobby = new Lobby(4, host,"", "");
        lobby.join(user1, "");
        lobby.join(user2,"");
        lobby.join(user3, "");
        //Überprüfen ob jeder Teilnehmer eine andere Farbe zugeordnet bekommt
        ArrayList<String> colors = lobby.getColors();
        String hostC = colors.get(0);
        String user1C = colors.get(1);
        String user2C = colors.get(2);
        String user3C = colors.get(3);

        ArrayList<String> testColor = colors;
        testColor.remove(hostC);
        assertEquals(true, !testColor.contains(hostC));

        testColor = colors;
        testColor.remove(user1C);
        assertEquals(true, !testColor.contains(user1C));

        testColor = colors;
        testColor.remove(user2C);
        assertEquals(true, !testColor.contains(user2C));

        testColor = colors;
        testColor.remove(user3C);
        assertEquals(true, !testColor.contains(user3C));
    }

    @Test
    public void getUsersStringArrayTest() {

    }

    @Test
    public void setReadyTest() {
        User host = new User(1, "host", "123", "email");
        User user1 = new User(2, "user1", "123", "email");
        Lobby lobby = new Lobby(4, host,"", "");
        lobby.join(user1, "");
        //Ausgangs-ready-Status
        assertEquals(false, lobby.getReady()[0]);
        assertEquals(false, lobby.getReady()[1]);
        //Ready setzen
        lobby.setReady(host);
        lobby.setReady(user1);
        assertEquals(true, lobby.getReady()[0]);
        assertEquals(true, lobby.getReady()[1]);
        //Ready entfernen
        lobby.setReady(host);
        lobby.setReady(user1);
        assertEquals(false, lobby.getReady()[0]);
        assertEquals(false, lobby.getReady()[1]);
    }

    @Test
    public void replaceColorTest() {
        User host = new User(1, "host", "123", "email");
        User user1 = new User(2, "user1", "123", "email");
        Lobby lobby = new Lobby(4, host,"", "");
        lobby.join(user1, "");
        ArrayList<String> colors = lobby.getColors();
        lobby.replaceColor(host);
        assertEquals(true, !lobby.getColors().contains(colors.get(0)));
        lobby.replaceColor(user1);
        assertEquals(true, !lobby.getColors().contains(colors.get(1)));
    }

    @Test
    public void getUserCountTest() {
        User host = new User(1, "host", "123", "email");
        User user1 = new User(2, "user1", "123", "email");
        Lobby lobby = new Lobby(4, host,"", "");
        lobby.join(user1, "");

        assertEquals(2, lobby.getLobbyUserArrayList().size());

    }

    @Test
    public void generateColorsTest() {
        User host = new User(1, "host", "123", "email");

        Lobby lobby = new Lobby(4, host,"", "");



    }

    @Test
    public void leaveLobbyEventTest() {

    }

}
