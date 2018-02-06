package gameprocedures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.longThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import data.user.User;
import events.Event;
import events.SiteType;
import game.Game;
import game.Player;
import game.board.Market;
import game.board.Ship;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

import game.board.Stone;
import game.board.StoneSite;
import game.board.cards.Card;
import game.board.cards.OrnamentCard;
import game.board.cards.ToolCard;
import game.gameprocedures.VoyageToStoneSite;
import lobby.Lobby;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import requests.gamemoves.*;
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

  int lobbyID;

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

    lobbyID = lobby.getLobbyID();
    //Moves einzelner Spieler werden gesettet
    moves[0] = new FillUpStorageMove(lobbyID);
    moves[1] = new FillUpStorageMove(lobbyID);
    moves[2] = new FillUpStorageMove(lobbyID);
    moves[3] = new FillUpStorageMove(lobbyID);

    moves2[0] = new LoadUpShipMove(0, 0, lobbyID);
    moves2[1] = new LoadUpShipMove(1, 0, lobbyID);
    moves2[2] = new LoadUpShipMove(2, 0, lobbyID);
    moves2[3] = new LoadUpShipMove(3, 0, lobbyID);

    repeatMoves2[0] = new LoadUpShipMove(0, 1, lobbyID);
    repeatMoves2[1] = new LoadUpShipMove(1, 1, lobbyID);
    repeatMoves2[2] = new LoadUpShipMove(2, 1, lobbyID);
    repeatMoves2[3] = new LoadUpShipMove(3, 1, lobbyID);

    moves3[0] = new VoyageToStoneSiteMove(0, SiteType.TEMPLE, lobbyID);
    moves3[1] = null;
    moves3[2] = null;
    moves3[3] = null;

    moves4[0] = new VoyageToStoneSiteMove(1, SiteType.PYRAMID, lobbyID);
    moves4[1] = new VoyageToStoneSiteMove(2, SiteType.TEMPLE, lobbyID);
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

    /**Aufgrund der Randomizierung der Schiffsgrößen müssen abhängig der Größe des Schiffes genug Steine gelagert sein um eine Voyage durchführen zu können
    Leider ist es deswegen umständlich das RundenEnde zu simulieren, aber man kann aufgrund der simplen boolean Methoden
     von StoneSite isDocked() schließen, dass die alle ships die Möglichkeit haben sich an jeder StoneSite andocken zu können**/
    Move[] moves5 = new Move[4];
    int actionShip[] = new int[4];
    int i = 0;
    for(Ship ship : game.getShips()) {
      if(ship.getMinimumStones() == 1) {
        moves5[i] = new VoyageToStoneSiteMove(ship.getId(), SiteType.TEMPLE, lobbyID);
        actionShip[i] = 1;
      }
      if(ship.getMinimumStones() == 2) {
        moves5[i] = new VoyageToStoneSiteMove(ship.getId(), SiteType.OBELISKS, lobbyID);
        actionShip[i] = 2;
      }
      if(ship.getMinimumStones() == 3) {
        moves5[i] = new LoadUpShipMove(ship.getId(), 2, lobbyID);
        actionShip[i] = 0;
      }
    }
    game.runOneRoundTest(moves5);

    if(Arrays.asList(actionShip).contains(1)) {
      assertEquals(true, ((StoneSite) game.getSiteByType(SiteType.TEMPLE)).isDocked());
      System.out.println("An Tempel wurde ein Schiff angedockt");
    }
    if(Arrays.asList(actionShip).contains(2)) {
      assertEquals(true, ((StoneSite) game.getSiteByType(SiteType.OBELISKS)).isDocked());
      System.out.println("An Obelisk wurde ein Schiff angedockt");
    }
    for(int ship : actionShip) {
      if(actionShip[ship] == 0) {
        if(Arrays.asList(actionShip).contains(0)) {
          assertEquals(3, game.getShipByID(ship));
          System.out.print("Schiff wurde um einen weiteren Stein beladen");
        }
      }
    }
  }

  @Test
  public void VoyageToMarketMove() {
    game = new Game(lobby, cl);

    game.runOneRoundTest(moves);
    game.runOneRoundTest(moves2);
    game.runOneRoundTest(repeatMoves2);
    game.runOneRoundTest(repeatMoves2);

    Move[] marketMove = {
      new VoyageToMarketMove(0,lobbyID), null, null, null
    };

    for(int i = 0; i <= 3; i++) {
      assertEquals(0, game.getPlayer(i).getCards().size());
    }
    game.runOneRoundTest(marketMove);
    for(int i = 0; i <= 3; i++) {
      System.out.println(game.getPlayer(i).getCards().size());
    }

    //assertEquals(0,((Market) game.getSiteByType(SiteType.MARKET)).getActiveCards().size());
    for(int i = 0; i <= 3; i++) {
      assertEquals(1, game.getPlayer(i).getCards().size());
    }
  }

  @Test
  public void AnyVoyageFail() {
    game = new Game(lobby, cl);
    game.runOneRoundTest(moves);
    //Fehlschlag durch ungenügend Steine
    game.runOneRoundTest(moves3);
    assertEquals(false, game.getShipByID(0).isDocked());

    //Steine werden auf das Schiff geladen
    game.runOneRoundTest(moves2);
    game.runOneRoundTest(repeatMoves2);
    game.runOneRoundTest(repeatMoves2);

    Move[] marketMove = {
            new VoyageToMarketMove(0,lobbyID), null, null, null
    };

    Move[] marketMove2 = {
            new VoyageToMarketMove(1,lobbyID), null, null, null
    };

    game.runOneRoundTest(marketMove);
    game.runOneRoundTest(marketMove2);
    assertEquals(false, game.getShipByID(1).isDocked());

  }

  @Test
  public void toolCardsTest() {
    game = new Game(lobby, cl);
    Move[] hammerMove = {
      new ToolCardMove(CardType.HAMMER, lobbyID), new ToolCardMove(CardType.HAMMER, lobbyID), null, null
    };

    Move[] leverMove = {
            new ToolCardMove(CardType.LEVER, lobbyID), null, null, null
    };
    Move[] chiselMove = {
            new ToolCardMove(CardType.CHISEL, lobbyID), null, null, null
    };

    CardType card = CardType.HAMMER;
    ToolCard card1 = new ToolCard(card);
    game.getPlayer(0).addCard(card1);

    game.runOneRoundTest(hammerMove);
    //game.runOneRoundTest(leverMove);
    //game.runOneRoundTest(chiselMove);
    assertEquals(5, game.getPlayer(0).getStones());
  }

  @Test
  public void cardChoosenTest() {
    game = new Game(lobby, cl);
    Card activeCard1 = ((Market)game.getSiteByType(SiteType.MARKET)).getActiveCards().get(0);
    Card activeCard2 = ((Market)game.getSiteByType(SiteType.MARKET)).getActiveCards().get(1);
    Card activeCard3 = ((Market)game.getSiteByType(SiteType.MARKET)).getActiveCards().get(2);
    Card activeCard4 = ((Market)game.getSiteByType(SiteType.MARKET)).getActiveCards().get(3);
    Move[] chooseMove = {
            new ChooseCardMove(lobbyID, 0), new ChooseCardMove(lobbyID, 1),
                    new ChooseCardMove(lobbyID, 2), new ChooseCardMove(lobbyID, 3)
    };
    game.runOneRoundTest(chooseMove);
    assertEquals(activeCard1, game.getPlayer(0).getCards().get(0));

  }

  @Test
  public void VTSMDM() {
    game = new Game(lobby, cl);
    game.runOneRoundTest(moves);
    game.runOneRoundTest(moves2);
    game.runOneRoundTest(repeatMoves2);
    game.runOneRoundTest(repeatMoves2);
    Stone[] j = game.getShipByID(2).getStones();
    int i = game.getShipByID(2).getSize();
    if(i == 3) {
      int[] dumpOrder = {1,2,0};
      Move[] manualDumpMove = {
              new ToolCardMove(CardType.LEVER, lobbyID), null, null, null
      };
      game.runOneRoundTest(manualDumpMove);
      assertEquals(j,game.getShipByID(2).getStones());
    }
    if(i == 4) {
       int[] dumpOrder = {1,2,0,3};
      Move[] manualDumpMove = {
              new ToolCardMove(CardType.LEVER, lobbyID), null, null, null
      };
      game.runOneRoundTest(manualDumpMove);
      assertEquals(j,game.getShipByID(2).getStones());
    }


  }

}
