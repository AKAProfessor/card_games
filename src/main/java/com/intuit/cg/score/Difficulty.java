package com.intuit.cg.score;

import java.util.stream.Stream;

/**
 *
 * @author sakhim
 */
public enum Difficulty implements Penalty {

    EASY(1), MEDIUM(2), HARD(3);

    private final int value;

    private Difficulty(int value) {
        this.value = value;
    }

    public static Difficulty of(int value) {
        return Stream
                .of(values())
                .filter(d -> d.value == value)
                .findFirst()
                .orElse(null);
    }

    @Override
    public int value() {
        return value;
    }

}
