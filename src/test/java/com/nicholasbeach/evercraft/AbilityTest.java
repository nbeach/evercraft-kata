package com.nicholasbeach.evercraft;

import com.nicholasbeach.evercraft.exception.ScoreOutOfBoundsException;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AbilityTest {

    private Ability ability;

    @Before
    public void before() {
        ability = new Ability();
    }

    @Test
    public void canBeGivenAScore() {
        int score = 5;
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
}
