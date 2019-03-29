package com.intuit.cg.blackjack.score;

import com.intuit.cg.Player;
import com.intuit.cg.score.Penalty;
import java.util.List;

/**
 *
 * @author sakhim
 */
public class BlackjackScore {

    private final Penalty penalty;

    public BlackjackScore(Penalty penalty) {
        if (penalty.value() < 1) {
            throw new IllegalArgumentException("Expected positive penalty value, but value was: " + penalty.value());
        }
        this.penalty = penalty;
    }

    public void compute(List<Player> playerList, Player dealer) {
        for (Player player : playerList) {
            if (player.isBust()) {
                player.incScore(-2 * penalty.value());
            } else if (player.isBlackjack() && !dealer.isBlackjack()) {
                player.incScore(10);
            } else if (dealer.isBlackjack() && !player.isBlackjack()) {
                player.incScore(-3 * penalty.value());
            } else if (player.handSum() > dealer.handSum() || dealer.isBust()) {
                player.incScore(3);
                if (player.isTwentyOne()) {
                    player.incScore(3);
                }
            } else if (player.handSum() < dealer.handSum()) {
                player.incScore(-1 * penalty.value());
                if (dealer.isTwentyOne()) {
                    player.incScore(-1 * penalty.value());
                }
            }
        }
    }

}
