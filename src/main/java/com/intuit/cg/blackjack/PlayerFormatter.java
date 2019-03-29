package com.intuit.cg.blackjack;

import com.intuit.cg.Player;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author sakhim
 */
public class PlayerFormatter {

    // TODO: Create better formatter (use card suits ASCII)
    public static String format(String completeRound, String newRound) {
        return new StringBuilder()
                .append(completeRound)
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("<><><><> New round <><><><>")
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .append(newRound)
                .toString();
    }

    // TODO: Create better formatter (use card suits ASCII)
    public static String format(Player dealer, List<Player> playerList) {
        return format(dealer)
                + System.lineSeparator()
                + playerList
                        .stream()
                        .map(PlayerFormatter::format)
                        .collect(Collectors.joining(System.lineSeparator()));
    }

    // TODO: Create better formatter (use card suits ASCII)
    private static String format(Player player) {
        String hand = player
                .getHand()
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" + "));
        String handState = null;
        if (player.isBust()) {
            handState = "Bust";
        } else if (player.isBlackjack()) {
            handState = "Blackjack";
        } else if (player.isSoftHand()) {
            handState = "Soft";
        } else if (player.isTwentyOne()) {
            handState = "Twenty one";
        }
        return new StringBuilder()
                .append(player.getName())
                .append("(score: ")
                .append(player.getScore())
                .append(") ")
                .append(hand)
                .append(" = ")
                .append(player.handSum())
                .append(handState != null ? " (" + handState + ")" : "")
                .toString();
    }

}
