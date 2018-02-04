package game.board.cards;

import events.SiteType;
import events.app.game.StoneAddedToSiteEvent;
import game.Game;
import game.board.Stone;
import game.board.StoneSite;
import requests.gamemoves.CardType;
import java.util.EnumMap;

public class LocationCard extends Card {
  EnumMap<CardType, SiteType> typeEnumMap = new EnumMap<>(CardType.class);

  public LocationCard(CardType cardType) {
    typeEnumMap.put(CardType.SARCOPHAGUS, SiteType.BURIALCHAMBER);
    typeEnumMap.put(CardType.ENTRANCE, SiteType.PYRAMID);
    typeEnumMap.put(CardType.PAVEDPATH, SiteType.OBELISKS);
    this.setType(cardType);
  }

  public StoneAddedToSiteEvent exec(Game game , int playerid ){
    SiteType type = typeEnumMap.get(getType());
    StoneSite stoneSite = (StoneSite) game.getSiteByType(type);
    stoneSite.addStone(new Stone(game.getPlayer(playerid)));
    return new StoneAddedToSiteEvent(game.getGameID(), playerid, type);
  }
}
