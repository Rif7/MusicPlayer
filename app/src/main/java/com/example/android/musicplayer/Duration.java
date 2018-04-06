package com.example.android.musicplayer;

import java.util.Locale;

public class Duration {
    private int seconds;

    Duration(int seconds) {
        this.seconds = seconds;
    }

    Duration(int normalizedMinutes, int normalizedSeconds) {
        this.seconds = normalizedMinutes*60 + normalizedSeconds;
    }

    int getNormalizedMinutes() {
        return seconds / 60;
    }

    int getNormalizedSeconds() {
        return seconds % 60;
    }

    public String getTime() {
        return String.format(Locale.US, "%d:%02d", getNormalizedMinutes(), getNormalizedSeconds());
    }
}
