package commands;

import commands.chat.ChatCommand;
import commands.chat.WhisperCommand;
import commands.lobby.*;
import commands.main.UserlistCommand;
import commands.profil.ChangeCredentialCommand;
import commands.start.LoginCommand;
import commands.start.LogoutCommand;
import commands.start.RegisterCommand;
import events.Event;
import events.app.chat.ChatMessageEvent;
import lobby.Lobby;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.mockito.Mock;
import requests.RequestType;
import requests.chat.ChatRequest;
import requests.chat.WhisperRequest;
import requests.lobby.*;
import requests.main.LogoutRequest;
import requests.main.UserlistRequest;
import requests.profil.ChangeCredentialRequest;
import requests.start.login.LoginRequest;
import requests.start.registration.RegisterRequest;
import socket.ClientAPI;
import socket.ClientListener;
import socket.Server;

import static junit.framework.TestCase.assertTrue;
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
    ChangeColorRequest changeColorRequest;

    @Mock
    ChangeCredentialRequest changeCredentialRequest;

    @Mock
    CreateRequest createRequest;

    @Mock
    JoinRequest joinRequest;

    @Mock
    LeaveLobbyRequest leaveLobbyRequest;

    @Mock
    LobbylistRequest lobbylistRequest;

    @Mock
    LoginRequest loginRequest;

    @Mock
    LogoutRequest logoutRequest;

    @Mock
    RegisterRequest registerRequest;

    @Mock
    SetReadyRequest setReadyRequest;

    @Mock
    StartGameRequest startGameRequest;

    @Mock
    UserlistRequest userlistRequest;

    @Mock
    WhisperRequest whisperRequest;

    @Mock
    ChatMessageEvent chatMessageEvent;


    ChatCommand chatCommand;
    ChangeColorCommand changeColorCommand;
    ChangeCredentialCommand changeCredentialCommand;
    CreateCommand createCommand;
    JoinCommand joinCommand;
    LeaveLobbyCommand leaveLobbyCommand;
    LobbylistCommand lobbylistCommand;
    LoginCommand loginCommand;
    LogoutCommand logoutCommand;
    RegisterCommand registerCommand;
    SetReadyCommand setReadyCommand;
    StartGameCommand startGameCommand;
    UserlistCommand userlistCommand;
    WhisperCommand whisperCommand;

    @Before
    public void init() {
        lobby = mock(Lobby.class);
        chatMessageEvent = mock(ChatMessageEvent.class);
    }

    @Test
    public void getCommand() {
        server = mock(Server.class);
        cL = mock(ClientListener.class);
        chatRequest = mock(ChatRequest.class);
        changeColorRequest = mock(ChangeColorRequest.class);
        changeCredentialRequest = mock(ChangeCredentialRequest.class);
        chatRequest = mock(ChatRequest.class);
        createRequest = mock(CreateRequest.class);
        joinRequest = mock(JoinRequest.class);
        leaveLobbyRequest = mock(LeaveLobbyRequest.class);
        lobbylistRequest = mock(LobbylistRequest.class);
        loginRequest = mock(LoginRequest.class);
        logoutRequest = mock(LogoutRequest.class);
        registerRequest = mock(RegisterRequest.class);
        setReadyRequest = mock(SetReadyRequest.class);
        startGameRequest = mock(StartGameRequest.class);
        userlistRequest = mock(UserlistRequest.class);
        whisperRequest = mock(WhisperRequest.class);


        clientAPI = mock(ClientAPI.class);

        CommandFactory commandFactory = new CommandFactory(cL);
        when(cL.getClientAPI()).thenReturn(clientAPI);
        when(cL.getServer()).thenReturn(server);
        //Alle Commands prüfen
        chatCommand = new ChatCommand(cL);
        changeColorCommand = new ChangeColorCommand(cL);
        changeCredentialCommand = new ChangeCredentialCommand(cL);
        createCommand = new CreateCommand(cL);
        joinCommand = new JoinCommand(cL);
        leaveLobbyCommand = new LeaveLobbyCommand(cL);
        lobbylistCommand = new LobbylistCommand(cL);
        loginCommand = new LoginCommand(cL);
        logoutCommand = new LogoutCommand(cL);
        registerCommand = new RegisterCommand(cL);
        setReadyCommand = new SetReadyCommand(cL);
        startGameCommand = new StartGameCommand(cL);
        userlistCommand = new UserlistCommand(cL);
        whisperCommand = new WhisperCommand(cL);

        when(chatRequest.getType()).thenReturn(RequestType.CHAT);
        when(changeColorRequest.getType()).thenReturn(RequestType.CHANGE_COLOR);
        when(changeCredentialRequest.getType()).thenReturn(RequestType.CHANGE_CREDENTIAL);
        when(joinRequest.getType()).thenReturn(RequestType.JOIN);
        when(createRequest.getType()).thenReturn(RequestType.CREATE);
        when(leaveLobbyRequest.getType()).thenReturn(RequestType.LEAVE_LOBBY);
        when(lobbylistRequest.getType()).thenReturn(RequestType.LOBBYLIST);
        when(loginRequest.getType()).thenReturn(RequestType.LOGIN);
        when(logoutRequest.getType()).thenReturn(RequestType.LOGOUT);
        when(registerRequest.getType()).thenReturn(RequestType.REGISTER);
        when(setReadyRequest.getType()).thenReturn(RequestType.SET_READY);
        when(userlistRequest.getType()).thenReturn(RequestType.USERLIST);
        when(whisperRequest.getType()).thenReturn(RequestType.WHISPER);
        assertTrue(commandFactory.getCommand(chatRequest) instanceof ChatCommand);
        assertTrue(commandFactory.getCommand(changeColorRequest) instanceof ChangeColorCommand);
        assertTrue(commandFactory.getCommand(changeCredentialRequest) instanceof ChangeCredentialCommand);
        assertTrue(commandFactory.getCommand(joinRequest) instanceof JoinCommand);
        assertTrue(commandFactory.getCommand(createRequest) instanceof CreateCommand);
        assertTrue(commandFactory.getCommand(leaveLobbyRequest) instanceof LeaveLobbyCommand);
        assertTrue(commandFactory.getCommand(lobbylistRequest) instanceof LobbylistCommand);
        assertTrue(commandFactory.getCommand(loginRequest) instanceof LoginCommand);
        assertTrue(commandFactory.getCommand(logoutRequest) instanceof LogoutCommand);
        assertTrue(commandFactory.getCommand(registerRequest) instanceof RegisterCommand);
        assertTrue(commandFactory.getCommand(setReadyRequest) instanceof SetReadyCommand);
        assertTrue(commandFactory.getCommand(userlistRequest) instanceof UserlistCommand);
        assertTrue(commandFactory.getCommand(whisperRequest) instanceof WhisperCommand);
    }
}
