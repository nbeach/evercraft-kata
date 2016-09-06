package com.nicholasbeach.evercraft;

import com.nicholasbeach.evercraft.exception.ScoreOutOfBoundsException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AbilityTest {

    private Ability ability;

    @Before
    public void before() {
        ability = new Ability();
    }

    @Test
    public void whenGivenAScore_AllowsAMinimumValueOf1() {
        assertScoreCanBeSetTo(1);
    }

    @Test
    public void whenGivenAScore_AllowsAMaximumValueOf20() {
        assertScoreCanBeSetTo(20);
    }

    private void assertScoreCanBeSetTo(int score) {
        ability.setScore(score);
        assertThat(ability.getScore()).isEqualTo(score);
    }

    @Test(expected = ScoreOutOfBoundsException.class)
    public void whenGivenAScore_LessThan1_ThrowsAnOutOfBoundsException() {
        ability.setScore(0);
    }

    @Test(expected = ScoreOutOfBoundsException.class)
    public void whenGivenAScore_GreaterThan20_ThrowsAnOutOfBoundsException() {
        ability.setScore(21);
    }

    @Test
    public void hasAModifierThatVariesByScore() {
        Map<Integer, Integer> scoreToModifierMap = new HashMap<>();
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

        for(Integer score : scoreToModifierMap.keySet()) {
            Ability ability = new Ability();
            ability.setScore(score);

            assertThat(ability.getModifier()).isEqualTo(scoreToModifierMap.get(score));
        }

    }
}
