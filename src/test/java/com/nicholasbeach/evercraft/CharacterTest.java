package com.nicholasbeach.evercraft;

import com.nicholasbeach.evercraft.exception.RollOutOfBoundsException;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

public class CharacterTest {

    private Character character;

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
    public void canBeGivenStrength() {
        Ability strength = new Ability();
        character.setStrength(strength);
        assertThat(character.getStrength()).isEqualTo(strength);
    }

    @Test
    public void canBeGivenDexterity() {
        Ability dexterity = new Ability();
        character.setDexterity(dexterity);
        assertThat(character.getDexterity()).isEqualTo(dexterity);
    }

    @Test
    public void canBeGivenConstitution() {
        Ability constitution = new Ability();
        character.setConstitution(constitution);
        assertThat(character.getConstitution()).isEqualTo(constitution);
    }

    @Test
    public void canBeGivenWisdom() {
        Ability wisdom = new Ability();
        character.setWisdom(wisdom);
        assertThat(character.getWisdom()).isEqualTo(wisdom);
    }

    @Test
    public void canBeGivenIntelligence() {
        Ability intelligence = new Ability();
        character.setIntelligence(intelligence);
        assertThat(character.getIntelligence()).isEqualTo(intelligence);
    }

    @Test
    public void canAttackAnotherCharacter_AndIfTheRollIsEqualToTheVictimsArmorClass_TheAttackWillHit() {
        int roll = 15;
        assertAttackHitsVictimGiven(roll, roll, true);
    }

    @Test
    public void canAttackAnotherCharacter_AndIfTheRollIsGreaterThanTheVictimsArmorClass_TheAttackWillHit() {
        int roll = 15;
        assertAttackHitsVictimGiven(roll, roll - 1, true);
    }

    @Test
    public void canAttackAnotherCharacter_AndIfTheRollIsLessThanTheVictimsArmorClass_TheAttackWillMiss() {
        int roll = 15;
        assertAttackHitsVictimGiven(roll, roll + 1, false);
    }

    private void assertAttackHitsVictimGiven(int roll, int victimArmorClass, boolean hitExpected) {
        Character victim = new Character();
        victim.setArmorClass(victimArmorClass);
        boolean hit = character.attack(victim, roll);
        assertThat(hit).isEqualTo(hitExpected);
    }

    @Test(expected = RollOutOfBoundsException.class)
    public void canAttackAnotherCharacter_AndAnExceptionIsThrown_IfTheRollIsGreaterThan20() {
        character.attack(new Character(), 21);
    }

    @Test(expected = RollOutOfBoundsException.class)
    public void canAttackAnotherCharacter_AndAnExceptionIsThrown_IfTheRollIsLessThan1() {
        character.attack(new Character(), 0);
    }

    @Test
    public void canAttackAnotherCharacter_AndWhenTheyMiss_NoDamageIsDealt() {
        assertAttackDamageOnVictimGiven(5, false, 0);
    }

    @Test
    public void canAttackAnotherCharacter_AndWhenTheyHit_1PointOfDamageIsDealt() {
        assertAttackDamageOnVictimGiven(5, true, 1);
    }

    @Test
    public void canAttackAnotherCharacter_AndWhenTheyHit_AndTheRollIs20_2PointsOfDamageIsDealt() {
        assertAttackDamageOnVictimGiven(20, true, 2);
    }

    private void assertAttackDamageOnVictimGiven(int roll, boolean attackIsHit, int expectedDamage) {
        int hitPoints = 10;
        Character victim = new Character();
        victim.setHitPoints(hitPoints);
        victim.setArmorClass(attackIsHit ? roll : roll + 1);

        boolean hit = character.attack(victim, roll);
        assertThat(hit).isEqualTo(attackIsHit);
        assertThat(victim.getHitPoints()).isEqualTo(hitPoints - expectedDamage);
    }

    @Test
    public void whenHitPointsAreGreaterThanZeroTheCharacterIsAlive() {
        character.setHitPoints(1);
        assertThat(character.isAlive()).isTrue();
    }

    @Test
    public void whenHitPointsAreZeroTheCharacterIsDead() {
        character.setHitPoints(0);
        assertThat(character.isAlive()).isFalse();
    }

    @Test
    public void whenHitPointsAreLessThanZeroTheCharacterIsDead() {
        character.setHitPoints(-1);
        assertThat(character.isAlive()).isFalse();
    }
}