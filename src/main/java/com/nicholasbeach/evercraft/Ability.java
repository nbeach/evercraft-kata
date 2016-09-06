package com.nicholasbeach.evercraft;

import com.nicholasbeach.evercraft.exception.ScoreOutOfBoundsException;

import java.util.HashMap;
import java.util.Map;

public class Ability {

    private int score;
    private Map<Integer, Integer> scoreToModifierMap;

    public Ability() {
        scoreToModifierMap = new HashMap<>();
        scoreToModifierMap.put(1, -5);
        scoreToModifierMap.put(2, -4);
        scoreToModifierMap.put(3, -4);
        scoreToModifierMap.put(4, -3);
        scoreToModifierMap.put(5, -3);
        scoreToModifierMap.put(6, -2);
        scoreToModifierMap.put(7, -2);
        scoreToModifierMap.put(8, -1);
        scoreToModifierMap.put(9, -1);
        scoreToModifierMap.put(10, 0);
        scoreToModifierMap.put(11, 0);
        scoreToModifierMap.put(12, 1);
        scoreToModifierMap.put(13, 1);
        scoreToModifierMap.put(14, 2);
        scoreToModifierMap.put(15, 2);
        scoreToModifierMap.put(16, 3);
        scoreToModifierMap.put(17, 3);
        scoreToModifierMap.put(18, 4);
        scoreToModifierMap.put(19, 4);
        scoreToModifierMap.put(20, 5);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        if(score < 1 || score > 20) {
            throw new ScoreOutOfBoundsException("The score " + score + " is outside of the allowed range of 1-20");
        }
        this.score = score;
    }

    public Integer getModifier() {
        return scoreToModifierMap.get(score);
    }
}
