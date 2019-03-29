package com.intuit.cg.cli;

import com.intuit.cg.Game;
import com.intuit.cg.Move;

/**
 *
 * @author sakhim
 */
public class GameCLI {

    private final Game game;

    public GameCLI(Game game) {
        this.game = game;
    }

    public void run() {
        String result = game.start();
        System.out.println(result);
        while (!game.isEnd()) {
            CLIUtils.printHeader(game.currentPlayer().getName() + " should make a move");
            for (int i = 0; i < game.moveList().size(); i++) {
                System.out.println(i + " - " + game.moveList().get(i));
            }
            int selectedMove = CLIUtils.parseInteger(s -> CLIUtils.validateInteger(s, 0, game.moveList().size() - 1));
            if (selectedMove == -1) {
                return;
            }
            Move move = game.moveList().get(selectedMove);
            String moveResult = game.makeMove(move);
            System.out.println(moveResult);
        }
    }

}
