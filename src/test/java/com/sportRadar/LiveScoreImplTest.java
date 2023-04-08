package com.sportRadar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LiveScoreImplTest {

    private LiveScore liveScore;

    @BeforeEach
    void setUp() {
        liveScore = new LiveScoreImpl();
    }

    @Test
    void testStartGame() {
        liveScore.startGame("teamA", "teamB");

        assertThrows(IllegalArgumentException.class, () -> liveScore.startGame("", "teamB"));
        assertThrows(NullPointerException.class, () -> liveScore.startGame("teamA", null));
        assertThrows(NullPointerException.class, () -> liveScore.startGame(null, "teamB"));
    }

    @Test
    void testUpdateScore() {
        liveScore.startGame("teamA", "teamB");
        liveScore.updateScore("teamA", "teamB", 1, 2);

        assertThrows(IllegalArgumentException.class, () -> liveScore.updateScore("teamA", "teamC", 1, 1));
        assertThrows(IllegalArgumentException.class, () -> liveScore.updateScore("teamC", "teamD", 1, 1));
    }

    @Test
    void testFinishGame() {
        liveScore.startGame("teamA", "teamB");
        liveScore.finishGame("teamA", "teamB");

        assertThrows(IllegalArgumentException.class, () -> liveScore.finishGame("teamA", "teamC"));
    }

    @Test
    void testGetSummary_NoGamesStarted() {
        LiveScore scoreTracker = new LiveScoreImpl();
        List<String> summary = scoreTracker.getSummary();

        assertTrue(summary.isEmpty());
    }

    @Test
    void testGetSummary() {
        liveScore.startGame("teamA", "teamB");
        liveScore.updateScore("teamA", "teamB", 2, 1);
        liveScore.startGame("teamC", "teamD");
        liveScore.updateScore("teamC", "teamD", 1, 1);

        List<String> summary = liveScore.getSummary();

        assertEquals(2, summary.size());
        assertEquals("teamA 2 - 1 teamB", summary.get(0));
        assertEquals("teamC 1 - 1 teamD", summary.get(1));
    }

    @Test
    void testGetSummary_MultipleGames() {

        liveScore.startGame("Mexico", "Canada");
        liveScore.updateScore("Mexico", "Canada", 0, 5);
        liveScore.startGame("Spain", "Brazil");
        liveScore.updateScore("Spain", "Brazil", 10, 2);
        liveScore.startGame("Germany", "France");
        liveScore.updateScore("Germany", "France", 2, 2);
        liveScore.startGame("Uruguay", "Italy");
        liveScore.updateScore("Uruguay", "Italy", 6, 6);
        liveScore.startGame("Argentina", "Australia");
        liveScore.updateScore("Argentina", "Australia", 3, 1);


        List<String> summary = liveScore.getSummary();

        assertEquals(5, summary.size());
        assertEquals("Uruguay 6 - 6 Italy", summary.get(0));
        assertEquals("Spain 10 - 2 Brazil", summary.get(1));
        assertEquals("Mexico 0 - 5 Canada", summary.get(2));
        assertEquals("Argentina 3 - 1 Australia", summary.get(3));
        assertEquals("Germany 2 - 2 France", summary.get(4));
    }
}
