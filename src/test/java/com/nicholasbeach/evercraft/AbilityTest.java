package com.nicholasbeach.evercraft;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AbilityTest {

    @Test
    public void canBegGivenAScore() {
        int score = 5;
        Ability ability = new Ability();
        ability.setScore(score);
        assertThat(ability.getScore()).isEqualTo(score);
    }

    @Test(expected = ScoreOutOfBoundsException.class)
    public void whenGivenAScore_LessThan1_ThrowsAnOutOfBoundsException() {
        Ability ability = new Ability();
        ability.setScore(0);
    }
}
