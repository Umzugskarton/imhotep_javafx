package gameprocedures;

import data.user.User;
import events.Event;
import events.SiteType;
import events.app.game.ShipDockedEvent;
import game.Game;
import game.Player;
import game.board.Pyramids;
import game.board.Ship;
import game.gameprocedures.VoyageToStoneSite;
import game.gameprocedures.toolcardprotocols.HammerProtocol;
import lobby.Lobby;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import requests.gamemoves.FillUpStorageMove;
import requests.gamemoves.LoadUpShipMove;
import requests.gamemoves.Move;
import requests.gamemoves.VoyageToStoneSiteMove;
import socket.ClientListener;
import socket.Server;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class runningGameTest {
    @Mock
    User u1;

    @Mock
    User u2;

    @Mock
    User u3;

    @Mock
    User u4;

    @Mock
    ClientListener cl;

    @Mock
    Move move;

    @Mock
    Server server;

    @Mock
    Event event;

    @Mock
    FillUpStorageMove fillUp;

    Lobby lobby;

    @Mock
    Game game;

    Player p1;

    Player p2;

    Player p3;

    Player p4;
    @Before
    public void init() {
        //Mock the Player
        u1 = mock(User.class);
        u2 = mock(User.class);
        u3 = mock(User.class);
        u4 = mock(User.class);
        //
        when(u1.getId()).thenReturn(0);
        when(u2.getId()).thenReturn(1);
        when(u3.getId()).thenReturn(2);
        when(u4.getId()).thenReturn(3);

        when(u1.getUsername()).thenReturn("Ich");
        when(u2.getUsername()).thenReturn("kann");
        when(u3.getUsername()).thenReturn("nicht");
        when(u4.getUsername()).thenReturn("mehr");



        User[] users = {u1, u2, u3, u4};
        lobby = new Lobby(4, u1, "LetzteHoffnung", "");
        lobby.join(u2,"");
        lobby.join(u3,"");
        lobby.join(u4,"");

        p1 = new Player(lobby.getUsers()[0], 0);
        p2 = new Player(lobby.getUsers()[0], 1);
        p3 = new Player(lobby.getUsers()[0], 2);
        p4 = new Player(lobby.getUsers()[0], 3);



        cl = mock(ClientListener.class);
        event = mock(Event.class);
        when(cl.getLobbyByID(1)).thenReturn(lobby);

        server = mock(Server.class);
        when(cl.getServer()).thenReturn(server);

        game = new Game(lobby, cl);
    }

    @Test
    public void beforeGameRunsTest() {
        //Überprüfen, dass zubeginn alles richtig gesetzt wird (private void setgame)
        //Überprüfen ob alle Spieler im Spiel sind
        Player[] players = {p1, p2, p3, p4};
        Player[] playersTest = {game.getPlayer(0), game.getPlayer(1), game.getPlayer(2), game.getPlayer(3)};
        assertEquals(players.length, playersTest.length);

        //Überprüfen das alle Schiffe initiiert worden sind
        Ship[] ships = {game.getShipByID(0), game.getShipByID(1), game.getShipByID(2), game.getShipByID(3)};
        assertEquals(4, ships.length);

        //Überprüfen ob alle Spieler ihre Steine bekommen haben
        assertEquals(2, playersTest[0].getSupplySled().getStones());
        assertEquals(3, playersTest[1].getSupplySled().getStones());
        assertEquals(4, playersTest[2].getSupplySled().getStones());
        assertEquals(5, playersTest[3].getSupplySled().getStones());
    }

    @Test
    public void runTest() {
        Move[] moves = {
                new FillUpStorageMove(lobby.getLobbyID()), new FillUpStorageMove(lobby.getLobbyID()), new FillUpStorageMove(lobby.getLobbyID()), new FillUpStorageMove(lobby.getLobbyID())
        };
        game.runOneRoundTest(moves);
        //Wenn alle Spieler nur FillUpStorageMove verwenden
        //Niemand bekommt dadurch Punkte
        assertEquals(0, IntStream.of(game.getPointsSum()).sum());
        //Market-Karten wurde ausgespielt
        assertEquals(4, game.getMARKET().getActiveCards().size());
        //Keine Schiffe sind gedockt
        assertEquals(false, game.getShipByID(0).isDocked());
        assertEquals(false, game.getShipByID(1).isDocked());
        assertEquals(false, game.getShipByID(2).isDocked());
        assertEquals(false, game.getShipByID(3).isDocked());
        //Stein-Anzahl überprüfen
        assertEquals(5, game.getPlayer(0).getSupplySled().getStones());
        assertEquals(5, game.getPlayer(1).getSupplySled().getStones());
        assertEquals(5, game.getPlayer(2).getSupplySled().getStones());
        assertEquals(5, game.getPlayer(3).getSupplySled().getStones());

        Move[] moves2 = {
                new LoadUpShipMove(0,0, lobby.getLobbyID()), new LoadUpShipMove(1,0, lobby.getLobbyID()), new LoadUpShipMove(2,0, lobby.getLobbyID()), new LoadUpShipMove(3,0, lobby.getLobbyID())
        };
        game.runOneRoundTest(moves2);

        Move[] moves3 = {
                new VoyageToStoneSiteMove(0, SiteType.MARKET, lobby.getLobbyID()),new VoyageToStoneSiteMove(1, SiteType.PYRAMID, lobby.getLobbyID()), new VoyageToStoneSiteMove(2, SiteType.OBELISKS, lobby.getLobbyID()),new VoyageToStoneSiteMove(3, SiteType.BURIALCHAMBER,lobby.getLobbyID())
        };
        game.runOneRoundTest(moves3);


    }
}
