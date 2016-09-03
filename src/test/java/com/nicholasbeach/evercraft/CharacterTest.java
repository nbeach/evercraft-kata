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

    @Test
    public void canBeGivenHitPoints() {
        int hitPoints = 10;
        character.setHitPoints(hitPoints);
        assertThat(character.getHitPoints()).isEqualTo(hitPoints);
    }

    @Test
    public void has5HitPointsByDefault() {
        assertThat(character.getHitPoints()).isEqualTo(5);
    }

    @Test
    public void canBeGivenAnArmorClass() {
        int armorClass = 5;
        character.setArmorClass(armorClass);
        assertThat(character.getArmorClass()).isEqualTo(armorClass);
    }

    @Test
    public void hasAnArmorClassOf10ByDefault() {
        assertThat(character.getArmorClass()).isEqualTo(10);
    }


    @Test
    public void canAttackAnotherCharacter_AndHitIfTheirRollIsEqualToTheVictimsArmorClass() {
        int roll = 15;
        testAttackResultGiven(roll, roll, true);
    }

    @Test
    public void canAttackAnotherCharacter_AndHitIfTheirRollIsGreaterThanTheVictimsArmorClass() {
        int roll = 15;
        testAttackResultGiven(roll, roll - 1, true);
    }

    @Test
    public void canAttackAnotherCharacter_AndMissIfTheirRollIsLessThanTheVictimsArmorClass() {
        int roll = 15;
        testAttackResultGiven(roll, roll + 1, false);
    }

    private void testAttackResultGiven(int roll, int victimArmorClass, boolean hitExpected) {
        Character victim = new Character();
        victim.setArmorClass(victimArmorClass);
        boolean hit = character.attack(victim, roll);
        assertThat(hit).isEqualTo(hitExpected);
    }
}