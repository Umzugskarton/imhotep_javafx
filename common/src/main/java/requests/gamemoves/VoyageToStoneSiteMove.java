package requests.gamemoves;

import events.SiteType;
import requests.RequestType;

public class VoyageToStoneSiteMove implements Move {

    private RequestType move = RequestType.VOYAGE_TO_STONE_SITE;
    private int shipId;
    private SiteType stoneSite;
    private int lobbyId;

    public VoyageToStoneSiteMove(int shipId, SiteType stoneSite, int lobbyId) {
        this.shipId = shipId;
        this.stoneSite = stoneSite;
        this.lobbyId = lobbyId;
    }

    public int getLobbyId() {
        return lobbyId;
    }

    public int getShipId() {
        return shipId;
    }

    public SiteType getStoneSite() {
        return stoneSite;
    }

    @Override
    public RequestType getType() {
        return move;
    }
}
