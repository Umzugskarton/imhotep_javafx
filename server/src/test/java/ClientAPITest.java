import data.user.User;
import database.userdata.DBUserDataSource;
import events.EventReason;
import lobby.Lobby;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import requests.*;
import socket.ClientAPI;
import user.UserIdentifier;

import java.util.ArrayList;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ClientAPITest {

    @Mock
    LoginRequest lRequest;

    @Mock
    RegisterRequest rRequest;

    @Mock
    ChangeCredentialRequest cRequest;

    @Mock
    ChatRequest chatRequest;

    @Mock
    WhisperRequest wRequest;

    @Mock
    CreateRequest createRequest;

    @Mock
    User u1;

    @Mock
    DBUserDataSource dbUserDataSource;


    @Before
    public void init() {

    }

    @Test
    public void loginTestWhenCorrect() {
        ArrayList<String> loggedUser = new ArrayList<>();

        dbUserDataSource = mock(DBUserDataSource.class);
        when(dbUserDataSource.validateLogin("Tom", "123")).thenReturn(true);
        //Übermitteln der Login-Daten
        lRequest = mock(LoginRequest.class);
        when(lRequest.getUsername()).thenReturn("Tom");
        when(lRequest.getPassword()).thenReturn("123");

        ClientAPI clientAPI = new ClientAPI(dbUserDataSource);
        log.print(clientAPI.login(lRequest, loggedUser).getReason());
        assertEquals(true ,clientAPI.login(lRequest, loggedUser).getSuccess());

        //Bereits eingeloggter Nutzer
        loggedUser.add("Tom");
        assertEquals(false ,clientAPI.login(lRequest, loggedUser).getSuccess());
    }

    @Test
    public void loginTestWhenFalse() {
        //Test bei falschen Eingaben
        ArrayList<String> loggedUser = new ArrayList<>();

        dbUserDataSource = mock(DBUserDataSource.class);
        when(dbUserDataSource.validateLogin("Tom2", "1234")).thenReturn(false);
        //Übermitteln der Login-Daten
        lRequest = mock(LoginRequest.class);
        when(lRequest.getUsername()).thenReturn("Tom");
        when(lRequest.getPassword()).thenReturn("123");
        ClientAPI clientAPI = new ClientAPI(dbUserDataSource);
        assertEquals(false ,clientAPI.login(lRequest, loggedUser).getSuccess());
    }

    @Test
    public void registerTest() {
        dbUserDataSource = mock(DBUserDataSource.class);
        when(dbUserDataSource.createUser("Tom","123","email")).thenReturn(true);
        ClientAPI clientAPI = new ClientAPI(dbUserDataSource);
        rRequest = mock(RegisterRequest.class);
        //Testen, wenn null übermittelt wird
        assertEquals(EventReason.INVALID_REQUEST, clientAPI.register(rRequest).getReason());

        //Übermitteln der Register-Daten
        when(rRequest.getUsername()).thenReturn("Tom");
        when(rRequest.getPassword()).thenReturn("123");
        when(rRequest.getEmail()).thenReturn("email");
        assertEquals(EventReason.REGISTRATION_SUCCESSFUL, clientAPI.register(rRequest).getReason());
    }

    @Test
    public void registerTestAlreadyExists() {
        dbUserDataSource = mock(DBUserDataSource.class);
        when(dbUserDataSource.createUser("Tom","123","email")).thenReturn(false);
        ClientAPI clientAPI = new ClientAPI(dbUserDataSource);
        rRequest = mock(RegisterRequest.class);
        //Übermitteln der Register-Daten
        when(rRequest.getUsername()).thenReturn("Tom");
        when(rRequest.getPassword()).thenReturn("123");
        when(rRequest.getEmail()).thenReturn("email");
        //Testen, wenn createUser false zurückgibt (bspw. wenn der User bereits existiert)
        assertEquals(EventReason.REGISTRATION_FAILED_USER_OR_EMAIL_EXISTS, clientAPI.register(rRequest).getReason());
    }

    @Test
    public void changeEMailTest() {
        dbUserDataSource = mock(DBUserDataSource.class);
        ClientAPI clientAPI = new ClientAPI(dbUserDataSource);
        u1 = mock(User.class);

        cRequest = mock(ChangeCredentialRequest.class);
        when(cRequest.getCredential()).thenReturn("neueMail");
        when(cRequest.getCrednr()).thenReturn(1);
        clientAPI.changeCredential(cRequest, u1);
        verify(dbUserDataSource).changeUser(u1, UserIdentifier.EMAIL,"neueMail");
    }

    @Test
    public void changePasswordTest() {
        dbUserDataSource = mock(DBUserDataSource.class);
        ClientAPI clientAPI = new ClientAPI(dbUserDataSource);
        u1 = mock(User.class);

        cRequest = mock(ChangeCredentialRequest.class);
        when(cRequest.getCredential()).thenReturn("neuesPW");
        when(cRequest.getCrednr()).thenReturn(2);
        clientAPI.changeCredential(cRequest, u1);
        verify(dbUserDataSource).changeUser(u1, UserIdentifier.PASSWORD,"neuesPW");
    }

    @Test
    public void chatTest() {
        u1 = mock(User.class);
        chatRequest = mock(ChatRequest.class);
        ClientAPI clientAPI = new ClientAPI(dbUserDataSource);

        when(chatRequest.getMsg()).thenReturn("Hallo");
        assertEquals("Hallo", clientAPI.chat(chatRequest, u1).getMsg());
        when(chatRequest.getLobbyId()).thenReturn(1);
        assertEquals("Hallo", clientAPI.chat(chatRequest, u1).getMsg());
    }

    @Test
    public void whisperTest() {
        u1 = mock(User.class);
        wRequest = mock(WhisperRequest.class);
        ClientAPI clientAPI = new ClientAPI(dbUserDataSource);
        when(wRequest.getMsg()).thenReturn("Hallo");
        when(wRequest.getTo()).thenReturn("Werner");
        assertEquals("Hallo", clientAPI.whisper(wRequest, u1).getMsg());
    }

    @Test
    public void createLobbyTest(){
        u1 = mock(User.class);
        createRequest = mock(CreateRequest.class);
        ClientAPI clientAPI = new ClientAPI(dbUserDataSource);
        int size = 2;
        String name = "NewLobby";
        String password = "123";
        Lobby lobby = new Lobby(size, u1, name, password);
        when(createRequest.getName()).thenReturn(name);
        when(createRequest.getPassword()).thenReturn(password);
        when(createRequest.getSize()).thenReturn(size);
        assertEquals(lobby.getName(), clientAPI.createLobby(createRequest, u1).getName());
        assertEquals(lobby.getSize(), clientAPI.createLobby(createRequest, u1).getSize());
    }
}
