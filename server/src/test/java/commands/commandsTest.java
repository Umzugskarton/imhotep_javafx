package commands;

import commands.chat.ChatCommand;
import events.Event;
import events.app.chat.ChatMessageEvent;
import lobby.Lobby;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.mockito.Mock;
import requests.RequestType;
import requests.chat.ChatRequest;
import socket.ClientAPI;
import socket.ClientListener;
import socket.Server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.*;

public class commandsTest {
    @Mock
    ClientAPI clientAPI;

    @Mock
    ClientListener cL;

    @Mock
    Server server;

    @Mock
    Lobby lobby;

    @Mock
    ChatRequest chatRequest;

    @Mock
    ChatMessageEvent chatMessageEvent;


    ChatCommand chatCommand;

    @Before
    public void init() {
        lobby = mock(Lobby.class);
        chatMessageEvent = mock(ChatMessageEvent.class);
    }

    @Test
    public void getCommand() {
        chatCommand = new ChatCommand(cL);
        chatRequest = mock(ChatRequest.class);
        cL = mock(ClientListener.class);
        when(cL.getServer()).thenReturn(server);
        clientAPI = mock(ClientAPI.class);
        CommandFactory commandFactory = new CommandFactory(cL);
        when(cL.getClientAPI()).thenReturn(clientAPI);
        when(chatRequest.getType()).thenReturn(RequestType.CHAT);
        assertEquals(chatCommand, commandFactory.getCommand(chatRequest));
    }
}
