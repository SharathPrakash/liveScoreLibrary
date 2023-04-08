package com.sportRadar;

public class LiveScoreFactory {
    /**
     * Creates a new instance of LiveScore.
     *
     * @return a new instance of LiveScore
     */
    public static LiveScore createLiveScore() {
        return new LiveScoreImpl();
    }
}
