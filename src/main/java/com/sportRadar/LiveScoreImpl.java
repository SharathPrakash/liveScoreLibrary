package com.sportRadar;

import java.util.*;

/**
 * Implementation of the LiveScore interface that allows tracking of scores for ongoing games.
 */
class LiveScoreImpl implements LiveScore {

    private final Map<String, int[]> scores;
    private final Map<String, String[]> matches;
    private final Map<Integer, List<String>> summary;

    /**
     * Constructor to initialize the data structures used in this class.
     */
    public LiveScoreImpl() {
        scores = new HashMap<>();
        matches = new HashMap<>();
        summary = new TreeMap<>(Collections.reverseOrder());
    }

    @Override
    public void startGame(String homeTeam, String awayTeam) {
        Objects.requireNonNull(homeTeam, "homeTeam must not be null");
        Objects.requireNonNull(awayTeam, "awayTeam must not be null");

        if (homeTeam.isEmpty() || awayTeam.isEmpty()) {
            throw new IllegalArgumentException("Invalid team names");
        }
        String[] teams = {homeTeam, awayTeam};
        String game = homeTeam + " vs " + awayTeam;

        matches.put(game, teams);
        scores.put(game, new int[]{0, 0});

        summary.putIfAbsent(0, new ArrayList<>());
        summary.get(0).add(game);

    }

    @Override
    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        String game = homeTeam + " vs " + awayTeam;
        if (!matches.containsKey(game)) {
            throw new IllegalArgumentException("Game not found");
        }

        int[] score = scores.get(game);
        score[0] += homeScore;
        score[1] += awayScore;
        int prevScore = score[0] + score[1] - homeScore - awayScore;

        summary.get(prevScore).remove(game);
        if (summary.get(prevScore).isEmpty()) {
            summary.remove(prevScore);
        }

        summary.putIfAbsent(score[0] + score[1], new ArrayList<>());
        summary.get(score[0] + score[1]).add(game);
    }

    @Override
    public void finishGame(String homeTeam, String awayTeam) {
        String game = homeTeam + " vs " + awayTeam;
        if (!matches.containsKey(game)) {
            throw new IllegalArgumentException("Game not found");
        }
        int[] score = scores.get(game);
        int totalScore = score[0] + score[1];
        summary.get(totalScore).remove(game);
        if (summary.get(totalScore).isEmpty()) {
            summary.remove(totalScore);
        }
        matches.remove(game);
        scores.remove(game);
    }

    @Override
    public List<String> getSummary() {
        List<String> result = new ArrayList<>();
        Comparator<String> gameComparator = (game1, game2) -> {
            int[] score1 = scores.get(game1);
            int[] score2 = scores.get(game2);
            int totalScore1 = score1[0] + score1[1];
            int totalScore2 = score2[0] + score2[1];
            if (totalScore1 == totalScore2) {
                int index1 = summary.get(totalScore1).indexOf(game1);
                int index2 = summary.get(totalScore2).indexOf(game2);
                return index2 - index1;
            } else {
                return totalScore2 - totalScore1;
            }
        };
        summary.values().forEach(games -> {
            games.sort(gameComparator);
            games.forEach(game -> {
                String[] teams = matches.get(game);
                int[] score = scores.get(game);
                result.add(String.format("%s %d - %d %s", teams[0], score[0], score[1], teams[1]));
            });
        });
        return result;
    }
}
