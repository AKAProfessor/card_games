package com.intuit.cg;

import com.intuit.cg.deck.CardShoe;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author sakhim
 */
public class Player {

    private String name;
    private int score;

    private final List<Integer> hand = new ArrayList<>();

    // TODO: Refactor to player decorator class (use decorator pattern)
    public Player(String name) {
        this(name, 1000);
    }

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incScore(int value) {
        this.score += value;
    }

    public List<Integer> getHand() {
        return hand;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Player other = (Player) obj;
        return Objects.equals(name, other.name);
    }

    public void draw(CardShoe cardShoe) {
        hand.add(cardShoe.draw());
    }

    public void clearHand() {
        hand.clear();
    }

    public int handSum() {
        return isSoftHand() ? handPipSum() + 10 : handPipSum();
    }

    public boolean isBust() {
        return handSum() > 21;
    }

    public boolean isNotBust() {
        return handSum() <= 21;
    }

    public boolean isTwentyOne() {
        return handSum() == 21;
    }

    public boolean isSoftHand() {
        return hand.contains(1) && handPipSum() < 11;
    }

    public boolean isBlackjack() {
        return hand.contains(1) && hand.size() == 2 && handSum() == 11;
    }

    public boolean isHandComplete() {
        return isBust() || isTwentyOne() || isBlackjack();
    }

    private int handPipSum() {
        return hand.stream().mapToInt(Integer::intValue).sum();
    }

}
