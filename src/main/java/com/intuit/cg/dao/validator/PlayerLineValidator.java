package com.intuit.cg.dao.validator;

/**
 *
 * @author sakhim
 */
public class PlayerLineValidator implements Validator<String> {

    private static final String PLAYER_LINE_REGEX = "^[A-Za-z0-9]{2,20};[0-9]{1,6}$";

    @Override
    public void validate(String playerLine) {
        if (playerLine == null || !playerLine.matches(PLAYER_LINE_REGEX)) {
            throw new IllegalArgumentException("Player line '" + playerLine + "' does not match '" + PLAYER_LINE_REGEX + "'");
        }
    }

}
