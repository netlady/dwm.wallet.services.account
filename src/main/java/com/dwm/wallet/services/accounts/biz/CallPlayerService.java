package com.dwm.wallet.services.accounts.biz;

import org.springframework.web.client.RestTemplate;

/**
 * class for calling player service functions
 */

public class CallPlayerService {

    RestTemplate playerRest = new RestTemplate();
    private final String playerServiceURL = "http://localhost:8081/player";

    /**
     * check if specific playerID has any record in player-service
     * @param playerId
     * @return
     */
    public boolean IsPlayerExistence(int playerId) {
        String playerURL = String.format("%s/exist/%d", playerServiceURL, playerId);
        return playerRest.getForObject(playerURL, boolean.class);
    }

    /**
     * check if membershipNo exists; so return related playerId
     * @param membershipNo
     * @return : if the player doesn't exist then will be returned -1
     */
    public int getPlayerId(String membershipNo) {
        String playerURL = String.format("%s/Id/%s", playerServiceURL, membershipNo);
        return playerRest.getForObject(playerURL, int.class);
    }
}