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
}
