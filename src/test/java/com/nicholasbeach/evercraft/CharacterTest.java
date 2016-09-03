package com.nicholasbeach.evercraft;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CharacterTest {

    @Test
    public void canBeGivenAName() {
        String name = "John Doe";
        Character character = new Character();
        character.setName(name);
        assertThat(character.getName()).isEqualTo(name);
    }

}