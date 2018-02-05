import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import events.app.chat.ChatMessageEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import lobby.Lobby;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import socket.ClientAPI;
import socket.ClientListener;
import socket.Server;

public class ClientListenerTest {

  @Mock
  Lobby lobby;

  @Mock
  Server server;

  @Mock
  ObjectOutputStream oc;

  @Mock
  ObjectInputStream ic;

  @Mock
  ClientAPI clientAPI;

  @Mock
  ClientListener cL;

  ChatMessageEvent chatMessageEvent;

  @Before
  public void init() {
    server = mock(Server.class);
    oc = mock(ObjectOutputStream.class);
    ic = mock(ObjectInputStream.class);
    clientAPI = mock(ClientAPI.class);
    chatMessageEvent = new ChatMessageEvent();
    cL = new ClientListener(server, oc, ic, clientAPI);
  }

  //Ohne In/Output-Streams lassen sich run und send nicht testen
  @Test(expected = NullPointerException.class)
  public void runTest() {
    cL.run();
  }

  @Test(expected = NullPointerException.class)
  public void sendTest() {
    cL.send(chatMessageEvent);
  }

  @Test
  public void getLobbyByID() {
    lobby = mock(Lobby.class);
    cL.addLobby(lobby);
    when(lobby.getLobbyID()).thenReturn(1);
    assertEquals(lobby, cL.getLobbyByID(1));
  }
}
