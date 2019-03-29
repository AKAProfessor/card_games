package com.intuit.cg.deck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author sakhim
 */
public class DefaultCardShoe implements CardShoe {

    private final Random random = new Random();
    private final Map<Integer, Integer> cardRankToCardCountMap = new HashMap<>();
    private final List<Integer> availableCardRankList = new ArrayList<>();

    private int numberOfDecks;

    public DefaultCardShoe(int numberOfDecks) {
        if (numberOfDecks < 1) {
            throw new IllegalArgumentException("Expected positive number of decks value, but value was: " + numberOfDecks);
        }
        this.numberOfDecks = numberOfDecks;
        init();
    }

    @Override
    public int draw() {
        if (availableCardRankList.isEmpty()) {
            throw new IllegalStateException("Empty card shoe");
        }
        int index = 0;
        if (availableCardRankList.size() != 1) {
            index = random.nextInt(availableCardRankList.size());
        }
        int cardRank = availableCardRankList.get(index);
        int cardCount = cardRankToCardCountMap.get(cardRank);
        if (--cardCount == 0) {
            cardRankToCardCountMap.remove(cardRank);
            availableCardRankList.remove(index);
        } else {
            cardRankToCardCountMap.put(cardRank, cardCount);
        }
        return cardRank;
    }

    @Override
    public boolean isEmpty() {
        return availableCardRankList.isEmpty();
    }

    private void init() {
        availableCardRankList.add(1); // A
        availableCardRankList.add(2);
        availableCardRankList.add(3);
        availableCardRankList.add(4);
        availableCardRankList.add(5);
        availableCardRankList.add(6);
        availableCardRankList.add(7);
        availableCardRankList.add(8);
        availableCardRankList.add(9);
        availableCardRankList.add(10);
        availableCardRankList.add(11); // J
        availableCardRankList.add(12); // Q
        availableCardRankList.add(13); // K
        for (Integer cardRank : availableCardRankList) {
            // Standard 52-card deck (4 suits x 13 ranks)
            cardRankToCardCountMap.put(cardRank, 4 * numberOfDecks);
        }
    }

}
