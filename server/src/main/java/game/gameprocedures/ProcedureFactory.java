package game.gameprocedures;

import java.util.EnumMap;
import requests.gamemoves.Move;
import game.Game;

import requests.gamemoves.MoveType;

public class ProcedureFactory {
  private EnumMap<MoveType, Procedure> procedures = new EnumMap<>(MoveType.class);

  public ProcedureFactory(int playerId, Game game) {
    procedures.put(MoveType.RESUPPLY_STORAGE, new FillUpStorage(game, playerId));
    procedures.put(MoveType.LOAD_UP_SHIP, new LoadUpShip(game, playerId));
    procedures.put(MoveType.VOYAGE_TO_STONE_SITE, new VoyageToStoneSite(game, playerId));
    procedures.put(MoveType.LEAD_TOOL_CARD, new LeadToolCard(game, playerId));
  }

  public Procedure getProcedure(Move move) {
    return procedures.get(move.getMoveType());
  }
}
