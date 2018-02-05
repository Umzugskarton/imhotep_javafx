import data.user.User;
import events.Event;
import lobby.Lobby;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import socket.ClientAPI;
import socket.ClientListener;
import socket.Server;

import java.net.ServerSocket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ServerTest {
    @Mock
    Server serverMock;

    @Mock
    Lobby lobby;

    @Mock
    Event event;

    @Mock
    ClientAPI clientAPI;

    @Mock
    ClientListener cL;

    @Mock
    ServerSocket sc;

    @Mock
    User u1;

    @Mock
    User u2;

    @Mock
    User u3;

    @Mock
    User u4;

    @Before
    public void init() {
        serverMock = mock(Server.class);

        u1 = mock(User.class);
        u2 = mock(User.class);
        u3 = mock(User.class);
        u4 = mock(User.class);

        sc = mock(ServerSocket.class);
        cL = mock(ClientListener.class);

        lobby = mock(Lobby.class);

        when(sc.getLocalPort()).thenReturn(1);
        User[] users = {u1, u2, u3, u4};
        when(u1.getUsername()).thenReturn("Tim");
        when(u2.getUsername()).thenReturn("Tom");
        when(u3.getUsername()).thenReturn("Tam");
        when(u4.getUsername()).thenReturn("Tum");
        when(lobby.getUsers()).thenReturn(users);
    }

    @Test
    public void sendToTest() {
        Server server = new Server(clientAPI, 1);
        assertEquals(false, server.sendTo(null, "Tim"));
        //TODO: Testen ob an verbundene ClientListener gesendet wird
    }


    @Test
    public void sendToLobbyTest() {
        Server server = new Server(clientAPI, 2);
        server.sendToLobby(null, lobby);
        verify(u1, times(2)).getUsername();
        verify(u2, times(2)).getUsername();
        verify(u3, times(2)).getUsername();
        verify(u4, times(2)).getUsername();
    }

    @Test
    public void sendToAll() {
        Server server = new Server(clientAPI, 3);
        server.sendToAll(null);
        when(cL.getUser()).thenReturn(u1);
    }

    @Test
    public void runTest() throws Exception {
        Server server = new Server(clientAPI, 4);
        //?
    }

    @Test
    public void addLobbyTest() {
        Server server = new Server(clientAPI, 5);
        server.addLobby(lobby);
        assertEquals(lobby, server.getLobbybyID(0));
    }

    @Test
    public void getLobbybyIDTest() {
        Server server = new Server(clientAPI, 6);
        assertEquals(null, server.getLobbybyID(1));
    }

    @Test
    public void getLobbiesTest() {
        Server server = new Server(clientAPI, 7);
        server.addLobby(lobby);
        server.addLobby(lobby);


    }

    @Test
    public void sendToLoggedInTest() {
        Server server = new Server(clientAPI, 8);
        server.sendToLoggedIn(null);
    }
}
