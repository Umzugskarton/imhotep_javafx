package gameprocedures;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import data.user.User;
import events.app.game.UpdatePointsEvent;
import game.Game;
import game.Player;
import game.board.Pyramids;
import game.board.Stone;
import lobby.Lobby;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import socket.ClientListener;

public class GameTest {

  @Mock
  Player p1;

  @Mock
  Player p2;

  @Mock
  Player p3;

  @Mock
  Player p4;

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
  Lobby lobby;

  @Mock
  UpdatePointsEvent event;

  int[] zeroPoints = {0, 0, 0, 0};

  @Before
  public void init() {
    u1 = mock(User.class);
    u2 = mock(User.class);
    u3 = mock(User.class);
    u4 = mock(User.class);
    when(u1.getId()).thenReturn(0);
    when(u2.getId()).thenReturn(1);
    when(u3.getId()).thenReturn(2);
    when(u4.getId()).thenReturn(3);
    User[] users = {u1, u2, u3, u4};
    lobby = mock(Lobby.class);
    when(lobby.getLobbyID()).thenReturn(1);
    when(lobby.getSize()).thenReturn(users.length);
    when(lobby.getUsers()).thenReturn(users);
    cl = mock(ClientListener.class);
    when(cl.getLobbyByID(1)).thenReturn(lobby);
  }

  @Test
  public void getPointsSumTest() {
    Game game = new Game(lobby, cl);
    //Start des Spiels haben alle Spieler 0 Punkte
    assertArrayEquals(zeroPoints, game.getPointsSum());
    //Spieler besitzen bereits Punkte
    game.getPlayer(0).addPoints(10);
    game.getPlayer(1).addPoints(20);
    game.getPlayer(2).addPoints(30);
    game.getPlayer(3).addPoints(40);
    int[] sumPoints = {10, 20, 30, 40};
    assertArrayEquals(sumPoints, game.getPointsSum());
  }

  @Test
  public void updatePyramidsTest() {
    Game game = new Game(lobby, cl);
    Pyramids pyramids = new Pyramids(4);
    Stone s1 = new Stone(game.getPlayer(0));
    Stone s2 = new Stone(game.getPlayer(1));
    Stone s3 = new Stone(game.getPlayer(2));
    Stone s4 = new Stone(game.getPlayer(3));
    when(game.getPointsSum()).thenReturn(zeroPoints);
    game.updatePyramids();
    assertEquals(zeroPoints, game.getPointsSum());

    Stone[] newStones = {s1, s2, s3, s4};
    pyramids.addStones(newStones);
  }

  @Test
  public void getCargoAsIntArrayByShipTest() {

  }

  @Test
  public void runTest() {

  }

  @Test
  public void executeMoveTest() {

  }
}
