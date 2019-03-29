package com.intuit.cg;

import com.intuit.cg.score.Penalty;
import java.util.List;

/**
 *
 * @author sakhim
 */
public interface Game {

    String name();

    int maxPlayers();

    List<Player> playerList();

    String start();

    Player currentPlayer();

    List<Move> moveList();

    String makeMove(Move move);

    boolean isEnd();

    void setPenalty(Penalty penalty);

    void setPlayerList(List<Player> playerList);

}
