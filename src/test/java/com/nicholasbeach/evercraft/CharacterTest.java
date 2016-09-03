package com.nicholasbeach.evercraft;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CharacterTest {

    Character character;

    @Before
    public void before() {
        character = new Character();
    }

    @Test
    public void canBeGivenAName() {
        String name = "John Doe";
        character.setName(name);
        assertThat(character.getName()).isEqualTo(name);
    }

    @Test
    public void canBeGivenAnAlignment() {
        Alignment alignment = Alignment.Evil;
        character.setAlignment(alignment);
        assertThat(character.getAlignment()).isEqualTo(alignment);
    }

}