package com.intuit.cg.blackjack;

import com.intuit.cg.Move;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author sakhim
 */
public enum BlackjackMove implements Move {

    HIT, STAND;

    public static BlackjackMove of(Move move) {
        return valueOf(move.name());
    }

    public static List<Move> list() {
        return Stream
                .of(values())
                .collect(Collectors.toList());
    }

}
