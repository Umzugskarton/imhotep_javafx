package game.gameprocedures.toolcardprotocols;

import events.app.game.LoadUpShipExclusiveEvent;
import events.app.game.OutOfStonesError;
import events.app.game.ToolCardEvent;
import game.Game;
import requests.gamemoves.CardType;
import requests.gamemoves.LoadUpShipMove;
import requests.gamemoves.Move;

public class ChiselProtocol extends Protocol {

    public ChiselProtocol(Game game, int playerId) {
        super(game, playerId);
    }

    public void exec() {
        game.sendAll(new ToolCardEvent(CardType.CHISEL, playerId, true, game.getGameID()));
        for (int i = 0; i < 2; i++) {
            game.sendTo(game.getPlayer(playerId).getUser(), new LoadUpShipExclusiveEvent(game.getGameID()));
            if (game.getPlayer(playerId).getStones() > 0) {
                game.getExecutor().waitForMove();
                Move move = acquireMove();
                if (move instanceof LoadUpShipMove) {
                    game.executeMove(move);
                }
            } else {
                game.sendTo(game.getPlayer(playerId).getUser(), new OutOfStonesError(playerId, game.getGameID()));
                break;
            }
        }
    }
}
