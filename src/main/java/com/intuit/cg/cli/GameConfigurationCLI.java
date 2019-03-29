package com.intuit.cg.cli;

import com.intuit.cg.CardGame;
import com.intuit.cg.Game;
import com.intuit.cg.GameFactory;
import com.intuit.cg.Player;
import com.intuit.cg.score.Difficulty;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sakhim
 */
public class GameConfigurationCLI {

    private final List<Player> playerList;

    // TODO: Refactor to a game configuration class (use step builder pattern)
    public GameConfigurationCLI(List<Player> playerList) {
        this.playerList = playerList;
    }

    public Game run() {

        CLIUtils.printHeader("Select game");
        for (int i = 0; i < CardGame.values().length; i++) {
            System.out.println(i + " - " + CardGame.values()[i]);
        }
        Integer selectedGame = CLIUtils.parseInteger(s -> CLIUtils.validateInteger(s, 0, CardGame.values().length - 1));
        if (selectedGame == -1) {
            return null;
        }
        Game game = GameFactory.getInstance(selectedGame);

        CLIUtils.printHeader("Select number of players 1 - " + game.maxPlayers());
        Integer numberOfPlayers = CLIUtils.parseInteger(s -> CLIUtils.validateInteger(s, 1, game.maxPlayers()));
        if (numberOfPlayers == -1) {
            return null;
        }

        CLIUtils.printHeader("Select or create players");
        List<Player> selectedPlayerList = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("0 - New player");
            for (int j = 0; j < playerList.size(); j++) {
                Player player = playerList.get(j);
                System.out.println(j + 1 + " - " + player.getName() + "(score: " + player.getScore() + ")");
            }
            int selectedPlayerNumber = CLIUtils.parseInteger(s -> CLIUtils.validateInteger(s, 0, playerList.size()));
            if (selectedPlayerNumber == -1) {
                return null;
            }
            if (selectedPlayerNumber == 0) {
                CLIUtils.printHeader("Enter new player name");
                String currentPlayerName = CLIUtils.parseString(s -> CLIUtils.validatePlayerName(s));
                if (currentPlayerName == null) {
                    return null;
                }
                Player newPlayer = new Player(currentPlayerName);
                selectedPlayerList.add(newPlayer);
            } else {
                Player selectedPlayer = playerList.remove(selectedPlayerNumber - 1);
                selectedPlayerList.add(selectedPlayer);
            }
        }
        game.setPlayerList(selectedPlayerList);

        Difficulty difficulty = Difficulty.EASY;
        if (selectedPlayerList.size() == 1) {
            CLIUtils.printHeader("Select difficulty");
            Difficulty[] difficulties = Difficulty.values();
            for (int i = 0; i < difficulties.length; i++) {
                System.out.println(i + " - " + difficulties[i]);
            }
            Integer selectedDifficulty = CLIUtils.parseInteger(s -> CLIUtils.validateInteger(s, 0, Difficulty.values().length - 1));
            if (selectedDifficulty == -1) {
                return null;
            }
            difficulty = Difficulty.of(1 + selectedDifficulty);
        }
        game.setPenalty(difficulty);

        return game;
    }

}
