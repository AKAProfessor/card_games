package com.intuit.cg.dao;

import com.intuit.cg.dao.validator.Validator;
import com.intuit.cg.Player;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author sakhim
 */
public class PlayerDao implements Dao<Player> {

    private static final String PROPERTY_SEPARATOR = ";";

    private final Path saveFilePath;
    private final Validator<String> validator;

    public PlayerDao(String saveFileLocation, Validator<String> validator) {
        this.saveFilePath = Paths.get(saveFileLocation);
        this.validator = validator;
    }

    @Override
    public List<Player> getAll() {
        List<String> playerLineList = null;
        try {
            playerLineList = Files.lines(saveFilePath).collect(Collectors.toList());
        } catch (IOException ex) { // TODO: Thrown exception should be handled properly
            throw new RuntimeException(ex);
        }
        return playerLineList
                .stream()
                .map(this::lineAsPlayer)
                .collect(Collectors.toList());
    }

    @Override
    public void saveAll(List<Player> playerList) {
        List<String> playerLineList
                = Stream.of(playerList, getAll())
                        .flatMap(List::stream)
                        .distinct()
                        .map(this::playerAsLine)
                        .collect(Collectors.toList());
        try {
            Files.write(saveFilePath, playerLineList);
        } catch (IOException ex) { // TODO: Thrown exception should be handled properly
            throw new RuntimeException(ex);
        }
    }

    private String playerAsLine(Player player) {
        String playerLine = player.getName() + PROPERTY_SEPARATOR + player.getScore();
        validator.validate(playerLine); // TODO: Thrown exception should be handled
        return playerLine;
    }

    private Player lineAsPlayer(String playerLine) {
        validator.validate(playerLine); // TODO: Thrown exception should be handled
        String[] playerProperties = playerLine.split(PROPERTY_SEPARATOR);
        return new Player(playerProperties[0], Integer.valueOf(playerProperties[1]));
    }

}
