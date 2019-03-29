# Card game
This Java program implements a simplified version of **Blackjack** that allows players to either hit or stand.
Moves like split and double down will be implemented in a future version.

### How to use
Players can choose among 2 modes, single-player or multi-player (7 players max).
Single-player mode allows players to choose among 3 levels of difficulty, easy, medium or hard.
Players can be newly created or choosen from a list of players.
New players are credited with an initial score of 1000.
Available players and their scores are fetched from the save file specified at program startup.
Players can quit a game session at any time using the quit command.
New players and score changes are saved to the specified save file.

### Scoring system
Like on a real Blackjack table, players compete against a dealer (the house), not against each other.
Nonetheless, winners can still be designated based on the highest score after an agreed upon number of rounds.
Player scores are updated at the end of every round:

*  -2 points for busted players
*  -3 points for players not receiving a blackjack and a dealer blackjack
* +10 points for players receiving a blackjack without a dealer blackjack
*  +3 points for players with a hand greater than the dealer's hand
*  +6 points for players with a hand valued as 21 and greater than the dealer's hand
*  +3 points for unbusted players and a busted dealer
*  +6 points for players with a hand valued as 21 and a busted dealer
*  -1 points for players with a hand smaller than the dealer's hand
*  -2 points for players with a hand smaller than the dealer's hand and the latter is valued as 21

### Difficulty
Penalty values are multiplied by 1, 2 or 3 depending on the choosen difficulty (easy, medium or hard respectively)

### Build
JDK 8 or greater is needed to build with Gradle wrapper, from project root directory run:
```bash
./gradlew build
```

### Run
Run generated JAR file from project root directory using:
```bash
java -jar build/libs/card_games.jar players.txt
```
