package com.intuit.cg;

import com.intuit.cg.blackjack.Blackjack;

/**
 *
 * @author sakhim
 */
public class GameFactory {

    public static Game getInstance(int num) {
        CardGame cardGame = CardGame.of(num);
        if (cardGame == null) {
            throw new IllegalArgumentException("Card game num invalid: " + num);
        }
        switch (cardGame) {
            case BLACKJACK:
                return new Blackjack();
            case GO_FISH:
            case GIN_RUMMY:
            default:
                throw new IllegalArgumentException("Card game not supported yet: " + cardGame);
        }
    }

}
