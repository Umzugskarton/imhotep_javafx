package game.gameprocedures;

import game.Game;
import java.util.EnumMap;
import requests.RequestType;
import requests.gamemoves.Move;

public class ProcedureFactory {
  private EnumMap<RequestType, Procedure> procedures = new EnumMap<>(RequestType.class);

  public ProcedureFactory(int playerId, Game game) {
    procedures.put(RequestType.RESUPPLY_STORAGE, new FillUpStorage(game, playerId));
    procedures.put(RequestType.LOAD_UP_SHIP, new LoadUpShip(game, playerId));
    procedures.put(RequestType.VOYAGE_TO_STONE_SITE, new VoyageToStoneSite(game, playerId));
    procedures.put(RequestType.LEAD_TOOL_CARD, new LeadToolCard(game, playerId));
  }

  public Procedure getProcedure(Move move) {
    return procedures.get(move.getType());
  }
}
