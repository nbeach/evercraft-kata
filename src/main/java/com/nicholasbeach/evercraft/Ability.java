package com.nicholasbeach.evercraft;

public class Ability {

    int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        if(score < 1 || score > 20) {
            throw new ScoreOutOfBoundsException("The score " + score + " is outside of the allowed range of 1-20");
        }
        this.score = score;
    }
}
