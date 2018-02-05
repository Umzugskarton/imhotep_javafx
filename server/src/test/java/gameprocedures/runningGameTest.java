package gameprocedures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import data.user.User;
import events.Event;
import events.SiteType;
import game.Game;
import game.Player;
import game.board.Ship;
import java.util.stream.IntStream;

import game.board.StoneSite;
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

  Move[] moves = new Move[4];

  Move[] moves2 = new Move[4];

  Move[] repeatMoves2 = new Move[4];

  Move[] moves3 = new Move[4];

  Move[] moves4 = new Move[4];

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
    lobby = new Lobby(4, u1, "LetzteHoffnung", "", server);
    lobby.join(u2, "");
    lobby.join(u3, "");
    lobby.join(u4, "");

    p1 = new Player(lobby.getUsers()[0], 0);
    p2 = new Player(lobby.getUsers()[0], 1);
    p3 = new Player(lobby.getUsers()[0], 2);
    p4 = new Player(lobby.getUsers()[0], 3);

    cl = mock(ClientListener.class);
    event = mock(Event.class);
    move = mock(Move.class);
    when(cl.getLobbyByID(1)).thenReturn(lobby);

    server = mock(Server.class);
    when(cl.getServer()).thenReturn(server);

    //Moves einzelner Spieler werden gesettet
    moves[0] = new FillUpStorageMove(lobby.getLobbyID());
    moves[1] = new FillUpStorageMove(lobby.getLobbyID());
    moves[2] = new FillUpStorageMove(lobby.getLobbyID());
    moves[3] = new FillUpStorageMove(lobby.getLobbyID());

    moves2[0] = new LoadUpShipMove(0, 0, lobby.getLobbyID());
    moves2[1] = new LoadUpShipMove(1, 0, lobby.getLobbyID());
    moves2[2] = new LoadUpShipMove(2, 0, lobby.getLobbyID());
    moves2[3] = new LoadUpShipMove(3, 0, lobby.getLobbyID());

    repeatMoves2[0] = new LoadUpShipMove(0, 1, lobby.getLobbyID());
    repeatMoves2[1] = new LoadUpShipMove(1, 1, lobby.getLobbyID());
    repeatMoves2[2] = new LoadUpShipMove(2, 1, lobby.getLobbyID());
    repeatMoves2[3] = new LoadUpShipMove(3, 1, lobby.getLobbyID());

    moves3[0] = new VoyageToStoneSiteMove(2, SiteType.TEMPLE, lobby.getLobbyID());
    moves3[1] = null;
    moves3[2] = null;
    moves3[3] = null;

    moves4[0] = new VoyageToStoneSiteMove(1, SiteType.PYRAMID, lobby.getLobbyID());
    moves4[1] = new VoyageToStoneSiteMove(2, SiteType.TEMPLE, lobby.getLobbyID());
    moves4[2] = null;
    moves4[3] = null;

  }

  @Test
  public void beforeGameRunsTest() {
    game = new Game(lobby, cl);
    //Überprüfen, dass zubeginn alles richtig gesetzt wird (private void setgame)
    //Überprüfen ob alle Spieler im Spiel sind
    Player[] players = {p1, p2, p3, p4};
    Player[] playersTest = {game.getPlayer(0), game.getPlayer(1), game.getPlayer(2),
        game.getPlayer(3)};
    assertEquals(players.length, playersTest.length);

    //Überprüfen das alle Schiffe initiiert worden sind
    Ship[] ships = {game.getShipByID(0), game.getShipByID(1), game.getShipByID(2),
        game.getShipByID(3)};
    assertEquals(4, ships.length);

    //Überprüfen ob alle Spieler ihre Steine bekommen haben
    for(int i = 0; i <= 3; i++){
      assertEquals(i+2, playersTest[i].getStones());
    }
  }

  @Test
  public void runTest() {
    //Game wird direkt gestartet
    game = new Game(lobby, cl);

    game.runOneRoundTest(moves);
    /**
    Wenn alle Spieler nur FillUpStorageMove verwenden, dann bekommt niemand Punkte (logisch)
     **/
    assertEquals(0, IntStream.of(game.getPointsSum()).sum());
    /**Market-Karten wurde ausgespielt
    assertEquals(4, game.getMARKET().getActiveCards().size());
    Keine Schiffe sind an StoneSites gedockt **/
    for(int i = 0; i <= 3; i++) {
      assertEquals(false, game.getShipByID(i).isDocked());
    }
    //Stein-Anzahl überprüfen
    for(int i = 0; i <= 3; i++) {
      assertEquals(5, game.getPlayer(i).getStones());
    }

    game.runOneRoundTest(moves2);
    game.runOneRoundTest(repeatMoves2);

    //Schiffe auf Steine überprüfen nachdem LoadUpShipMove auf alle Schiffe von allen Spieler ausgeführt worden sit
    for(int i = 0; i <= 3; i++) {
      assertEquals(2, game.getShipByID(i).getLoadedStones());
    }

    game.runOneRoundTest(moves3);
    assertEquals(true, ((StoneSite) game.getSiteByType(SiteType.TEMPLE)).isDocked());
  }
}
