package game.GameProcedures.ToolCardProtocols;

import GameEvents.LoadUpShipExclusiveEvent;
import GameEvents.ToolCardEvent;
import GameEvents.VoyageToStoneSiteExclusiveEvent;
import GameMoves.LoadUpShipMove;
import GameMoves.Move;
import GameMoves.VoyageToStoneSiteMove;
import game.Game;

public class SailProtocol implements Protocol {
    private Game game;
    private int playerId;

    public SailProtocol(Game game, int playerId) {
        this.game = game;
        this.playerId = playerId;
    }

    public void exec() {
        game.sendAll(new ToolCardEvent("Sail", playerId, true));

        Move move;
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                game.sendTo(game.getOrder()[playerId].getUser(), new LoadUpShipExclusiveEvent());
                move = aquireMove();
                if (move instanceof LoadUpShipMove) {
                    game.executeMove(move);
                }
            } else {
                game.sendTo(game.getOrder()[playerId].getUser(), new VoyageToStoneSiteExclusiveEvent());
                move = aquireMove();
                if (move instanceof VoyageToStoneSiteMove) {
                    game.executeMove(move);
                }
            }
        }
    }

    private Move aquireMove() {
        game.getExecutor().waitForMove();
        if (game.getExecutor().getMove() != null)
            return game.getExecutor().getMove();
        return null;
    }
}



