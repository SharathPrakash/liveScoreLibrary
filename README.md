# LiveScore Library
This is a Java implementation of the LiveScore interface that allows tracking of scores for ongoing games.

---
**WARNING**

Don't use this in production. It was created for demo purposes only.

---
**Usage**

To use the LiveScoreFactory class, simply call the createLiveScore() method:
````java
LiveScore liveScore = LiveScoreFactory.createLiveScore();
````
This will create a new instance of LiveScore using the default implementation provided by LiveScoreImpl.

---

**Custom Implementations**

If you want to create a custom implementation of LiveScore, you can do so by implementing the LiveScore interface and passing it to the factory method:
````java
public class CustomLiveScore implements LiveScore {
    // implement methods...
}

LiveScore liveScore = LiveScoreFactory.createLiveScore(new CustomLiveScore());

````
This will create a new instance of LiveScore using your custom implementation.

---

## How to use it:

Add the following dependency:
````XML
<dependency>
    <groupId>com.sportRadar</groupId>
    <artifactId>liveScoreLibrary</artifactId>
    <version>${VERSION}</version>
</dependency>
````

---

**Starting a Game**

To start a game, call the startGame method and pass the names of the home and away teams as parameters.

````java
liveScore.startGame("Home Team", "Away Team");
````
---

**Updating a Score**

To update the score of a game, call the updateScore method and pass the names of the home and away teams, as well as their updated scores.

````java
liveScore.updateScore("Home Team", "Away Team", 1, 0);
````
---

**Finishing a Game**

To finish a game, call the finishGame method and pass the names of the home and away teams.

---

````java
liveScore.finishGame("Home Team", "Away Team");
````
---
**Getting a Summary**

To get a summary of all ongoing games, call the getSummary method. This method returns a list of strings, where each string represents a game and its score.

````java
List<String> summary = liveScore.getSummary();
````
---

**Implementation Details**

The LiveScoreImpl class uses three data structures to store information about games: a Map to store the scores of each game, a Map to store the home and away teams for each game, and a Map to store a summary of all games based on their score.

The startGame method initializes these data structures for a new game, while the updateScore and finishGame methods update and remove information about an existing game.

The getSummary method returns a list of strings that are generated by mapping the data in the data structures to a formatted string.

---
**License**

This code is licensed under the MIT License.

---
