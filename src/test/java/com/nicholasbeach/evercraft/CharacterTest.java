package com.nicholasbeach.evercraft;

import com.nicholasbeach.evercraft.exception.RollOutOfBoundsException;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CharacterTest {

    private Character character;
    private Ability ability;

    @Before
    public void before() {
        character = new Character();
        ability = new Ability();
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
    public void canBeGivenAMaximumHitPointsValue() {
        int hitPoints = 10;
        character.setMaximumHitPoints(hitPoints);
        assertThat(character.getMaximumHitPoints()).isEqualTo(hitPoints);
    }

    @Test
    public void canBeGivenACurrentHitPointsValue() {
        int hitPoints = 10;
        character.setMaximumHitPoints(hitPoints);
        assertThat(character.getMaximumHitPoints()).isEqualTo(hitPoints);
    }

    @Test
    public void hasAMaximumHitPointsOf5ByDefault() {
        assertThat(character.getMaximumHitPoints()).isEqualTo(5);
    }

    @Test
    public void hasACurrentHitPointsOf5ByDefault() {
        assertThat(character.getCurrentHitPoints()).isEqualTo(5);
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
        character.setStrength(ability);
        assertThat(character.getStrength()).isEqualTo(ability);
    }

    @Test
    public void canBeGivenDexterity() {
        character.setDexterity(ability);
        assertThat(character.getDexterity()).isEqualTo(ability);
    }

    @Test
    public void dexterityModifierIsAddedToArmorClass() {
        int armorClass = 5;
        character.setArmorClass(armorClass);

        int modifier = 3;
        character.setDexterity(getMockAbilityThatReturnsModifier(modifier));

        assertThat(character.getArmorClass()).isEqualTo(armorClass + modifier);
    }

    @Test
    public void whenDexterityIsNotSet_ArmorClassIsNotModified() {
        int armorClass = 5;
        character.setArmorClass(armorClass);
        character.setDexterity(null);
        assertThat(character.getArmorClass()).isEqualTo(armorClass);
    }

    @Test
    public void canBeGivenConstitution() {
        character.setConstitution(ability);
        assertThat(character.getConstitution()).isEqualTo(ability);
    }

    @Test
    public void constitutionModifierIsAddedToMaximumHitPoints() {
        int hitPoints = 5;
        character.setMaximumHitPoints(hitPoints);

        int modifier = 3;
        character.setConstitution(getMockAbilityThatReturnsModifier(modifier));

        assertThat(character.getMaximumHitPoints()).isEqualTo(hitPoints + modifier);
    }

    @Test
    public void constitutionModifierDoesNotReduceMaximumHitPointsToLessThan1() {
        character.setMaximumHitPoints(3);
        character.setConstitution(getMockAbilityThatReturnsModifier(-3));

        assertThat(character.getMaximumHitPoints()).isEqualTo(1);
    }

    @Test
    public void whenConstitutionIsNotSet_MaximumHitPointsAreNotModified() {
        int hitPoints = 5;
        character.setMaximumHitPoints(hitPoints);
        character.setConstitution(null);
        assertThat(character.getMaximumHitPoints()).isEqualTo(hitPoints);
    }

    private Ability getMockAbilityThatReturnsModifier(int modifier) {
        Ability ability = mock(Ability.class);
        when(ability.getModifier()).thenReturn(modifier);
        return ability;
    }

    @Test
    public void canBeGivenWisdom() {
        character.setWisdom(ability);
        assertThat(character.getWisdom()).isEqualTo(ability);
    }

    @Test
    public void canBeGivenIntelligence() {
        character.setIntelligence(ability);
        assertThat(character.getIntelligence()).isEqualTo(ability);
    }

    @Test
    public void canBeGivenCharisma() {
        character.setCharisma(ability);
        assertThat(character.getCharisma()).isEqualTo(ability);
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
        victim.setCurrentHitPoints(hitPoints);
        victim.setArmorClass(attackIsHit ? roll : roll + 1);

        boolean hit = character.attack(victim, roll);
        assertThat(hit).isEqualTo(attackIsHit);
        assertThat(victim.getCurrentHitPoints()).isEqualTo(hitPoints - expectedDamage);
    }

    @Test
    public void whenCurrentHitPointsAreGreaterThanZeroTheCharacterIsAlive() {
        character.setCurrentHitPoints(1);
        assertThat(character.isAlive()).isTrue();
    }

    @Test
    public void whenCurrentHitPointsAreZeroTheCharacterIsDead() {
        character.setCurrentHitPoints(0);
        assertThat(character.isAlive()).isFalse();
    }

    @Test
    public void whenCurrentHitPointsAreLessThanZeroTheCharacterIsDead() {
        character.setCurrentHitPoints(-1);
        assertThat(character.isAlive()).isFalse();
    }
}