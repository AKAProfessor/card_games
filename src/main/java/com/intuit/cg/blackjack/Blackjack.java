package com.intuit.cg.blackjack;

import com.intuit.cg.Move;
import com.intuit.cg.blackjack.score.BlackjackScore;
import com.intuit.cg.blackjack.deck.BlackjackCardshoe;
import com.intuit.cg.Game;
import com.intuit.cg.Player;
import com.intuit.cg.deck.CardShoe;
import com.intuit.cg.score.Penalty;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author sakhim
 */
public class Blackjack implements Game {

    private BlackjackScore score;
    private List<Player> playerList;
    private final Player dealer = new Player("------ Dealer", 0);
    private final CardShoe cardShoe = new BlackjackCardshoe(6);
    private List<Move> moveList = BlackjackMove.list();
    private int currentPlayer;

    @Override
    public String name() {
        return "Simplified Blackjack";
    }

    @Override
    public int maxPlayers() {
        return 7;
    }

    @Override
    public List<Player> playerList() {
        return playerList;
    }

    @Override
    public String start() {
        playerList.forEach(player -> player.draw(cardShoe));
        dealer.draw(cardShoe);
        playerList.forEach(player -> player.draw(cardShoe));
        // Stand is the only available move when first player hits a Blackjack
        if (currentPlayer().isBlackjack()) {
            moveList = Arrays.asList(BlackjackMove.STAND);
        }
        return PlayerFormatter.format(dealer, playerList);
    }

    @Override
    public List<Move> moveList() {
        return moveList;
    }

    @Override
    public String makeMove(Move move) {
        switch (BlackjackMove.of(move)) {
            case HIT:
                currentPlayer().draw(cardShoe);
                break;
            case STAND:
                currentPlayer = ++currentPlayer % playerList.size();
                if (currentPlayer == 0 && playerList.stream().anyMatch(Player::isNotBust)) {
                    while (dealer.handSum() < 17) {
                        dealer.draw(cardShoe);
                    }
                }
        }
        // When move completes current round
        if (dealer.handSum() >= 17 || playerList.stream().allMatch(Player::isBust)) {
            score.compute(playerList, dealer);
            // TODO: Refactor when more moves available
            moveList = BlackjackMove.list();
            String completeRound = PlayerFormatter.format(dealer, playerList);
            // Reset all hands
            dealer.clearHand();
            playerList.forEach(Player::clearHand);
            String newRound = start();
            return PlayerFormatter.format(completeRound, newRound);
        }
        // Stand is the only available move when player hand is complete
        moveList = currentPlayer().isHandComplete() ? Arrays.asList(BlackjackMove.STAND) : BlackjackMove.list();
        return PlayerFormatter.format(dealer, playerList);
    }

    @Override
    public Player currentPlayer() {
        return playerList.get(currentPlayer);
    }

    @Override
    public boolean isEnd() {
        return cardShoe.isEmpty();
    }

    @Override
    public void setPenalty(Penalty penalty) {
        this.score = new BlackjackScore(penalty);
    }

    @Override
    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

}
