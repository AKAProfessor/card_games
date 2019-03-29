package com.intuit.cg.blackjack.deck;

import com.intuit.cg.deck.DefaultCardShoe;

/**
 *
 * @author sakhim
 */
public class BlackjackCardshoe extends DefaultCardShoe {

    public BlackjackCardshoe(int numberOfDecks) {
        super(numberOfDecks);
    }

    @Override
    public int draw() {
        int cardRank = super.draw();
        // Face cards J (11), Q (12) and K (13) are worth 10 in Blackjack
        return cardRank > 10 ? 10 : cardRank;
    }

}
