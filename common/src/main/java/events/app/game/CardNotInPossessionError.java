package events.app.game;

import requests.gamemoves.CardType;

public class CardNotInPossessionError extends GameEvent {

    CardType card;

    public CardNotInPossessionError(CardType card, int lobbyId) {
        this.lobbyId = lobbyId;
        this.card = card;
    }
}
