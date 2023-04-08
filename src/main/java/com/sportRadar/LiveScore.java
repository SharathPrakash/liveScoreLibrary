package com.sportRadar;

import java.util.List;

public interface LiveScore {

    /**
     * Starts a new game with the given home and away teams.
     *
     * @param homeTeam the name of the home team
     * @param awayTeam the name of the away team
     * @throws IllegalArgumentException if either of the team names is null or empty
     */
    void startGame(String homeTeam, String awayTeam);

    /**
     * Updates the score of a game with the given home and away teams, adding the given home and away scores.
     *
     * @param homeTeam  the name of the home team
     * @param awayTeam  the name of the away team
     * @param homeScore the score of the home team to be added
     * @param awayScore the score of the away team to be added
     * @throws IllegalArgumentException if the game is not found
     */
    void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore);

    /**
     * Finishes an ongoing game between the home and away teams and removes it from the maps.
     *
     * @param homeTeam the name of the home team
     * @param awayTeam the name of the away team
     * @throws IllegalArgumentException if the game is not found in the matches map
     */
    void finishGame(String homeTeam, String awayTeam);

    /**
     * Gets the summary of all games based on their score.
     *
     * @return a list of strings representing the summary of all games based on their score.
     */
    List<String> getSummary();
}
