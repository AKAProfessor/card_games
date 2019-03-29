package com.intuit.cg;

import com.intuit.cg.cli.GameCLI;
import com.intuit.cg.cli.GameConfigurationCLI;
import com.intuit.cg.dao.Dao;
import com.intuit.cg.dao.PlayerDao;
import com.intuit.cg.dao.validator.PlayerLineValidator;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Usage: java -jar card_games.jar [save-file]");
            return;
        }
        if (args.length != 1) {
            System.err.println("Invalid parameters: " + Arrays.toString(args));
            return;
        }
        if (!Files.exists(Paths.get(args[0]))) {
            System.err.println("Specified file not found: " + args[0]);
            return;
        }

        new Application().run(args[0]);

    }

    public void run(String saveFileLocation) {

        // TODO: DAO should be used through a service
        Dao<Player> playerDao = new PlayerDao(saveFileLocation, new PlayerLineValidator());
        List<Player> playerList = playerDao.getAll();

        GameConfigurationCLI gameConfigurationCLI = new GameConfigurationCLI(playerList);
        Game game = gameConfigurationCLI.run();

        if (game != null) {
            GameCLI gameCLI = new GameCLI(game);
            gameCLI.run();
            playerDao.saveAll(game.playerList());
        }

    }

}
