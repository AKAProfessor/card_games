package com.intuit.cg;

import java.util.stream.Stream;

/**
 *
 * @author sakhim
 */
public enum CardGame {

    BLACKJACK(0), GO_FISH(1), GIN_RUMMY(2);

    private final int num;

    private CardGame(int num) {
        this.num = num;
    }

    public static CardGame of(int num) {
        return Stream
                .of(values())
                .filter(cg -> cg.num == num)
                .findFirst()
                .orElse(null);
    }

}
